package cn.system.class01;

/**
 * @author Mark
 * @date 2021/8/20 22:25
 */
public class Code02_IsBST {

    public static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public static class Info {
        boolean isBST;
        int max;
        int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static boolean isBST(Node head) {
        return process(head).isBST;
    }

    public static Info process(Node x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int max = x.val;

        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
        }
        int min = x.val;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
        }
        boolean isBST = true;
        if (leftInfo != null && !leftInfo.isBST) {
            isBST = false;
        }
        if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        }
        if (leftInfo != null && leftInfo.max >= x.val) {
            isBST = false;
        }
        if (rightInfo != null && rightInfo.min <= x.val) {
            isBST = false;
        }
        return new Info(isBST, max, min);
    }

    public static void main(String[] args) {
        Node node9 = new Node(9);
        Node node4 = new Node(4);
        Node node15 = new Node(15);
        Node node3 = new Node(3);
        Node node5 = new Node(5);
        Node node10 = new Node(10);
        Node node16 = new Node(16);
        node9.left = node4;
        node9.right = node15;
        node4.left = node3;
        node4.right = node5;
        node15.left = node10;
        node15.right = node16;

        // mid(node9);
        System.out.println(isBST(node9));

    }

    public static void mid(Node head) {
        if (head == null) {
            return;
        }
        mid(head.left);
        System.out.println(head.val);
        mid(head.right);
    }
}
