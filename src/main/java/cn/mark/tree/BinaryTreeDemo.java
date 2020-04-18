package cn.mark.tree;

import java.util.*;

/**
 * 二叉树
 * <p>
 * 前序遍历: 先输出父节点，再遍历左子树和右子树
 * 中序遍历: 先遍历左子树，再输出父节点，再遍历右子树
 * 后序遍历: 先遍历左子树，再遍历右子树，最后输出父节点
 * 小结: 看输出父节点的顺序，就确定是前序，中序还是后序
 *
 * @author Mark
 * @create 2020/2/29
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {

        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        root.left = node2;
        root.right = node3;
        node3.right = node4;
        node3.left = node5;

        BinaryTree binaryTree = new BinaryTree(root);

        //前序遍历
        // binaryTree.preOrder();
        // System.out.println("======================");
        // binaryTree.list(binaryTree.preOrderNoRecursion());
        //中序遍历
        // binaryTree.midOrder();
        // System.out.println("======================");
        // binaryTree.list(binaryTree.midOrderNoRecursion());
        //后序遍历
        // binaryTree.postOrder();
        // System.out.println("======================");
        // binaryTree.list(binaryTree.postOrderNoRecursion());

        //从上往下打印二叉树
        binaryTree.list(binaryTree.buildFromTopToButtom());

        //后序遍历查找
        // HeroNode heroNode = binaryTree.postOrderSearch(5);
        // if (heroNode != null) {
        //     System.out.println(heroNode);
        // } else {
        //     System.out.println("未找到改英雄");
        // }

        //删除5号节点
        // System.out.println("删除前，前序遍历如下：");
        // binaryTree.preOrder();
        // System.out.println("删除后，前序遍历如下：");
        // binaryTree.delete(5);
        // binaryTree.preOrder();

        // System.out.println("删除前，前序遍历如下：");
        // binaryTree.preOrder();
        // System.out.println("删除后，前序遍历如下：");
        // binaryTree.delete2(3);
        // binaryTree.preOrder();
    }

}

//二叉树
class BinaryTree {
    HeroNode root;

    public BinaryTree(HeroNode root) {
        this.root = root;
    }

    //按照编号删除节点（简单删除），如果是叶子节点则删除，如果是非叶子结点，则删除子树
    public void delete(int no) {
        if (this.root != null) {
            //如果删除的节点就是root节点，则root = null
            if (this.root.no == no) {
                this.root = null;
            } else {
                this.root.delete(no);
            }
        } else {
            System.out.println("空树无法删除~");
        }
    }

    /**
     * 按照编号删除节点，如果删除的节点是叶子节点直接删除，如果删除的节点是非叶子结点，按照如下规则进行删除
     * 如果该非叶子节点A只有一个子节点B，则子节点B替代节点A
     * 如果该非叶子节点A有左子节点B和右子节点C，则让左子节点B替代节点A。
     *
     * @param no
     */
    public void delete2(int no) {
        if (this.root != null) {
            if (this.root.no == no) {
                if (this.root.left == null || this.root.right == null) {
                    if (this.root.left != null) {//说明根节点只存在左节点
                        this.root = this.root.left;
                    } else if (this.root.right != null) {//说明根节点只存在右节点
                        this.root = this.root.right;
                    } else {//说明根节点是叶子节点
                        this.root = null;
                    }
                } else {//说明根节点存在子树
                    //定义临时变量存储根节点的右子树
                    HeroNode tempRight = this.root.right;
                    this.root = this.root.left;
                    this.root.right = tempRight;
                }
            } else {
                this.root.delete2(no);
            }
        } else {
            System.out.println("空树无法删除~");
        }
    }


    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        }
        return null;
    }

    //中序遍历查找
    public HeroNode midOrderSearch(int no) {
        if (this.root != null) {
            return this.root.midOrderSearch(no);
        }
        return null;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        }
        return null;
    }

    //遍历
    public void list(List<HeroNode> list) {
        if (list == null || list.size() < 0) {
            System.out.println("链表为空，无法遍历~");
            return;
        }
        for (HeroNode heroNode : list) {
            System.out.println(heroNode);
        }
    }

    //从上往下打印二叉树
    public List<HeroNode> buildFromTopToButtom() {
        List<HeroNode> list = new ArrayList<>();
        Queue<HeroNode> queue = new LinkedList<>();
        if (this.root == null) {
            return list;
        }
        //根放入队列
        queue.offer(this.root);
        while (!queue.isEmpty()) {
            HeroNode tmp = queue.poll();
            list.add(tmp);
            if (tmp.left != null) {
                queue.offer(tmp.left);
            }
            if (tmp.right != null) {
                queue.offer(tmp.right);
            }
        }
        return list;
    }

    //前序遍历（非递归）
    public List<HeroNode> preOrderNoRecursion() {
        List<HeroNode> list = new ArrayList<>();
        Stack<HeroNode> stack = new Stack<>();
        if (this.root == null) {
            return list;
        }
        stack.push(this.root);
        while (!stack.isEmpty()) {
            HeroNode tmp = stack.pop();
            list.add(tmp);
            if (tmp.right != null) {
                stack.push(tmp.right);
            }
            if (tmp.left != null) {
                stack.push(tmp.left);
            }
        }
        return list;
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("根节点为空，无法遍历~");
        }
    }

    //中序遍历（非递归）
    public List<HeroNode> midOrderNoRecursion() {
        List<HeroNode> list = new ArrayList<>();
        Stack<HeroNode> stack = new Stack<>();
        if (this.root == null) {
            return list;
        }
        HeroNode tmp = this.root;
        while (tmp != null || !stack.isEmpty()) {
            if (tmp != null) {
                stack.push(tmp);
                tmp = tmp.left;
            } else {
                tmp = stack.pop();
                list.add(tmp);
                tmp = tmp.right;
            }
        }
        return list;
    }

    //中序遍历
    public void midOrder() {
        if (this.root != null) {
            this.root.midOrder();
        } else {
            System.out.println("根节点为空，无法遍历~");
        }
    }

    //后序遍历（非递归）
    public List<HeroNode> postOrderNoRecursion() {
        LinkedList<HeroNode> list = new LinkedList<>();
        Stack<HeroNode> stack = new Stack<>();
        if (this.root == null) {
            return list;
        }
        stack.push(this.root);
        while (!stack.isEmpty()) {
            HeroNode tmp = stack.pop();
            list.addFirst(tmp);
            if (tmp.left != null) {
                stack.push(tmp.left);
            }
            if (tmp.right != null) {
                stack.push(tmp.right);
            }
        }
        return list;
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("根节点为空，无法遍历~");
        }
    }
}

