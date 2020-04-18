package cn.mark.cas;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * @author Mark
 * @create 2020/3/12
 */
public class Demo {

    public static void main(String[] args) {
        // final Atomic atomic = new Atomic();
        //
        // new Thread(new Runnable() {
        //     @Override
        //     public void run() {
        //         try {
        //             TimeUnit.SECONDS.sleep(3);
        //         } catch (InterruptedException e) {
        //             e.printStackTrace();
        //         }
        //         atomic.getNum();
        //     }
        // }).start();
        //
        // // while (Thread.activeCount() > 2) {
        // //     Thread.yield();
        // // }
        //
        // while (atomic.num == 0) {
        //
        // }
        //
        // System.out.println("===============");
        //
        // System.out.println(atomic.num);


        // System.out.println(Runtime.getRuntime().availableProcessors());

        // Object o1 = new Object();
        // SoftReference<Object> softReference = new SoftReference<>(o1);
        // System.out.println(o1);
        // System.out.println(softReference.get());
        //
        // System.out.println("===");
        // System.gc();
        // System.out.println(o1);
        // System.out.println(softReference.get());

        String str = "abc";
        String str1 = "abc";
        String str2 = new String("abc");
        System.out.println(str == str1);
        System.out.println(str == str2);
        System.out.println(str == str2.intern());


    }
}

class Atomic {

    volatile int num = 0;

    public synchronized int getNum() {
        return num++;
    }
}
