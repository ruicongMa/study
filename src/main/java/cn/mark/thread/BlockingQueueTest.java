package cn.mark.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Mark
 * @date 2020/11/6 14:27
 */
public class BlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
        boolean result = false;
        result = queue.offer(1);
        System.out.println("offer-1-" + result);
        result = queue.offer(2);
        System.out.println("offer-2-" + result);
        // result = queue.offer(3, 2, TimeUnit.SECONDS);
        // System.out.println("offer-3-" + result);
        // queue.add(3);
        queue.remove();
        queue.remove();
        queue.remove();
    }
}
