package com.mark;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mark
 * @date 2020/4/24 16:59
 */
public class Test {

    final ReentrantLock lock = new ReentrantLock();

    public void test1() {
        try {
            lock.lock();
            //do something...
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void test2() {
        // lock.lock();
        int i = 10 / 0;
        try {
            // System.out.println(i);
            //do something...
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // lock.unlock();
            System.out.println("finally");
        }
    }

    public static void main(String[] args) {

        test2();
        // System.out.println("test");
        // test();
        // List<Integer> list = new ArrayList<>();
        // list.add(2);
        // list.add(1);
        // list.add(3);
        // list.sort(new Comparator<Integer>() {
        //     @Override
        //     public int compare(Integer o1, Integer o2) {
        //         return o1.compareTo(o2);
        //     }
        // });
        // System.out.println(list);

        // System.out.println(calc(89));
        // String patt = "^x*(x|(yx)+)$";
        // String str = "yxx";
        // boolean matches = str.matches(patt);
        // System.out.println(matches);

        // List<String> list = new ArrayList<>();
        // list.add("a");
        // list.add("b");
        // System.out.println(list);
        // Iterator<String> iterator = list.iterator();
        // while (iterator.hasNext()) {
        //     String next = iterator.next();
        //     System.out.println(next);
        //     iterator.remove();
        // }
        // System.out.println(list);

    }

    public static int calc(int x) {
        if (x <= 0) {
            return x;
        } else {
            return (x + 1) + calc(x - 2);
        }
    }

    public static void test() {
        try {
            int i = 10;
            if (i % 2 == 0) {
                System.out.println("ok----");
                throw new RuntimeException("ok");
            }
        } finally {
            System.out.println("finally");
        }
    }
}