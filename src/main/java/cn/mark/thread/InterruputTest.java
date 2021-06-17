package cn.mark.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mark
 * @date 2020/11/7 19:04
 */
public class InterruputTest {

    private static int num = 0;
    private static Lock lock = new ReentrantLock();


    public static void incr() {
        lock.lock();
        try {
            num++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // for (int i = 0; i < 1000; i++) {
        //     new Thread(() -> {
        //         InterruputTest.incr();
        //     }).start();
        // }
        //
        // System.out.println(InterruputTest.num);

        Thread t1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(3000);
                    System.out.println("sjfjslfjslfjlsf");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // Thread.currentThread().interrupt();
                }
            }
            System.out.println(Thread.currentThread().getName() + "-");
        }, "t1");

        t1.start();

        TimeUnit.SECONDS.sleep(2);
        t1.interrupt();

        System.out.println("main over");
    }

}
