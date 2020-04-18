package cn.mark.hashtab;

import java.util.Scanner;

/**
 * 采用数组 + 单向链表模拟哈希表
 *
 * @author Mark
 * @create 2020/2/29
 */
public class HashTabDemo {

    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("delete: 删除雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    emp = hashTab.findById(id);
                    if (emp != null) {
                        System.out.println(emp);
                    } else {
                        System.out.println("从hash表中未找到该员工~ id=" + id);
                    }
                    break;
                case "delete":
                    System.out.println("请输入要删除的id");
                    id = scanner.nextInt();
                    hashTab.deleteById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//定义hashTab
class HashTab {
    //定义数组，用于存放链表
    private EmpSingleLinkedList[] empSingleLinkedListArr;
    private int size;//数组大小

    public HashTab(int size) {
        this.size = size;
        empSingleLinkedListArr = new EmpSingleLinkedList[size];
        //初始化数组
        for (int i = 0; i < size; i++) {
            empSingleLinkedListArr[i] = new EmpSingleLinkedList();
        }
    }

    /**
     * 根据员工id查找员工
     *
     * @param id
     * @return
     */
    public Emp findById(int id) {
        return empSingleLinkedListArr[hash(id)].findById(id);
    }

    /**
     * 根据员工id删除员工
     *
     * @param id
     */
    public void deleteById(int id) {
        empSingleLinkedListArr[hash(id)].deleteById(id);
    }

    /**
     * 遍历所有员工
     */
    public void list() {
        for (int i = 0; i < size; i++) {
            empSingleLinkedListArr[i].list(i);
        }
    }

    /**
     * 添加员工（没判重）
     *
     * @param emp
     */
    public void add(Emp emp) {
        //根据员工id计算hash散列值
        int empSingleLinkedListNo = hash(emp.id);
        empSingleLinkedListArr[empSingleLinkedListNo].add(emp);
    }

    /**
     * 根据员工id获取hash散列值（存放在哪个链表中）
     *
     * @param id
     * @return
     */
    public int hash(int id) {
        return id % size;
    }
}

//单向链表存储员工
class EmpSingleLinkedList {

    Emp head;//头节点

    /**
     * 根据员工id删除员工
     *
     * @param id
     */
    public void deleteById(int id) {
        if (head == null) {
            return;
        }
        //如果删除的节点就是头节点
        if (head.id == id) {
            head = head.next;
            return;
        }
        //定义辅助指针temp，找到待删除节点的前一个节点进行删除 temp.next = temp.next.next;
        Emp temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.id == id) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("未找到要删除的员工，id=" + id);
        }
    }

    /**
     * 根据员工id查询员工
     *
     * @param id
     * @return
     */
    public Emp findById(int id) {
        if (head == null) {
            return null;
        } else {
            //定义辅助指针curEmp
            Emp curEmp = head;
            while (curEmp != null) {
                if (curEmp.id == id) {
                    return curEmp;
                }
                curEmp = curEmp.next;
            }
            return null;
        }
    }

    /**
     * 遍历当前链表中所有员工信息
     */
    public void list(int i) {
        if (head == null) {
            System.out.printf("第%d条链表为空~\n", i + 1);
        } else {
            //定义辅助指针curEmp
            System.out.printf("第%d条链表信息：", i + 1);
            Emp curEmp = head;
            while (curEmp != null) {
                System.out.printf(" => id=%d,name=%s", curEmp.id, curEmp.name);
                curEmp = curEmp.next;
            }
            System.out.println();
        }
    }

    /**
     * 添加员工，默认直接添加到链表最后（id自增，也可以按编号顺序添加）
     *
     * @param emp
     */
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
        } else {
            //定义辅助指针curEmp
            Emp curEmp = head;
            while (curEmp.next != null) {
                curEmp = curEmp.next;
            }
            curEmp.next = emp;
        }
    }
}

//员工信息
class Emp {
    int id;
    String name;
    Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
