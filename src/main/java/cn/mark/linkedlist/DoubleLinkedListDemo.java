package cn.mark.linkedlist;

/**
 * 双向链表
 *
 * @author Mark
 * @create 2020/2/23
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode2 node1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 node2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 node3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 node4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        // doubleLinkedList.add(node1);
        // doubleLinkedList.add(node4);
        // doubleLinkedList.add(node3);
        // doubleLinkedList.add(node2);

        doubleLinkedList.list();

        // HeroNode2 updateNode = new HeroNode2(2,"小懒虫","豹子头");
        // doubleLinkedList.update(updateNode);
        // System.out.println("修改后的链表信息如下：");
        // doubleLinkedList.list();

        // doubleLinkedList.delete(3);
        // doubleLinkedList.delete(1);
        // doubleLinkedList.delete(4);
        // System.out.println("删除后的链表信息如下：");
        // doubleLinkedList.list();

        //按照编号顺序添加节点
        doubleLinkedList.addByOrder(node4);
        doubleLinkedList.addByOrder(node1);
        doubleLinkedList.addByOrder(node4);
        doubleLinkedList.addByOrder(node3);
        doubleLinkedList.addByOrder(node2);
        doubleLinkedList.list();

    }

}

class DoubleLinkedList {

    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    /**
     * 按照元素编号添加节点到链表中
     *
     * @param node
     */
    public void addByOrder(HeroNode2 node) {
        if (node == null) {
            return;
        }
        HeroNode2 temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > node.no) {
                break;
            } else if (temp.next.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("要添加的节点【%d】已存在，请勿重复添加~\r\n", node.no);
        } else {
            node.pre = temp;
            node.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = node;
            }
            temp.next = node;
        }
    }

    /**
     * 修改节点信息，编号不能修改
     *
     * @param node
     */
    public void update(HeroNode2 node) {
        if (head.next == null) {
            return;
        }
        HeroNode2 temp = head.next;
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
            System.out.printf("为找到要修改的节点【%d】", node.no);
        }
    }

    /**
     * 删除节点
     *
     * @param no
     */
    public void delete(int no) {
        if (head.next == null) {
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("未找到要删除的节点【%d】", no);
        }
    }

    /**
     * 添加节点到链表末尾
     *
     * @param node
     */
    public void add(HeroNode2 node) {
        if (node == null) {
            return;
        }
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }

    /**
     * 遍历链表
     */
    public void list() {
        if (head.next == null) {
            return;
        }
        HeroNode2 temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode2 {
    int no;
    String name;
    String nickName;
    HeroNode2 pre;
    HeroNode2 next;

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
