package com.mark.netty;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mark
 * @date 2022/5/5 18:59
 */
public abstract class Animal {

    private static Map<String, String> map = new HashMap<>();

    public Animal() {
        System.out.println(this);
        map.put(this.getClass().getSimpleName(), this.getClass().getName());
    }

    public Map<String, String> getMap() {
        return map;
    }

    public static void main(String[] args) {
        Animal animal = new Dog();
    }
}
