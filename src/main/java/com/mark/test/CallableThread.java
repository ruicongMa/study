package com.mark.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("进入Call方法，开始休眠，休眠时间为：" + System.currentTimeMillis());
        Thread.sleep(10000);
        return "今天停电";
    }

    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Callable<String> call = new CallableThread();
        Future<String> fu = es.submit(call);
        es.shutdown();
        Thread.sleep(5000);
        System.out.println("主线程休眠5秒，当前时间" + System.currentTimeMillis());
        String str = fu.get();
        System.out.println("Future已拿到数据，str=" + str + ";当前时间为：" + System.currentTimeMillis());
    }
}