package cn.mark.dynamic;

/**
 * 最大正方形：
 * 在一个由0和1组成的二维矩阵中内，找到只包含1的最大正方形，并返回其面积。
 *
 * @author Mark
 * @date 2020/4/5 12:02
 */
public class Exec_07 {

    public static void main(String[] args) {
        // int[][] matrix = {
        //         {1, 0, 1, 0, 0},
        //         {1, 0, 1, 1, 1},
        //         {1, 1, 1, 1, 1},
        //         {1, 0, 0, 1, 0}
        // };
        int[][] matrix = {
                {1, 1, 1, 0, 0},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0}
        };
        System.out.println(maximalSquare(matrix));
    }

    public static int maximalSquare(int[][] matrix) {
        //如果矩阵长或宽少于1则直接返回0
        if (matrix.length < 1 || matrix[0].length < 1) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        //记录最大边长
        int max = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //把(i,j)作为左上角向右下角搜索
                if (matrix[i][j] == 1) {
                    //此时正方形的边长
                    int sqlen = 1;
                    boolean flag = true;//记录是否遇到0的位置
                    while (sqlen + i < rows && sqlen + j < cols && flag) {
                        for (int k = j; k <= sqlen + j; k++) {
                            if (matrix[i + sqlen][k] == 0) {
                                flag = false;
                                break;
                            }
                        }
                        for (int k = i; k <= sqlen + i; k++) {
                            if (matrix[k][j + sqlen] == 0) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            sqlen++;
                        }
                    }
                    if (max < sqlen) {
                        max = sqlen;
                    }
                }
            }
        }
        return max * max;
    }
}
