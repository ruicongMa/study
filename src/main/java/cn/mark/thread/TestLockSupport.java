package cn.mark.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Mark
 * @date 2020/11/16 09:40
 */
public class TestLockSupport {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);


        Thread t = new Thread(() -> {
            for(int i=0;i<10;i++){
                System.out.println(i);
                if(i == 5){
                    latch.countDown();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // for (int i = 0; i < 10; i++) {
            //     System.out.println(i);
            //     if (i == 5) {
            //         LockSupport.park();
            //     }
            //     if(i == 8){
            //         LockSupport.park();
            //     }
            //     try {
            //         TimeUnit.SECONDS.sleep(1);
            //     } catch (InterruptedException e) {
            //         e.printStackTrace();
            //     }
            // }
        });
        t.start();
        // LockSupport.unpark(t);

        try {
            TimeUnit.SECONDS.sleep(1);
            latch.await();
            System.out.println("ok");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
            latch.await();
            System.out.println("moe");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
