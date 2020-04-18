package cn.mark.tree.threadedbinarytree;

/**
 * 线索化二叉树介绍：
 * 1.n个结点的二叉链表中含有n+1  【公式 2n-(n-1)=n+1】 个空指针域。利用二叉链表中的空指针域，存放指向该结点在某种遍历次序下的前驱和后继结点的指针（这种附加的指针称为"线索"）
 * 2.这种加上了线索的二叉链表称为线索链表，相应的二叉树称为线索二叉树(Threaded BinaryTree)。根据线索性质的不同，线索二叉树可分为前序线索二叉树、中序线索二叉树和后序线索二叉树三种
 * 3.一个结点的前一个结点，称为前驱结点
 * 4.一个结点的后一个结点，称为后继结点
 *
 * @author Mark
 * @create 2020/3/1
 */
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //先手动维护树之间的关系
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        //用于后序线索化二叉树遍历
        node2.parent = root;
        node3.parent = root;
        node4.parent = node2;
        node5.parent = node2;
        node6.parent = node3;


        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);
        // System.out.println("未进行中序线索化前，中序遍历如下：");//{8, 3, 10, 1, 14, 6}

        //中序线索化二叉树
        // threadedBinaryTree.midThreadedBinaryTree();
        // System.out.println("进行中序线索化二叉树后，中序遍历如下：");
        // threadedBinaryTree.midTreadedBinaryTreeList();//{8, 3, 10, 1, 14, 6}

        //前序线索化二叉树
        // threadedBinaryTree.preThreadedBinaryTree();
        // threadedBinaryTree.preThreadedBinaryTreeList();//{1, 3, 8, 10, 6, 14}

        //后序线索化二叉树
        threadedBinaryTree.postThreadedBinaryTree();
        threadedBinaryTree.postThreadedBinaryTreeList();//{8,10,3,14,6,1}

    }
}

//定义线索化二叉树
class ThreadedBinaryTree {
    HeroNode root;

    HeroNode pre;//前驱节点

    public ThreadedBinaryTree(HeroNode root) {
        this.root = root;
    }

    /**
     * 效率高了，不需要进行递归
     * 前序线索化二叉树遍历（不能用原来的前序方式进行遍历，因为左右指针发生了变化）
     */
    public void preThreadedBinaryTreeList() {
        if (this.root != null) {
            //定义临时temp
            HeroNode temp = this.root;
            while (temp != null) {
                //循环输出当前节点
                while (temp != null && temp.leftType == 0) {
                    System.out.println(temp);
                    temp = temp.left;
                }
                //循环找到rightType = 1
                while (temp != null && temp.rightType == 1) {
                    System.out.println(temp);
                    temp = temp.right;
                }
                System.out.println(temp);
                temp = temp.right;
            }
        }
    }

    public void preThreadedBinaryTree() {
        if (this.root != null) {
            this.preThreadedBianryTree(this.root);
        }
    }

    /**
     * 前序线索化二叉树(针对节点左右指针为空的情况进行前驱、后继处理)
     *
     * @param curNode 当前节点
     */
    public void preThreadedBianryTree(HeroNode curNode) {
        if (curNode == null) {
            return;
        }
        //当前节点线索化
        if (curNode.left == null) {
            curNode.left = pre;
            curNode.leftType = 1;
        }
        if (pre != null && pre.right == null) {
            pre.right = curNode;
            pre.rightType = 1;
        }
        pre = curNode;
        //向左递归前序线索化二叉树
        if (curNode.leftType == 0) {
            preThreadedBianryTree(curNode.left);
        }
        //向右递归前序线索二叉树
        if (curNode.rightType == 0) {
            preThreadedBianryTree(curNode.right);
        }
    }

