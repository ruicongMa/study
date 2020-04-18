package cn.mark.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程交替打印 ABCABC...
 *
 * @author Mark
 * @create 2020/3/12
 */
public class ThreadAlternatePrintDemo {

    public static void main(String[] args) {

        final Alternate alternate = new Alternate();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    alternate.printA(i);
                }
            }
        }, "线程A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    alternate.printB(i);
                }
            }
        }, "线程B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    alternate.printC(i);
                }
            }
        }, "线程C").start();
    }
}

class Alternate {

    private int num = 1;//标识当前哪个线程该执行
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void printA(int totalLoop) {
        try {
            lock.lock();
            if (num != 1) {
                conditionA.await();
            }
            for (int i = 1; i <= 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
            }
            num = 2;
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB(int totalLoop) {
        try {
            lock.lock();
            if (num != 2) {
                conditionB.await();
            }
            for (int i = 1; i <= 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
            }
            num = 3;
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC(int totalLoop) {
        try {
            lock.lock();
            if (num != 3) {
                conditionC.await();
            }
            for (int i = 1; i <= 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
            }
            System.out.println("===========================================");
            num = 1;
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
