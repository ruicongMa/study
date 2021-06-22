package cn.primary.class06;

/**
 * @author Mark
 * @date 2021/6/22 下午6:10
 */
public class Code03_TraversalBinaryTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            this.value = v;
        }
    }

    // 前序遍历：头、左、右
    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    // 中序遍历：左、头、右
    public static void mid(Node head) {
        if (head == null) {
            return;
        }
        mid(head.left);
        System.out.println(head.value);
        mid(head.right);
    }

    // 后序遍历：左、右、头
    public static void pos(Node head) {
        if (head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.println(head.value);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("========");
        mid(head);
        System.out.println("========");
        pos(head);
        System.out.println("========");
    }
}