    /**
     * 效率高了，不需要进行递归
     * 中序线索化二叉树遍历（不能用原来的中序方式进行遍历，因为左右指针发生了变化）
     */
    public void midTreadedBinaryTreeList() {
        if (this.root != null) {
            //定义临时temp，指向当前节点
            HeroNode temp = this.root;
            while (temp != null) {
                //循环找到leftType = 1
                while (temp != null && temp.leftType == 0) {
                    temp = temp.left;
                }
                //输出当前节点
                System.out.println(temp);
                //循环找到当前节点的后继节点 temp.rightType = 1
                while (temp != null && temp.rightType == 1) {
                    //输出当前节点的后继节点
                    temp = temp.right;
                    System.out.println(temp);
                }
                temp = temp.right;
            }
        }
    }

    public void midThreadedBinaryTree() {
        if (this.root != null) {
            this.midThreadedBinaryTree(this.root);
        }
    }

    /**
     * 中序线索化二叉树(针对节点左右指针为空的情况进行前驱、后继处理)
     *
     * @param curNode 当前节点
     */
    public void midThreadedBinaryTree(HeroNode curNode) {
        if (curNode == null) {
            return;
        }
        //向左递归中序线索化二叉树
        midThreadedBinaryTree(curNode.left);
        //线索化当前节点
        if (curNode.left == null) {
            curNode.left = pre;//设置当前节点的左指针指向前驱节点
            curNode.leftType = 1;//修改当前节点的左指针类型为前驱节点
        }
        if (pre != null && pre.right == null) {
            pre.right = curNode;//让前驱节点的右指针指向当前节点
            pre.rightType = 1;//修改前驱节点的右指针类型为后继节点
        }
        //记录前驱节点，让当前节点为下一个节点的前驱节点
        pre = curNode;
        //向右递归中序线索化二叉树
        midThreadedBinaryTree(curNode.right);
    }

    /**
     * 效率高了，不需要进行递归
     * 后序线索化二叉树遍历（不能用原来的后序方式进行遍历，因为左右指针发生了变化）
     */
    public void postThreadedBinaryTreeList() {
        if (this.root != null) {
            HeroNode temp = this.root;
            while (temp != null && temp.leftType == 0) {
                temp = temp.left;
            }
            HeroNode preNode = null;
            while (temp != null) {
                //右节点是线索
                if (temp.rightType == 1) {
                    System.out.println(temp);
                    preNode = temp;
                    temp = temp.right;
                } else {
                    //如果上个处理的节点是当前节点的右节点
                    if (temp.right == preNode) {
                        System.out.println(temp);
                        if (temp == root) {
                            return;
                        }
                        preNode = temp;
                        temp = temp.parent;
                    } else {    //如果从左节点的进入则找到有子树的最左节点
                        temp = temp.right;
                        while (temp != null && temp.leftType == 0) {
                            temp = temp.left;
                        }
                    }
                }
            }
        }
    }

    public void postThreadedBinaryTree() {
        if (this.root != null) {
            this.postThreadedBinaryTree(this.root);
        }
    }

    /**
     * 后序线索化二叉树（针对节点左右指针为空的情况进行前驱、后继处理）
     *
     * @param curNode
     */
    public void postThreadedBinaryTree(HeroNode curNode) {
        if (curNode == null) {
            return;
        }
        //向左递归后续线索化二叉树
        postThreadedBinaryTree(curNode.left);
        //向右递归后续线索化二叉树
        postThreadedBinaryTree(curNode.right);
        //线索化当前节点
        if (curNode.left == null) {
            curNode.left = pre;
            curNode.leftType = 1;
        }
        if (pre != null && pre.right == null) {
            pre.right = curNode;
            pre.rightType = 1;
        }
        pre = curNode;
    }

}

//定义HeroNode
class HeroNode {
    int no;
    String name;
    HeroNode left;
    HeroNode right;
    int leftType;//0表示左子树，1表示前驱节点
    int rightType;//0表示右子树，1表示后继节点
    HeroNode parent;//父节点，用于处理后序线索化二叉树遍历

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name=" + name +
                '}';
    }
}
