package cn.base.class02;

/**
 * @author Mark
 * @date 2021/6/27 下午5:31
 */
public class Code01_ReverseList {

    public static class Node {
        int val;
        Node next;

        public Node(int data) {
            this.val = data;
        }
    }

    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        // Node node1 = new Node(1);
        // node1.next = new Node(2);
        // node1.next.next = new Node(3);
        // Node head = reverseLinkedList(node1);
        // while (head != null) {
        //     System.out.print(head.val + " ");
        //     head = head.next;
        // }
        // System.out.println();

        // DoubleNode doubleNode1 = new DoubleNode(1);
        // doubleNode1.next = new DoubleNode(2);
        // doubleNode1.next.last = doubleNode1;
        // doubleNode1.next.next = new DoubleNode(3);
        // doubleNode1.next.next.last = doubleNode1.next;
        //
        // DoubleNode head = reverseDoubleLinkedList(doubleNode1);
        // DoubleNode tail = null;
        // while (head != null) {
        //     System.out.print(head.val + " ");
        //     tail = head;
        //     head = head.next;
        // }
        // System.out.println();
        // while (tail != null) {
        //     System.out.print(tail.val + " ");
        //     tail = tail.last;
        // }
        // System.out.println();

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node2;
        node2.next = node3;

        // node3 = null;
        System.out.println(node1);
    }

    public static class DoubleNode {
        int val;
        DoubleNode last;
        DoubleNode next;

        public DoubleNode(int data) {
            this.val = data;
        }
    }

    public static DoubleNode reverseDoubleLinkedList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }
}
