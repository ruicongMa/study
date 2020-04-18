package cn.mark.stack;

import java.util.Scanner;

/**
 * 采用单链表模拟栈
 *
 * @author Mark
 * @create 2020/2/23
 */
public class SingleLinkedStackDemo {

    public static void main(String[] args) {

        SingleLinkedStack stack = new SingleLinkedStack(4);

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
                    stack.push(new Node(value));
                    break;
                case "pop":
                    try {
                        Node node = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", node.value);
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

class SingleLinkedStack {
    private Node head = new Node(-1);//头节点
    private int maxSize;//链表的大小，表示栈的大小

    public SingleLinkedStack(int maxSize) {
        this.maxSize = maxSize;
    }

    //遍历栈中的数据，从链表末尾进行遍历
    public void list() {
        if (isEmpty()) {
            System.out.println("栈为空，无法遍历~~");
            return;
        }
        Node temp = head.next;
        //获取栈中有效节点的个数
        int length = getLength();
        //定义辅助指针
        //进行遍历，倒数第1个，倒数第2个，以此类推
        for (int i = 1; i <= length; i++) {//有多少个有效节点就遍历多少次
            for (int j = 0; j < length - i; j++) {//倒数第几个元素需要从j遍历到length-i次
                temp = temp.next;
            }
            System.out.printf("stack[%d] = %d\n", length - i, temp.value);
            temp = head.next;//重置temp
        }
    }

    //判断栈是否为空
    public boolean isEmpty() {
        return head.next == null;
    }

    //判断栈中有效节点的个数
    public int getLength() {
        if (head.next == null) {
            return 0;
        }
        int count = 0;
        Node temp = head.next;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    //判断栈是否满
    public boolean isFull() {
        return getLength() == maxSize;
    }

    //入栈
    public void push(Node node) {
        if (isFull()) {
            System.out.println("栈已经满了，无法添加~~");
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    //出栈
    public Node pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，无法弹出数据~~");
        }
        //定义辅助指针temp，指向最后一个节点的前一个节点
        Node temp = head;
        while (true) {
            if (temp.next.next == null) {
                break;
            }
            temp = temp.next;
        }
        Node lastNode = temp.next;
        temp.next = null;
        return lastNode;
    }

}

//存储数组的节点
class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
