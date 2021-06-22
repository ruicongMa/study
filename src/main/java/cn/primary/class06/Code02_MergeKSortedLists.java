package cn.primary.class06;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Mark
 * @date 2021/6/22 下午4:42
 */
// 测试链接：https://leetcode.com/problems/merge-k-sorted-lists/
public class Code02_MergeKSortedLists {

    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public static class ListNodeComparator implements Comparator<ListNode> {

        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }

    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparator());
        // 把所有链表都加入优先级队列中，按照比较器规则，添加完后自动形成小顶堆
        for (int i = 0; i < lists.length; i++) {
            heap.add(lists[i]);
        }
        if (heap.isEmpty()) {
            return null;
        }
        ListNode head = heap.poll();
        ListNode pre = head;
        if (pre.next != null) {
            heap.add(pre.next);
        }
        while (!heap.isEmpty()) {
            ListNode cur = heap.poll();
            pre.next = cur;
            pre = cur;
            if (cur.next != null) {
                heap.add(cur.next);
            }
        }
        return head;
    }
}
