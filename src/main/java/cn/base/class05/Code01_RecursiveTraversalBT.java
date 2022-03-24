package cn.base.class05;

/**
 * 递归版的二叉树遍历（前序、中序、后序）
 *
 * @author Mark
 * @date 2021/7/5 上午10:06
 */
public class Code01_RecursiveTraversalBT {

    public static class Node {
        int val;
        Node left;
        Node right;

        public Node(int data) {
            this.val = data;
        }
    }

    // 前序（头、左、右）
    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.val);
        pre(head.left);
        pre(head.right);
    }

    // 中序（左、头、右）
    public static void mid(Node head) {
        if (head == null) {
            return;
        }
        mid(head.left);
        System.out.println(head.val);
        mid(head.right);
    }

    // 后序（左、右、头）
    public static void post(Node head) {
        if (head == null) {
            return;
        }
        post(head.left);
        post(head.right);
        System.out.println(head.val);
    }

    // 递归序
    public static void f(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.val);
        f(head.left);
        System.out.println(head.val);
        f(head.right);
        System.out.println(head.val);
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.left = node2;
        node1.right = node3;
        f(node1);
    }

}
