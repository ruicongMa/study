package cn.mark.linkedlist;

/**
 * 删除单链表的中间节点
 *
 * @author Mark
 * @date 2020/4/16 9:55
 */
public class Exec_03 {

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(new HeroNode(1, String.valueOf(1), String.valueOf(1)));
        singleLinkedList.add(new HeroNode(2, String.valueOf(2), String.valueOf(2)));
        singleLinkedList.add(new HeroNode(3, String.valueOf(3), String.valueOf(3)));
        singleLinkedList.add(new HeroNode(4, String.valueOf(4), String.valueOf(4)));
        singleLinkedList.add(new HeroNode(5, String.valueOf(5), String.valueOf(5)));
        HeroNode heroNode = removeMidNode(singleLinkedList.getHead());
        System.out.println(heroNode);
    }

    public static HeroNode removeMidNode(HeroNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        if (head.next.next.next == null) {
            return head.next.next;
        }
        HeroNode fast = head.next.next.next;//快指针
        HeroNode slow = head.next;//慢指针
        //slow最终指向中间节点的前驱
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //进行删除
        slow.next = slow.next.next;
        return head;
    }
}
