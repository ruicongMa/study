package cn.mark.dynamic;

/**
 * 背包问题：采用动态规划算法解决
 *
 * @author Mark
 * @create 2020/3/4
 */
public class KnapsackProblemDemo {

    public static void main(String[] args) {
        //物品的容量
        int[] w = {1, 4, 3};
        //物品的价格
        int[] price = {1500, 3000, 2000};
        //背包的容量
        int m = 4;
        //物品的个数
        int n = 3;

        //创建二维数组v[i][j]表示前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        //记录放入商品的情况，我们定一个二维数组
        int[][] path = new int[n + 1][m + 1];
        //初始化第一行和第一列都为0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;//初始化第一列为0
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;//初始化第一行为0
        }
        //利用公式来进行处理
        for (int i = 1; i < v.length; i++) {//不处理第一行
            for (int j = 1; j < v[0].length; j++) {//不处理第一列
                //如果放入物品的容量大于当前背包的容量，则往背包中放入上一个单元格中物品的价格
                if (w[i - 1] > j) {//因为i从1开始的，所以当前物品的容量是w[i-1]
                    v[i][j] = v[i - 1][j];
                } else {
                    //如果放入的物品的容量小于等于当前背包的容量，则求最大值
                    //最大值：Max{上一个单元格中物品的价格，当前物品的价格+当前背包容量减去当前物品容量后的能够存放的物品的价格}
                    // v[i][j] = Math.max(v[i - 1][j], v[i] + v[i - 1][j - w[i]]);
                    if (v[i - 1][j] < price[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = price[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
        //输出运行结果
        for (int[] row : v) {
            for (int p : row) {
                System.out.print(p + " ");
            }
            System.out.println();
        }

        for (int[] row : path) {
            for (int p : row) {
                System.out.print(p + " ");
            }
            System.out.println();
        }

        int i = path.length - 1; //行的最大下标
        int j = path[0].length - 1;  //列的最大下标
        while (i > 0 && j > 0) { //从path的最后开始找
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1]; //w[i-1]
            }
            i--;
        }
    }
}
