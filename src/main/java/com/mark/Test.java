package com.mark;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Mark
 * @date 2020/4/24 16:59
 */
public class Test {

    public static void main(String[] args) {
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

        int a = 65;
        System.out.println(a % 64);
        System.out.println(a & 63);
        System.out.println(0 ^ 0);
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