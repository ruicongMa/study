package cn.mark.linkedlist;

import java.util.Stack;

/**
 * 带有头节点的单向链表
 *
 * @author Mark
 * @create 2020/2/22
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        // HeroNode node1 = new HeroNode(1, "宋江", "及时雨");
        // HeroNode node2 = new HeroNode(2, "卢俊义", "玉麒麟");
        // HeroNode node3 = new HeroNode(3, "吴用", "智多星");
        // HeroNode node4 = new HeroNode(4, "林冲", "豹子头");
        //
        // SingleLinkedList singleLinkedList = new SingleLinkedList();
        // singleLinkedList.add(node1);
        // singleLinkedList.add(node4);
        // singleLinkedList.add(node3);
        // singleLinkedList.add(node2);
        // singleLinkedList.list();

        // 按照节点的编号从小到大的顺序添加
        // singleLinkedList.addByOrder(node1);
        // singleLinkedList.addByOrder(node4);
        // singleLinkedList.addByOrder(node2);
        // singleLinkedList.addByOrder(node3);
        // singleLinkedList.addByOrder(node3);
        // singleLinkedList.list();

        // 修改节点
        // HeroNode updateNode = new HeroNode(4, "张三", "小张三");
        // singleLinkedList.update(updateNode);
        // singleLinkedList.list();

        //删除节点
        // singleLinkedList.delete(3);
        // singleLinkedList.delete(1);
        // singleLinkedList.delete(4);

        // singleLinkedList.list();

        // System.out.println("单向链表有效节点的个数为：" + getLength(singleLinkedList.getHead()));

        // int k = 1;
        // System.out.println("查找单向链表中倒数第" + k + "个节点：" + getNodeOfk(singleLinkedList.getHead(), k));

        //将单向链表反转
        // System.out.println("反转后的单向链表如下：");
        // reverseSingleLinkedList(singleLinkedList.getHead());
        // singleLinkedList.list();

        //逆序打印单链表
        // reversePrintSingleLinkedList(singleLinkedList.getHead());

        //测试合并两个有序单链表，合并之后新的链表依然后续
        HeroNode node1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode node2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode node3 = new HeroNode(3, "吴用", "智多星");
        HeroNode node4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        singleLinkedList1.addByOrder(node4);
        singleLinkedList1.addByOrder(node1);
        singleLinkedList1.addByOrder(node3);
        singleLinkedList1.addByOrder(node2);

        System.out.println("有序单链表1原来的节点信息如下：");
        singleLinkedList1.list();

        System.out.println("===============================");

        HeroNode node11 = new HeroNode(1, "假宋江", "假及时雨");
        HeroNode node22 = new HeroNode(2, "假卢俊义", "假玉麒麟");
        HeroNode node33 = new HeroNode(3, "假吴用", "假智多星");
        HeroNode node44 = new HeroNode(4, "假林冲", "假豹子头");
        HeroNode node5 = new HeroNode(5, "鲁智深", "花和尚");

        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.addByOrder(node11);
        singleLinkedList2.addByOrder(node33);
        singleLinkedList2.addByOrder(node22);
        singleLinkedList2.addByOrder(node44);
        singleLinkedList2.addByOrder(node5);

        System.out.println("有序单链表2原来的节点信息如下：");
        singleLinkedList2.list();
        System.out.println("===============================");
        System.out.println("合并之后新的有序链表信息如下：");

        HeroNode newHead = mergeSingleList(singleLinkedList1.getHead(), singleLinkedList2.getHead());
        SingleLinkedList newSingleLinkedList = new SingleLinkedList();
        newSingleLinkedList.list(newHead);

    }

    /**
     * 合并两个有序单链表，合并之后新的链表依然有序
     *
     * @param head1 单链表1头节点
     * @param head2 单链表2头节点
     * @return 新链表的头节点
     */
    public static HeroNode mergeSingleList(HeroNode head1, HeroNode head2) {
        //定义新链表的头节点
        HeroNode newHead = new HeroNode(0, "", "");
        if (head1.next == null) {
            //如果单链表1为空链表，则newHead.next = head2.next;
            newHead.next = head2.next;
        } else if (head2.next == null) {
            //如果单链表2为空链表，则newHead.next = head1.next;
            newHead.next = head1.next;
        }
        //定义辅助指针temp1,用于遍历链表1
        HeroNode temp1 = head1.next;
        //定义辅助指针temp2，用于遍历链表2
        HeroNode temp2 = head2.next;
        //定义辅助指针temp，记录后续节点添加位置
        HeroNode temp = newHead;
        while (temp1 != null || temp2 != null) {
            if (temp1 == null) {
                temp.next = temp2;
                temp2 = temp2.next;
            } else if (temp2 == null) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                if (temp1.no <= temp2.no) {
                    temp.next = temp1;
                    temp1 = temp1.next;
                } else {
                    temp.next = temp2;
                    temp2 = temp2.next;
                }
            }
            temp = temp.next;
        }
        return newHead;
    }

    /**
     * 逆序打印单链表
     * 思路：
     * 利用栈数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
     * 不会破坏原单链表结构
     *
     * @param head 头节点
     */
    public static void reversePrintSingleLinkedList(HeroNode head) {
        if (head.next == null) {
            return;
        }
        //定义栈对象，用于存储每个节点
        Stack<HeroNode> stack = new Stack<HeroNode>();
        //定义临时指针temp
        HeroNode temp = head.next;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 将单链表反转
     * 思路：
     * 1.从头到尾遍历原单向链表，逐一取出当前节点添加到新的链表头后
     * 2.将原链表的头节点的下一个节点指向新链表头节点的下一个节点，head.next = reverseHead.next
     *
     * @param head 头节点
     */
    public static void reverseSingleLinkedList(HeroNode head) {
        //如果链表为空或者只有一个节点，则不用反转
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义cur指针，指向当前节点
        HeroNode cur = head.next;
        //定义当前节点的下一个节点
        HeroNode next = null;
        //定义反转后的头节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        while (cur != null) {
            //临时保存当前节点的下一个节点
            next = cur.next;
            //将当前节点的下一个节点指向新链表头节点的下一个节点
            cur.next = reverseHead.next;
            //将新链表的头节点的下一个节点指向当前节点
            reverseHead.next = cur;
            //将当前节点后移
            cur = next;
        }
        //将原链表头节点的下一个节点指向新链表头节点的下一个节点
        head.next = reverseHead.next;
    }

    /**
     * 统计单向链表中有效节点的个数
     *
     * @param head 头节点
     * @return
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int count = 0;
        //定义临时指针temp
        HeroNode temp = head.next;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    /**
     * 查找单链表中倒数第k个节点
     *
     * @param head 头节点
     * @param k    倒数第k个节点
     * @return
     */
    public static HeroNode getNodeOfk(HeroNode head, int k) {
        if (head.next == null) {
            return null;
        }
        //获取单向链表的有效节点个数
        int length = getLength(head);
        if (k <= 0 || k > length) {
            return null;
        }
        //定义辅助指针temp
        HeroNode temp = head.next;
        //从链表第一个节点遍历到 length - k 就是倒数第k个节点
        for (int i = 0; i < length - k; i++) {
            temp = temp.next;
        }
        return temp;
    }
}

