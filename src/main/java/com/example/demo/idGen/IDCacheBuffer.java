package com.example.demo.idGen;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class IDCacheBuffer {
    private IDCache[] caches; //双buffer
    private volatile int currentPos; //当前的使用的cache的index
    private volatile boolean nextReady; //下一个segment是否处于可切换状态
    private volatile boolean initOk; //是否初始化完成
    private final AtomicBoolean threadRunning; //线程是否在运行中
    private final ReadWriteLock lock;//读写锁

    public IDCacheBuffer() {
        this.caches = new IDCache[]{new IDCache(this), new IDCache(this)};
        this.currentPos = 0;
        this.initOk = false;
        this.threadRunning = new AtomicBoolean(false);
        this.lock = new ReentrantReadWriteLock();
    }

    private int nextPos() {
        return (this.currentPos + 1) % 2;
    }

    public IDCache getCurrent() {
        return this.caches[this.currentPos];
    }

    public IDCache getNext() {
        return this.caches[nextPos()];
    }

    public void switchPos() {
        this.currentPos = nextPos();
    }

    public Lock rLock() {
        return this.lock.readLock();
    }

    public Lock wLock() {
        return this.lock.writeLock();
    }

    public boolean isInitOk() {
        return this.initOk;
    }

    public void setInitOk(boolean initOk) {
        this.initOk = initOk;
    }

    public boolean isNextReady() {
        return this.nextReady;
    }

    public void setNextReady(boolean nextReady) {
        this.nextReady = nextReady;
    }

    public AtomicBoolean getThreadRunning() {
        return threadRunning;
    }
}