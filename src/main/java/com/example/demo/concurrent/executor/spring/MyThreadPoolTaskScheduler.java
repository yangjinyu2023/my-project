package com.example.demo.concurrent.executor.spring;

import org.springframework.core.task.TaskRejectedException;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.scheduling.support.TaskUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 自定义ThreadPoolTaskScheduler，实现自我取消的功能
 *
 * @author yangjinyu
 * @time 2022/4/1 19:46
 */
public class MyThreadPoolTaskScheduler extends ThreadPoolTaskScheduler {

    private final Map<Object, ScheduledFuture<?>> scheduledTasks = new IdentityHashMap<>();

    @Nullable
    private volatile ErrorHandler errorHandler;

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public void cancelTask(Object identifier) {
        ScheduledFuture<?> future = scheduledTasks.get(identifier);
        if (null != future) {
            future.cancel(true);
        }
    }

    public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long period, Object identifier) {
        ScheduledExecutorService executor = getScheduledExecutor();
        try {
            ScheduledFuture<?> future =  executor.scheduleAtFixedRate(errorHandlingTask(task, true), 0, period, TimeUnit.MILLISECONDS);
            scheduledTasks.put(identifier, future);
            return future;
        }
        catch (RejectedExecutionException ex) {
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, ex);
        }
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long period) {
        ScheduledExecutorService executor = getScheduledExecutor();
        try {
            ScheduledFuture<?> future =  executor.scheduleAtFixedRate(errorHandlingTask(task, true), 0, period, TimeUnit.MILLISECONDS);
            scheduledTasks.put(((ScheduledMethodRunnable) task).getTarget(), future);
            return future;
        }
        catch (RejectedExecutionException ex) {
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, ex);
        }
    }

    @Override
	public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, long delay) {
		ScheduledExecutorService executor = getScheduledExecutor();
		try {
			ScheduledFuture<?> future = executor.scheduleWithFixedDelay(errorHandlingTask(task, true), 0, delay, TimeUnit.MILLISECONDS);
			scheduledTasks.put(((ScheduledMethodRunnable) task).getTarget(), future);
			return future;
		}
		catch (RejectedExecutionException ex) {
			throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, ex);
		}
	}

    private Runnable errorHandlingTask(Runnable task, boolean isRepeatingTask) {
        return TaskUtils.decorateTaskWithErrorHandler(task, this.errorHandler, isRepeatingTask);
    }
}