package cn.mark.linkedlist;

/**
 * Josephu  问题为：设编号为1，2，… n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，
 * 数到m 的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，
 * 依次类推，直到所有人出列为止，由此产生一个出队编号的序列。
 * <p>
 * 采用单向环形链表解决约瑟夫问题
 *
 * @author Mark
 * @create 2020/2/23
 */
public class Josepfu {

    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.list();

        circleSingleLinkedList.jumperCircle(1, 2, 5);
    }
}

//环形单向链表
class CircleSingleLinkedList {

    private Boy first;

    /**
     * 小孩出圈
     *
     * @param k 从k个小孩开始
     * @param m 数m下
     * @param n n个小孩
     */
    public void jumperCircle(int k, int m, int n) {
        if (k < 1 || m < 1 || k > n) {
            return;
        }
        if (first == null) {
            return;
        }
        //定义辅助指针helper指向最后一个小孩
        Boy helper = first;
        while (helper.next != first) {
            helper = helper.next;
        }
        //在从k个小孩开始前，需要让first和helper移动k-1次
        for (int i = 0; i < k - 1; i++) {
            first = first.next;
            helper = helper.next;
        }
        //依次出圈
        while (true) {
            if (helper == first) {
                //说明圈里只有一个小孩
                break;
            }
            //从k个小孩开始报数，数m下，需要让first和helper移动m-1次
            for (int i = 0; i < m - 1; i++) {
                first = first.next;
                helper = helper.next;
            }
            //输出要出圈的小孩
            System.out.printf("%d号小孩出圈\n", first.no);
            //踢出出圈的小孩
            first = first.next;
            helper.next = first;
        }
        System.out.printf("最后一个小孩【%d】\n", first.no);
    }

    /**
     * 构建环形单向链表
     * 思路：
     * 1.先构建第一个小孩，使first指向第一个小孩，并形成环形(自己指向自己)
     * 2.后续进来的小孩依次添加进来即可
     *
     * @param nums 小孩数
     */
    public void addBoy(int nums) {
        if (nums < 1) {
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                //first指向第一个小孩
                first = boy;
                boy.next = first;
                curBoy = first;
            } else {
                curBoy.next = boy;
                boy.next = first;
                curBoy = curBoy.next;
            }
        }

    }

    /**
     * 遍历环形单向链表
     */
    public void list() {
        if (first == null) {
            return;
        }
        //定义辅助指针curBoy
        Boy curBoy = first;
        while (true) {
            System.out.println(curBoy);
            if (curBoy.next == first) {
                break;
            }
            curBoy = curBoy.next;
        }
    }
}

class Boy {
    int no;//编号
    Boy next;

    public Boy(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
