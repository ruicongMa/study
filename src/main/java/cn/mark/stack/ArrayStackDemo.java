package cn.mark.stack;

import java.util.Scanner;

/**
 * 用数组模拟stack
 *
 * @author Mark
 * @create 2020/2/23
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        //测试一下ArrayStack 是否正确
        //先创建一个ArrayStack对象->表示栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~~");
    }
}

class ArrayStack {
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，数据存储在数组中
    private int top = -1;//栈顶，开始为-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
    }

    //判断栈是否为空
    public boolean isEmpty() {
        return top == -1;
    }

    //判断栈是否满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满，无法添加~");
            return;
        }
        this.stack[++top] = value;
    }

    //出栈，从栈顶弹出数据
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，无法出栈");
        }
        return this.stack[top--];
    }

    //遍历栈，从栈顶开始遍历
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，无法遍历，请添加数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, this.stack[i]);
        }
    }
}
