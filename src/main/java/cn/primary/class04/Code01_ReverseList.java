package cn.primary.class04;

/**
 * @author Mark
 * @date 2021/6/18 下午4:57
 */
public class Code01_ReverseList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            this.value = data;
        }

        @Override
        public String toString() {
            return value + "";
        }
    }

    // 单链表反转
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

    // 双链表反转
    public static DoubleNode reverseDoubleList(DoubleNode head) {
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

    public static void main(String[] args) {
        // 单链表反转测试
        Node node1 = new Node(1);
        node1.next = new Node(2);
        node1.next.next = new Node(3);

        Node head = reverseLinkedList(node1);
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
        System.out.println("===========");

        // 双链表反转测试
        DoubleNode doubleNode1 = new DoubleNode(1);
        doubleNode1.next = new DoubleNode(2);
        doubleNode1.next.next = new DoubleNode(3);
        doubleNode1.next.next.last = doubleNode1.next;
        doubleNode1.next.last = doubleNode1;

        DoubleNode doubleNodeHead = reverseDoubleList(doubleNode1);
        while (doubleNodeHead != null) {
            System.out.println(doubleNodeHead.next + "<-" + doubleNodeHead + "->" + doubleNodeHead.last);
            doubleNodeHead = doubleNodeHead.next;
        }
        System.out.println();
        System.out.println("===========");
    }
}
