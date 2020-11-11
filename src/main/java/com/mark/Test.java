package com.mark;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mark
 * @date 2020/4/24 16:59
 */
public class Test {

    public static void main(String[] args) {
        // System.out.println("test");

        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(20);
        list.add(1);
        list.add(3);
        System.out.println(list);
        list.sort(Integer::compareTo);
        System.out.println(list);
    }
}