//定义英雄单链表类
class SingleLinkedList {

    //初始化一个不带数据的头节点，头节点不要动
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到单链表最后，不考虑节点的顺序。
     * 思路：
     * 1.找到单链表中的最后一个节点
     * 2.最后一个节点的next指向当前node
     *
     * @param node 要添加的节点
     */
    public void add(HeroNode node) {
        if (node == null) {
            return;
        }
        //定义一个辅助节点
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        //说明找到最后一个节点
        temp.next = node;
    }

    /**
     * 按照编号顺序来添加节点到链表中
     *
     * @param node
     */
    public void addByOrder(HeroNode node) {
        //定义辅助变量temp，最终temp指向要添加节点的前一个节点（单向链表）
        HeroNode temp = head;
        boolean flag = false;//标识添加的节点是否在链表中存在
        while (true) {
            if (temp.next == null) {
                break;
            }

            //找到了要添加的位置
            if (temp.next.no > node.no) {
                break;
            } else if (temp.next.no == node.no) {
                flag = true;
                break;
            }

            temp = temp.next;
        }

        if (flag) {
            System.out.printf("添加的节点[%d]已经存在于链表中，请别重复添加~\r\n", node.no);
        } else {
            node.next = temp.next;
            temp.next = node;
        }

    }

    /**
     * 按照编号修改节点信息，编号不能修改
     *
     * @param node
     */
    public void update(HeroNode node) {
        if (head.next == null) {
            return;
        }
        //定义辅助变量temp
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.name = node.name;
            temp.nickName = node.nickName;
        } else {
            System.out.printf("没有找到编号[%d]的节点，不能修改~", node.no);
        }
    }

    /**
     * 删除节点
     * 思路：
     * 1.找到待删除节点的前一个节点temp
     * 2.temp.next = temp.next.next
     *
     * @param no
     */
    public void delete(int no) {
        if (head.next == null) {
            return;
        }
        //定义辅助变量temp，最终指向删除节点的前一个节点（单向链表）
        HeroNode temp = head;//而不是head.next
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            //说明找到要删除的节点的前一个节点
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("未找到要删除的节点[%d]\r\n", no);
        }

    }

    public void list(HeroNode head) {
        this.head = head;
        list();
    }

    /**
     * 遍历链表中的节点信息
     */
    public void list() {
        if (head.next == null) {
            return;
        }
        //说明链表不为空
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }


}

//定义英雄节点类
class HeroNode {

    int no;
    String name;
    String nickName;
    HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
