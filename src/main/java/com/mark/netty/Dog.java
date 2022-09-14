package com.mark.netty;

/**
 * @author Mark
 * @date 2022/5/5 19:00
 */
public class Dog extends Animal {

    public Dog() {
        System.out.println("dog=>" + this);
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        System.out.println(dog.getMap());
        Cat cat = new Cat();
        System.out.println(cat.getMap());
        System.out.println(Dog.class.getName());

    }
}
