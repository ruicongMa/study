package cn.mark.tree;

/**
 * 二叉树的子结构
 *
 * @author Mark
 * @date 2020/4/15 9:36
 */
public class Exec_04 {

    public static void main(String[] args) {
        //输入两颗二叉树A，B，判断B是不是A的子结构。（ps:我们约定空树不是任意一个树的子结构）
    }

    public static boolean hasSubTree(HeroNode root1, HeroNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        //判断B是否为A的子结构
        return isSubTree(root1, root2);
    }

    //判断B是否为A的子结构
    private static boolean isSubTree(HeroNode root1, HeroNode root2) {
        if (root1 == null) {
            return false;
        }
        //以root1为root2的根节点，判断子结构是否成立
        if (judge(root1, root2)) {
            return true;
        } else {
            //如果root1作为起点不行，则递归判断左右节点
            return isSubTree(root1.left, root2) || isSubTree(root1.right, root2);
        }
    }

    //以root1为root2的根节点，判断子结构是否成立
    private static boolean judge(HeroNode root1, HeroNode root2) {
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.no == root2.no) {
            return judge(root1.left, root2.left) && judge(root1.right, root2.right);
        }
        return false;
    }
}
