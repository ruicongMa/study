package cn.mark.linkedlist;

/**
 * 如何优雅的反转单链表？
 * https://mp.weixin.qq.com/s/WNO3KNhS6oU7rUvCNEGw8g
 *
 * @author Mark
 * @date 2020/4/7 10:16
 */
public class Exec_01 {

    public static void main(String[] args) {
        /**
         * 【题目描述】
         * 反转单链表。例如链表为：
         *
         * 1->2->3->4
         *
         * 反转后为
         *
         * 4->3->2->1
         *
         * 【要求】
         * 如果链表的长度为 N, 时间复杂度达到 O(N), 额外空间复杂度达到 O(1)
         */

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(new Node(1));
        singleLinkedList.add(new Node(2));
        singleLinkedList.add(new Node(3));
        singleLinkedList.add(new Node(4));
        // singleLinkedList.add(new Node(5));
        // singleLinkedList.add(new Node(6));
        // singleLinkedList.add(new Node(7));
        // singleLinkedList.add(new Node(8));
        // singleLinkedList.add(new Node(9));

        //打印原始链表
        singleLinkedList.list();

        //反转链表
        // singleLinkedList.reverse(singleLinkedList.getHead());

        System.out.println("========================");

        //打印反转后的链表
        // singleLinkedList.list();

        // singleLinkedList.reverse2(singleLinkedList.getHead());

        // Node node = singleLinkedList.reverseKGroup(singleLinkedList.getHead().next, 3);
        Node node = singleLinkedList.solve(singleLinkedList.getHead().next, 2);
        System.out.println(node);
    }

    static class SingleLinkedList {

        private Node head = new Node(0);

        public Node getHead() {
            return head;
        }


        public Node solve(Node head, int k) {
            //调用反转方法
            head = reverse2(head);
            //调用每k个为一组的逆序函数（从头部开始组起）
            head = reverseKGroup(head, k);
            //在反转一次
            head = reverse2(head);
            return head;
        }

        /**
         * @param head 这个头节点指向的是第一个节点
         * @param k    每k个分一组
         * @return
         */
        public Node reverseKGroup(Node head, int k) {
            Node temp = head;
            for (int i = 1; i < k && temp != null; i++) {
                temp = temp.next;
            }
            //判断节点的数量是否能够凑成一组
            if (temp == null) {
                return head;
            }
            Node t2 = temp.next;
            temp.next = null;
            //把当前的组进行逆序
            Node newHead = reverse2(head);
            //把之后的节点进行分组逆序
            Node newTemp = reverseKGroup(t2, k);
            //把两部分连接起来
            head.next = newTemp;
            return newHead;
        }

        //简单添加，直接往链表尾部添加
        public void add(Node node) {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }

        //遍历
        public void list() {
            Node temp = head;
            while (temp.next != null) {
                System.out.println(temp.next);
                temp = temp.next;
            }
        }

        //采用递归进行反转链表
        public Node reverse2(Node head) {
            if (head == null || head.next == null) {
                return head;
            }
            Node newList = reverse2(head.next);
            head.next.next = head;
            head.next = null;
            return newList;
        }

        /**
         * 反转链表
         * 思路：
         * 1.用临时变量保存当前节点的后驱节点
         * 2.把当前节点的后驱节点指向它的前驱节点
         *
         * @param head
         */
        public void reverse(Node head) {
            Node next = null;
            Node pre = null;
            Node cur = head.next;
            while (cur != null) {
                next = cur.next;
                //当前节点的后驱指向前驱
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            head.next = pre;
        }
    }

    static class Node {
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

}
