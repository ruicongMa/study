package com.mark.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author Mark
 * @date 2022/6/28 18:56
 */
public class Test01 {

    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Callable<String> call = new CallableThread();
        FutureTask<String> task = new FutureTask<String>(call);
        es.submit(task);
        es.shutdown();
        Thread.sleep(5000);
        System.out.println("主线程等待5秒，当前时间为：" + System.currentTimeMillis());
        String str = task.get();
        System.out.println("Future已拿到数据，str=" + str + ";当前时间为：" + System.currentTimeMillis());
    }
}
