package com.example.demo.idGen;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class IDCache {

    private volatile long maxSize;

    private ConcurrentLinkedQueue<String> queue;

    private IDCacheBuffer buffer;

    public IDCache(IDCacheBuffer buffer) {
        this.queue = new ConcurrentLinkedQueue<>();
        this.buffer = buffer;
    }

    public IDCacheBuffer getBuffer() {
        return buffer;
    }

    public String get() {
        return this.queue.poll();
    }

    public boolean cache(List<String> ids) {
        return this.queue.addAll(ids);
    }

    public boolean isReachedWarningLine() {
        long idle = this.queue.size();
        return this.maxSize - idle < 0.9 * this.maxSize;
    }

    @Override
    public String toString() {
        return "IdBufferPool{" +
                "buffer=" + buffer +
                '}';
    }


}