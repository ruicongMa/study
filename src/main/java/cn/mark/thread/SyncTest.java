package cn.mark.thread;

/**
 * @author Mark
 * @date 2020/11/6 10:58
 */
public class SyncTest {

    public static void main(String[] args) {
        SyncTest syncTest = new SyncTest();
        new Thread(() -> {
            syncTest.test1();
        }, "thread-A").start();
        new Thread(() -> {
            syncTest.test2();
        }, "thread-B").start();
    }

    public synchronized void test1() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-test1");
        test2();
    }

    public synchronized void test2() {
        System.out.println(Thread.currentThread().getName() + "-test2");
    }
}
