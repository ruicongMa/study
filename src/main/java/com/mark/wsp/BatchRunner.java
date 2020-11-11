package com.mark.wsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class BatchRunner<T> {

    private final static int _defaultMaxConcurrentThreadNum = 10;
    private final static int _defaultTimeoutInMilliseconds = Integer.MAX_VALUE;

    private final ExecutorService threadPool;

    private CountDownLatch latch;
    private Queue<Action<T>> actionQueue;
    private int actionSize;

    private volatile boolean isRunning = true;

    private volatile boolean haveException;
    private volatile Exception exception;


    private int maxConcurrentThreadNum;
    private int timeoutInMilliseconds;

    private Object lifeCycleMonitor = new Object();

    private List<T> results = Collections.synchronizedList(new ArrayList<T>());


    public BatchRunner(List<Action<T>> actions, ExecutorService threadPool, int maxConcurrentThreadNum, int timeoutInMilliseconds) {
        this.actionQueue = new ConcurrentLinkedQueue<>();
        this.actionQueue.addAll(actions);
        this.actionSize = actions.size();
        this.threadPool = threadPool;
        this.maxConcurrentThreadNum = maxConcurrentThreadNum;
        this.timeoutInMilliseconds = timeoutInMilliseconds;
    }


    public List<T> run() throws Exception {


        int workNum = Math.min(this.actionSize, this.maxConcurrentThreadNum);
        this.latch = new CountDownLatch(workNum);
        for (int i = 0; i < workNum; i++) {
            Runner processor = new Runner();
            threadPool.submit(processor);
        }

        boolean foo = latch.await(this.timeoutInMilliseconds, TimeUnit.MILLISECONDS);
        shutdown();

        if (!foo) {
            throw new TimeoutException();
        }

        return results;
    }


    private void haveException(Exception e) {
        synchronized (this.lifeCycleMonitor) {
            this.isRunning = false;
            this.haveException = true;
            this.exception = e;
        }
    }

    private boolean isHaveException() {
        return this.haveException;
    }

    private boolean isRunning() {
        synchronized (this.lifeCycleMonitor) {
            return this.isRunning;
        }
    }

    private void shutdown() {
        synchronized (this.lifeCycleMonitor) {
            this.isRunning = false;
        }
    }


    private class Runner implements Runnable {

        private volatile boolean isActive = true;

        private boolean isActive() {
            return isRunning && isActive;
        }

        public void shutdown() {
            this.isActive = false;
        }

        @Override
        public void run() {

            while (isActive()) {
                Action<T> action = actionQueue.poll();
                if (action == null) {
                    break;
                }

                try {
                    results.add(action.get());
                } catch (Exception t) {
                    haveException(t);
                    break;
                }
            }

            latch.countDown();

        }
    }


    public static class TimeoutException extends Exception {

    }

}