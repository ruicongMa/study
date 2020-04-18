package cn.mark.tree;

/**
 * 二叉搜索树的后序遍历
 * 输入一个整数数组，判断该数组是不是某个二叉搜索树的后序遍历结果。如果是则输出yes，否则输出no，
 * 假设输入的数组的任意两个数字都互不相同。
 *
 * @author Mark
 * @date 2020/4/15 17:02
 */
public class Exec_05 {

    public static void main(String[] args) {
        //二叉搜索树也叫二叉排序树，即左子树的值比root小，右子树的值比root大
    }

    public static boolean vertifySequenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length < 0) {
            return false;
        }
        return judge(sequence, 0, sequence.length - 1);
    }

    private static boolean judge(int[] sequence, int left, int right) {
        //只有一个节点，递归结束
        if (left >= right) {
            return true;
        }
        //最右边的节点相当于根节点
        int t = sequence[right];
        //用来记录序列中第一个比根节点大的节点的下标
        int index = right;
        for (int i = left; i <= right - 1; i++) {
            //找到根节点的右孩子
            if (sequence[i] > t) {
                index = i;
                i++;
                //如果右子树中有比根节点还小的树的话，显然是不成立的
                while (i <= right - 1) {
                    if (sequence[i] < t) {
                        return false;
                    }
                    i++;
                }
            }
        }
        //递归检查左右子树
        return judge(sequence, left, index - 1) && judge(sequence, index, right - 1);
    }
}
