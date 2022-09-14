package cn.primary.class01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mark
 * @date 2021/9/14 19:06
 */
public class MyTest {


    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING = -1 << COUNT_BITS;
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    private static final int STOP = 1 << COUNT_BITS;
    private static final int TIDYING = 2 << COUNT_BITS;
    private static final int TERMINATED = 3 << COUNT_BITS;

    private static final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));

    // Packing and unpacking ctl
    private static int runStateOf(int c) {
        return c & ~CAPACITY;
    }

    private static int workerCountOf(int c) {
        return c & CAPACITY;
    }

    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    public static void main(String[] args) {
        // System.out.println(Integer.toBinaryString(RUNNING));
        // System.out.println(Integer.toBinaryString(0));
        // System.out.println(Integer.toBinaryString(ctlOf(RUNNING, 0)));
        int a = ctl.incrementAndGet();
        int c = ctl.get();
        System.out.println(a);
        System.out.println(c);
        System.out.println(Integer.toBinaryString(c));
        System.out.println(Integer.toBinaryString(workerCountOf(c)));
    }
}
