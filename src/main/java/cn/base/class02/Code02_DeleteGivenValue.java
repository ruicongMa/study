package cn.base.class02;

/**
 * @author Mark
 * @date 2021/6/27 下午6:17
 */
public class Code02_DeleteGivenValue {

    public static class Node {
        int val;
        Node next;

        public Node(int data) {
            this.val = data;
        }
    }

    // 把给定的值都删除
    public static Node deleteAllGivenValue(Node head, int val) {
        // head来到第一个不需要删的位置
        while (head != null) {
            if (head.val != val) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        // 1 2 1 1 3 2
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        node1.next = node2;
        Node node11 = new Node(1);
        node2.next = node11;
        Node node111 = new Node(1);
        node11.next = node111;
        Node node3 = new Node(3);
        node111.next = node3;
        node3.next = new Node(2);

        Node head = deleteAllGivenValue(node1, 1);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
