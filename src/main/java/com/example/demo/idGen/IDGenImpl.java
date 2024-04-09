package com.example.demo.idGen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class IDGenImpl implements IDGen {
    private static final Logger logger = LoggerFactory.getLogger(IDGenImpl.class);

    /**
     * IDCache未初始化成功时的异常码
     */
    private static final String EXCEPTION_ID_CACHE_INIT_FALSE = "-1";
    /**
     * IdPoolBuffer中的两个IdPool均未从DB中装载时的异常码
     */
    private static final String EXCEPTION_ID_TWO_POOLS_ARE_NULL = "-2";

    private volatile boolean initOK = false;
    private final IDCacheBuffer buffer = new IDCacheBuffer();
    private ExecutorService executor = new ThreadPoolExecutor(1, 2, 0,
            TimeUnit.SECONDS, new SynchronousQueue<>(), new UpdateThreadFactory());

    public static class UpdateThreadFactory implements ThreadFactory {
        private static int threadInitNumber = 0;

        private static synchronized int nextThreadNum() {
            return threadInitNumber++;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "Thread-IDCache-Update-" + nextThreadNum());
        }
    }

    @Override
    public boolean init() {
        logger.info("IDGen Init ...");
        // 确保加载到kv后才初始化成功
        cacheFromDB(buffer.getCurrent());
        initOK = true;
        return initOK;
    }

    @Override
    public Result get() {
        if (!initOK) {
            return new Result(EXCEPTION_ID_CACHE_INIT_FALSE, Status.EXCEPTION);
        }
        if (!buffer.isInitOk()) {
            synchronized (buffer) {
                if (!buffer.isInitOk()) {
                    try {
                        cacheFromDB(buffer.getCurrent());
                        logger.info("Init buffer. Get sequences {} from DB", buffer.getCurrent());
                        buffer.setInitOk(true);
                    } catch (Exception e) {
                        logger.warn("Init buffer {} exception", buffer.getCurrent(), e);
                    }
                }
            }
        }
        return getIDFromCache();
    }

    private Result getIDFromCache() {
        while (true) {
            buffer.rLock().lock();
            try {
                IDCache cache = buffer.getCurrent();
                // !buffer.isNextReady() && cache.isReachedWarningLine()保证只有在需要的时候才会进行readyNext操作
                // compareAndSet(false, true)可以保证无论有多少次读取并发进行，只有一次能触发readyNext操作
                // 到达警戒值，触发缓存next
                if (!buffer.isNextReady() && cache.isReachedWarningLine()
                        && buffer.getThreadRunning().compareAndSet(false, true)) {
                    executor.execute(() -> {
                        IDCache next = buffer.getNext();
                        boolean updateOk = false;
                        try {
                            cacheFromDB(next);
                            updateOk = true;
                            logger.info("cache from DB {}", next);
                        } catch (Exception e) {
                            logger.warn("cacheFromDB() exception", e);
                        } finally {
                            if (updateOk) {
                                // 只有读锁都释放，才能加写锁
                                buffer.wLock().lock();
                                buffer.setNextReady(true);
                                buffer.wLock().unlock();
                            }
                            buffer.getThreadRunning().set(false);
                        }
                    });
                }
                String id = cache.get();
                if (id != null) {
                    return new Result(id, Status.SUCCESS);
                }
            } finally {
                buffer.rLock().unlock();
            }

            // 等待next缓存完毕
            waitAndSleep(buffer);

            // 切换cache
            buffer.wLock().lock();
            try {
                // 这里再次获取id，避免多个线程都操作了switchPos
                String id = buffer.getCurrent().get();
                if (id != null) {
                    return new Result(id, Status.SUCCESS);
                }
                if (buffer.isNextReady()) {
                    buffer.switchPos();
                    buffer.setNextReady(false);
                } else {
                    logger.error("Both two caches in {} are not ready!", buffer);
                    return new Result(EXCEPTION_ID_TWO_POOLS_ARE_NULL, Status.EXCEPTION);
                }
            } finally {
                buffer.wLock().unlock();
            }
        }
    }

    private void cacheFromDB(IDCache cache) {
        logger.info("IdGen updateCacheFromDb ...");
        // 从数据库批量获取序列
        cache.cache(getDataBaseSequence());
    }

    private AtomicLong count = new AtomicLong();

    public List<String> getDataBaseSequence() {
        // 模拟从数据库获取序列集合
        List<String> primaryKeyList = new ArrayList<>();
        long index = count.get();
        for (long i = 5000 * index; i < 5000 * (index + 1); i++) {
            primaryKeyList.add(String.valueOf(i));
        }
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count.getAndIncrement();
        return primaryKeyList;
    }

    private void waitAndSleep(IDCacheBuffer buffer) {
        int roll = 0;
        while (buffer.getThreadRunning().get()) {
            roll += 1;
            if (roll > 10000) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                    break;
                } catch (InterruptedException e) {
                    logger.warn("Thread {} Interrupted", Thread.currentThread().getName());
                    break;
                }
            }
        }
    }
}