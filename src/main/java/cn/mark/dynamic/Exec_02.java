package cn.mark.dynamic;

/**
 * 二维数组的DP
 * 一个机器人位于一个 m * n 网格的左上角，机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角，问总共有多少条不同的路径？
 *
 * @author Mark
 * @date 2020/4/4 16:25
 */
public class Exec_02 {

    public static void main(String[] args) {
        System.out.println(uniquePaths(7, 3));
    }


    /**
     * 时间复杂度：O(m*n)
     *
     * @param m m行
     * @param n n列
     * @return
     */
    public static int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        int[][] dp = new int[m][n];
        //初始化
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        //推导出 dp[m-1][n-1]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
