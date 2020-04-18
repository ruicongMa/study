package cn.mark.dynamic;

/**
 * 打家劫舍：
 * 在一条直线上，有n个房屋，每个房屋中有数量不等的财宝，有一个盗贼希望从房屋中盗取财宝，由于房屋中有报警器，如果同时从相邻的两个房屋中盗取
 * 财宝就会触发报警器。问在不触发报警器的前提下，最多获取多少财宝？
 * 例如：[5,2,6,3,1,7] 则选择 5,6,7
 *
 * @author Mark
 * @date 2020/4/4 17:55
 */
public class Exec_04 {

    public static void main(String[] args) {

        /**
         * 子问题：
         * 如果偷第三个房间，则意味着第二个房间不偷，也就是第三个房间的宝藏数 + 第一个房间的宝藏数
         * 如果不偷第三个房间，则宝藏数量等于第一个房间的宝藏数或者第二个房间的宝藏数
         */

        /**
         * 确认状态：
         * int[] nums; //记录各个房间的宝藏数
         * int[] flags = new int[n]; //记录各个房间有没有被偷，若flag=0 则没偷，flag=1 则偷了。
         * int[] dp = new int[n]; //dp[i]表示前i个房间偷到的最大宝藏数
         */

        /**
         * 初始状态：
         * 第一个房间：
         * condition1：dp[0] = nums[0]; flags[0] = 1;
         * condition2：dp[0] = 0; flags[0] = 0;
         *
         * 第二个房间：
         * condition1：when flags[1] = 1; dp[1] = nums[1];
         * condition2：when flags[1] = 0; dp[1] = dp[0];
         * 选condition1 还是 condition2 呢？ 比较两种情况下dp[i]的大小，推广到前i个房间：（i>=2）
         * when flags[i]=1, dp[i] = nums[i] + dp[i-2]
         * when flags[i]=0, dp[i] = dp[i-1]
         */

        int[] nums = {5, 2, 6, 3, 1, 7};
        System.out.println(rob(nums));
    }

    public static int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        if (nums.length < 2) {
            return nums[0];
        }
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }


}
