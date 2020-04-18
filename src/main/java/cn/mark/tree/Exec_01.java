package cn.mark.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 二叉树的构建
 *
 * @author Mark
 * @date 2020/4/10 16:29
 */
public class Exec_01 {

    public static void main(String[] args) {
        //给你一个二叉树的前序遍历和中序遍历，你要构建出一个二叉树
        /**
         * 前序遍历：preOrder = [3,9,20,15,7]
         * 中序遍历：midOrder = [9,3,15,20,7]
         */
        int[] preOrder = {3, 9, 20, 15, 7};
        int[] midOrder = {9, 3, 15, 20, 7};
        BinaryTree binaryTree = new BinaryTree(buildTree(preOrder, midOrder));
        //前序遍历
        binaryTree.preOrder();
        System.out.println("================");
        //中序遍历
        binaryTree.midOrder();
    }

    /**
     * 根据前序遍历和中序遍历结果，构建出二叉树
     *
     * @param preOrder 前序遍历结果
     * @param midOrder 后续遍历结果
     * @return
     */
    public static HeroNode buildTree(int[] preOrder, int[] midOrder) {
        //用HashMap存储中序遍历，目的是方便查询。因为我们从前序遍历找到根节点后，还需寻找根节点在中序遍历的哪个位置。
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < midOrder.length; i++) {
            map.put(midOrder[i], i);
        }
        return build(preOrder, map, 0, preOrder.length - 1, 0);
    }

    /**
     * 递归构建子树
     *
     * @param preOrder 前序遍历
     * @param map      中序映射关系
     * @param preStart 前序序列的开始
     * @param preEnd   前序序列的结束
     * @param midStart 中序序列的开始
     * @return
     */
    public static HeroNode build(int[] preOrder, Map<Integer, Integer> map, int preStart, int preEnd, int midStart) {
        //递归边界
        if (preEnd < preStart) {
            return null;
        }
        //前序序列的第一位是根节点
        HeroNode root = new HeroNode(preOrder[preStart], String.valueOf(preOrder[preStart]));
        //从中序序列中，找到根节点的下标
        int rootIndex = map.get(root.no);
        //len代表左子树的节点个数
        int len = rootIndex - midStart;
        //左右子树递归调用
        root.left = build(preOrder, map, preStart + 1, preStart + len, midStart);
        root.right = build(preOrder, map, preStart + len + 1, preEnd, rootIndex + 1);
        return root;
    }

}
