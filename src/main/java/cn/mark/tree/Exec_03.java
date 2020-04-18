package cn.mark.tree;

/**
 * 二叉树的镜像
 *
 * @author Mark
 * @date 2020/4/15 9:13
 */
public class Exec_03 {

    public static void main(String[] args) {
        //操作给定的二叉树，将其变换为源二叉树的镜像。
        HeroNode root = new HeroNode(8, String.valueOf(8));
        HeroNode node6 = new HeroNode(6, String.valueOf(6));
        HeroNode node10 = new HeroNode(10, String.valueOf(10));
        HeroNode node5 = new HeroNode(5, String.valueOf(5));
        HeroNode node7 = new HeroNode(7, String.valueOf(7));
        HeroNode node9 = new HeroNode(9, String.valueOf(9));
        HeroNode node11 = new HeroNode(11, String.valueOf(11));
        root.left = node6;
        root.right = node10;
        node6.left = node5;
        node6.right = node7;
        node10.left = node9;
        node10.right = node11;
        BinaryTree binaryTree = new BinaryTree(root);
        //源镜像前序遍历
        binaryTree.preOrder();
        System.out.println("=====================");
        mirror(root);
        binaryTree.preOrder();
    }

    public static void mirror(HeroNode root) {
        if (root == null) {
            return;
        }
        solve(root);
    }

    private static HeroNode solve(HeroNode root) {
        if (root == null) {
            return root;
        }
        //递归先把左右节点镜像化
        HeroNode left = solve(root.left);
        HeroNode right = solve(root.right);
        //对左右子树进行交换
        root.left = right;
        root.right = left;
        return root;
    }
}
