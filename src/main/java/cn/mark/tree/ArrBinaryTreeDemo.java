package cn.mark.tree;

/**
 * 顺序存储二叉树：
 * 从数据存储来看，数组存储方式和树的存储方式可以相互转换，即数组可以转换成树，树也可以转换成数组
 * <p>
 * 顺序存储二叉树的特点:
 * 1.顺序二叉树通常只考虑完全二叉树
 * 2.第n个元素的左子节点为  2 * n + 1
 * 3.第n个元素的右子节点为  2 * n + 2
 * 4.第n个元素的父节点为  (n-1) / 2
 * 5.n : 表示二叉树中的第几个元素(按0开始编号)
 *
 * @author Mark
 * @create 2020/3/1
 */
public class ArrBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        //前序遍历顺序存储二叉树
        // arrBinaryTree.preOrder();
        //中序遍历顺序存储二叉树
        // arrBinaryTree.midOrder();
        //后序遍历顺序存储二叉树
        arrBinaryTree.postOrder();
    }
}

//定义顺序存储二叉树
class ArrBinaryTree {
    int[] arr;//二叉树数据

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        this.preOrder(0);
    }

    /**
     * 前序遍历顺序存储二叉树
     *
     * @param n 第n个元素(n从0开始，对应数组的下标)
     */
    public void preOrder(int n) {
        if (arr == null || arr.length == 0) {
            System.out.println("顺序存储二叉树为空，无法遍历~");
            return;
        }
        //输出当前节点
        System.out.println(arr[n]);
        //向左递归前序遍历
        if ((2 * n + 1) < arr.length) {
            preOrder(2 * n + 1);
        }
        //向右递归前序遍历
        if ((2 * n + 2) < arr.length) {
            preOrder(2 * n + 2);
        }
    }

    public void midOrder() {
        this.midOrder(0);
    }

    /**
     * 中序遍历顺序存储二叉树
     *
     * @param n 第n个元素(n从0开始，对应数组的下标)
     */
    public void midOrder(int n) {
        if (arr == null || arr.length == 0) {
            System.out.println("顺序存储二叉树为空，无法遍历~");
            return;
        }
        //向左递归中序遍历
        if ((2 * n + 1) < arr.length) {
            midOrder(2 * n + 1);
        }
        //输出当前节点
        System.out.println(arr[n]);
        //向右递归中序遍历
        if ((2 * n + 2) < arr.length) {
            midOrder(2 * n + 2);
        }
    }

    public void postOrder() {
        this.postOrder(0);
    }

    /**
     * 后序遍历顺序存储二叉树
     *
     * @param n 第n个元素(n从0开始，对应数组的下标)
     */
    public void postOrder(int n) {
        if (arr == null || arr.length == 0) {
            System.out.println("顺序存储二叉树为空，无法遍历~");
            return;
        }
        //向左递归中序遍历
        if ((2 * n + 1) < arr.length) {
            postOrder(2 * n + 1);
        }
        //向右递归中序遍历
        if ((2 * n + 2) < arr.length) {
            postOrder(2 * n + 2);
        }
        //输出当前节点
        System.out.println(arr[n]);
    }
}
