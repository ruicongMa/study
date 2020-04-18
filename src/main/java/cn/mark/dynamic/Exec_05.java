package cn.mark.dynamic;

/**
 * 给定一个数组，求这个数组的连续子数组中，最大的那一段的和。如数组[-2,1,-3,4,-1,2,1,-5,4]
 * 最大的是[4,-1,2,1] = 6
 *
 * @author Mark
 * @date 2020/4/5 10:58
 */
public class Exec_05 {

    public static void main(String[] args) {
        /**
         * 子问题：
         * 只考虑第一个元素，则最大字段和为其本身 dp[0] = nums[0]
         * 考虑前两个元素，最大子段和为 nums[0],nums[1]以及 nums[0] + nums[1] 中最大值 设为 dp[1]
         * 考虑前三个元素，如何求其最大子段和？还是分为两种情况讨论，第三个元素在最后的字串内吗？
         * 若第三个元素也包含在最后的字串内，dp[2] = Max(dp[1]+nums[2],nums[2])
         */

        /**
         * 初始状态：
         * dp[0]=nums[0]
         * dp[1]=max(dp[0]+nums[1],nums[1])
         */

        /**
         * 状态转移方程：
         * dp[i]=max(dp[i-1]+nums[i],nums[i])
         */

        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println(maxSubArray(nums));

    }

    public static int maxSubArray(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int[] dp = new int[len];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }
}
