package cn.primary.class04;

/**
 * @author Mark
 * @date 2021/6/20 下午6:21
 */
public class Code06_MergeTwoSortedLinkedList {

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int data) {
            this.val = data;
        }
    }

    public static ListNode mergeTwoSortedLinkedList(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        // 从两个有序的链表中找见头位置，谁小谁做头
        ListNode head = head1.val <= head2.val ? head1 : head2;
        ListNode cur1 = head.next;
        ListNode cur2 = head == head1 ? head2 : head1;
        ListNode pre = head;
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                pre.next = cur1;
                cur1 = cur1.next;
            } else {
                pre.next = cur2;
                cur2 = cur2.next;

            }
            pre = pre.next;
        }
        pre.next = cur1 != null ? cur1 : cur2;
        return head;
    }

    public static void main(String[] args) {
        ListNode firstListNode = new ListNode(1);
        firstListNode.next = new ListNode(3);
        firstListNode.next.next = new ListNode(5);

        ListNode secondListNode = new ListNode(1);
        secondListNode.next = new ListNode(2);

        ListNode head = mergeTwoSortedLinkedList(firstListNode, secondListNode);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
