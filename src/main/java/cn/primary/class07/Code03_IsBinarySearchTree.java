package cn.primary.class07;

/**
 * @author Mark
 * @date 2021/6/23 下午5:08
 */
// 测试链接：https://leetcode.com/problems/validate-binary-search-tree/
public class Code03_IsBinarySearchTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info {
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static Info process(TreeNode x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int max = x.val;
        int min = x.val;
        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
        }
        boolean isBST = true;
        if (leftInfo != null && !leftInfo.isBST) {
            isBST = false;
        }
        if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        }
        boolean leftMaxLessX = leftInfo == null ? true : leftInfo.max < x.val;
        boolean rightMixMoreX = rightInfo == null ? true : rightInfo.min > x.val;
        if (!(leftMaxLessX && rightMixMoreX)) {
            isBST = false;
        }
        return new Info(isBST, max, min);
    }

    public static void main(String[] args) {
        TreeNode node5 = new TreeNode(5);
        node5.left = new TreeNode(1);
        node5.right = new TreeNode(4);
        node5.right.left = new TreeNode(3);
        node5.right.right = new TreeNode(6);

        Info info = process(node5);
        System.out.println(info.isBST);
    }
}
