package cn.system.class01;

import java.util.Arrays;

/**
 * 给定一个数组，其中只有一种数出现 k次，其他数出现 m次，m > 1，k < m，要求时间复杂度 O(1)，空间复杂度 O(1)
 *
 * @author Mark
 * @date 2021/8/15 10:11
 */
public class Code01_KM {

    /**
     * 思路：int 占4个字节，共32位。任何一个整数可以用32位长度的数组来表示，数组中每个元素记录每个数在此位置1的个数。
     */
    public static int onlyKTimes(int[] arr, int k, int m) {
        int[] t = new int[32];
        for (int num : arr) {
            // 累加num在每个位置上1的次数
            for (int i = 0; i <= 31; i++) {
                t[i] += ((num >> i) & 1);
            }
        }
        System.out.println(Arrays.toString(t));
        // 2,2,4,4,4,6,6,6
        int ans = 0;
        for (int i = 0; i < t.length; i++) {
            if (t[i] % m != 0) {
                // 说明ans在i位置上存在1，则把1回填到ans在i位置上
                ans |= (1 << i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {-1, 6, 2, 2, 6, 2, 6, -1};
        int k = 2;
        int m = 3;
        System.out.println(onlyKTimes(arr, k, m));
    }
}
