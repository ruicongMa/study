package cn.base.class02;

/**
 * @author Mark
 * @date 2021/6/28 下午4:04
 */
public class Code09_GetMax {

    // 求数组arr[L...R]中的最大值，怎么用递归方法实现。
    public static int getMax(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);
        return Math.max(getMax(arr, L, mid), getMax(arr, mid + 1, R));
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 2, 8, 7};
        System.out.println(getMax(arr, 0, arr.length - 1));
    }
}
