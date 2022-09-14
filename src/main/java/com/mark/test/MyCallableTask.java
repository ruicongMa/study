package com.mark.test;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @author Mark
 * @date 2022/6/28 18:36
 */
public class MyCallableTask implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("callable do something");
        Thread.sleep(5000);
        System.out.println("call is done...");
        return new Random().nextInt(100);
    }
}