//定义HeroNode
class HeroNode {
    int no;
    String name;
    HeroNode left;
    HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    /**
     * 按照编号删除节点（简单删除版）
     * 如果删除的节点是叶子节点，则删除该节点
     * 如果删除的节点是非叶子节点，则删除该子树
     * <p>
     * 思路：
     * 1.因为是单向的，要删除节点必须要找到它的父节点才能删除
     * 2.如果当前节点的左节点不为空，且当前节点的左节点no等于要删除的no，则this.left = null
     * 3.如果当前节点的右节点不为空，且当前节点的右节点no等于要删除的no，则this.right = null
     * 4.如果第三步和第四步都未找到，则向当前节点左递归删除，找到就删除
     * 5.如果当前节点左递归完毕后，未找到，则向当前节点右递归删除
     *
     * @param no
     */
    public void delete(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delete(no);
        }
        if (this.right != null) {
            this.right.delete(no);
        }
    }

    /**
     * 按照编号删除节点，如果删除的节点是叶子节点直接删除，如果删除的节点是非叶子结点，按照如下规则进行删除
     * 如果该非叶子节点A只有一个子节点B，则子节点B替代节点A
     * 如果该非叶子节点A有左子节点B和右子节点C，则让左子节点B替代节点A。
     *
     * @param no
     */
    public void delete2(int no) {
        if (this.left != null && this.left.no == no) {
            if (this.left.left == null || this.left.right == null) {
                if (this.left.left != null) {//说明要删除的节点只有一个左节点
                    //则左节点B替换节点A
                    this.left = this.left.left;
                    return;
                } else if (this.left.right != null) {//说明要删除的节点只有一个右节点
                    //则右节点B替换节点A
                    this.left = this.left.right;
                    return;
                } else {//说明要删除的节点是叶子节点
                    this.left = null;
                    return;
                }
            } else {//说明要删除的节点是非叶子结点
                //说明非叶子节点A有左子节点B和右子节点C，则让左子节点B替代节点A
                //临时保存要删除节点的右节点
                HeroNode tempRight = this.left.right;
                this.left = this.left.left;
                this.left.right = tempRight;
                return;
            }
        }
        if (this.right != null && this.right.no == no) {
            if (this.right.left == null || this.right.right == null) {
                if (this.right.left != null) {//说明要删除的节点只有一个左节点
                    //则左节点B替换节点A
                    this.right = this.right.left;
                    return;
                } else if (this.right.right != null) {//说明要删除的节点只有一个右节点
                    //则右节点B替换节点A
                    this.right = this.right.right;
                    return;
                } else {//说明要删除的是叶子节点
                    this.right = null;
                    return;
                }
            } else {//说明要删除的节点是非叶子结点
                //临时保存要是删除节点的右节点
                HeroNode tempRight = this.right.right;
                this.right = this.right.left;
                this.right.right = tempRight;
                return;
            }
        }
        if (this.left != null) {
            this.left.delete(no);
        }
        if (this.right != null) {
            this.right.delete(no);
        }
    }

    /**
     * 前序遍历查找思路：
     * 1.如果当前节点的no是否等于要查找的no，如果等于直接返回
     * 2.如果不等于，则判断当前节点的左节点是否为空，如果不为空，则左递归前序查找
     * 3.如果左递归前序查找，找到该节点，则返回，否则继续判断当前节点的右节点是否为空，如果不为空，则递归前序查找
     *
     * @param no 编号
     * @return
     */
    public HeroNode preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }
        HeroNode resultNode = null;
        if (this.left != null) {
            resultNode = this.left.preOrderSearch(no);
        }
        if (resultNode != null) {
            return resultNode;
        }
        if (this.right != null) {
            resultNode = this.right.preOrderSearch(no);
        }
        return resultNode;
    }

    /**
     * 中序遍历查找思路：
     * 1.如果当前节点的左节点不为空，则左递归中序遍历查找，如果找到则返回
     * 2.如果左递归未找到，则判断当前节点no是否等于查找的no，如果等于则返回
     * 3.如果不等于，则判断当前节点右节点是否为空，如果不为空，则向右递归中序遍历查找
     *
     * @param no
     * @return
     */
    public HeroNode midOrderSearch(int no) {
        HeroNode resultNode = null;
        if (this.left != null) {
            resultNode = this.left.midOrderSearch(no);
        }
        if (resultNode != null) {
            return resultNode;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resultNode = this.right.midOrderSearch(no);
        }
        return resultNode;
    }

    /**
     * 后序查找思路：
     * 1.如果当前节点的左节点不为空，则向左递归后序查找，如果找到则返回
     * 2.如果左递归未找到，则判断当前节点的右节点，如果不为空，则向右递归后序查找，如果找到则返回
     * 3.如果右递归未找到，则判断当前节点no是否等于要查找的no，如果是则返回，如果不是则返回null
     *
     * @param no
     * @return
     */
    public HeroNode postOrderSearch(int no) {
        HeroNode resultNode = null;
        if (this.left != null) {
            resultNode = this.left.postOrderSearch(no);
        }
        if (resultNode != null) {
            return resultNode;
        }
        if (this.right != null) {
            resultNode = this.right.postOrderSearch(no);
        }
        if (resultNode != null) {
            return resultNode;
        }
        if (this.no == no) {
            return this;
        }
        return resultNode;
    }

    /**
     * 前序遍历思路：
     * 1.输出当前节点
     * 2.如果当前节点的左节点不为空，则继续前序遍历。
     * 3.如果当前节点的右节点不为空，则继续前序遍历。
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历思路：
     * 1.如果当前节点的左节点不为空，则继续中序遍历。
     * 2.输出当前节点
     * 3.如果当前节点的右节点不为空，则继续中序遍历。
     */
    public void midOrder() {
        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    /**
     * 后序遍历思路：
     * 1.如果当前节点的左节点不为空，则继续后序遍历。
     * 2.如果当前节点的右节点不为空，则继续后序遍历。
     * 3.输出当前节点
     */
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name=" + name +
                '}';
    }
}
