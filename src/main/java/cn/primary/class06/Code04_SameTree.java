package cn.primary.class06;

/**
 * @author Mark
 * @date 2021/6/22 下午6:29
 */
// 测试链接：https://leetcode.com/problems/same-tree
public class Code04_SameTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        // ^异或：相同为false，不同为true
        if (p == null ^ q == null) {
            return false;
        }
        if (p == null && q == null) {
            return true;
        }
        // p和q都不为空
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
