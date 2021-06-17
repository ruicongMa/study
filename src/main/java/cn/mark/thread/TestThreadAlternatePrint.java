package cn.mark.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mark
 * @date 2020/11/19 10:19
 */
public class TestThreadAlternatePrint {

    static Lock lock = new ReentrantLock();
    static Condition c1 = lock.newCondition();
    static Condition c2 = lock.newCondition();
    static int num = 1;

    public static void main(String[] args) {
        char[] nums = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] arrs = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};

        new Thread(() -> {
            lock.lock();
            try {
                for (char c : arrs) {
                    if (num != 1) {
                        c1.await();
                    }
                    System.out.print(c);
                    TimeUnit.SECONDS.sleep(1);
                    num = 2;
                    c2.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                for (char c : nums) {
                    if (num != 2) {
                        c2.await();
                    }
                    System.out.print(c + " ");
                    TimeUnit.SECONDS.sleep(1);
                    num = 1;
                    c1.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();

    }
}
