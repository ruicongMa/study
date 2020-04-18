package cn.mark.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Mark
 * @create 2020/3/12
 */
public class Demo {

    public static void main(String[] args) {
        // test();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("call");
                int sum = 0;
                for(int i=0;i<=100;i++){
                    sum+=i;
                }
                return sum;
            }
        });

        executorService.shutdown();
    }

    public static void test() {
        System.out.println("aaa");
        try {
            int a = 10 / 0;
            System.out.println("try=====");
        } finally {
            System.out.println("finally===============");
        }
    }
}
