package cn.mark.recursion;

/**
 * 采用递归[回溯算法]解决八皇后问题
 * 八皇后规则：任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。
 *
 * @author Mark
 * @create 2020/2/25
 */
public class Queue8Demo {

    int max = 8;//最大多少个皇后
    //理论上应该创建一个二维数组来表示棋盘，但是实际上可以通过算法，用一个一维数组即可解决问题.
    // arr[8] = {0 , 4, 7, 5, 2, 6, 1, 3}
    // arr下标表示第几行，即第几个皇后，arr[i] = val , val 表示第i+1个皇后，放在第i+1行的第val+1列
    int[] arr = new int[max];
    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {

        Queue8Demo queue8Demo = new Queue8Demo();
        queue8Demo.check(0);

        System.out.printf("一共有%d解法\n", count);
        System.out.printf("一共判断冲突的次数%d次\n", judgeCount); // 1.5w
    }

    /**
     * 摆放皇后
     * 思路：
     * 1.第一个皇后先放第一行第一列
     * 2.第二个皇后放在第二行第一列、然后判断是否OK， 如果不OK，继续放在第二列、第三列、依次把所有列都放完，找到一个合适
     * 3.继续第三个皇后，还是第一列、第二列……直到第8个皇后也能放在一个不冲突的位置，算是找到了一个正确解
     * 4.当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后，放到第一列的所有正确解，全部得到.
     * 5.然后回头继续第一个皇后放第二列，后面继续循环执行 1,2,3,4的步骤
     *
     * @param n 第n个皇后
     */
    public void check(int n) {
        if (n == max) {
            //说明8个皇后已经摆好了
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            //先将第n个皇后放在第i列
            arr[n] = i;
            //判断是否冲突
            if (judge(n)) {
                //如果不冲突则摆放下一个皇后
                check(n + 1);
            }
            //如果冲突则不需要处理，因为下一次循环就是将该皇后放入到本行的下一个列上进行判断是否冲突
        }

    }

    /**
     * 判断第n个皇后摆放是否冲突
     *
     * @param n 第n个皇后
     * @return true：不冲突，false：冲突
     */
    private boolean judge(int n) {
        judgeCount++;
        //第n个皇后摆放时，需要和之前的摆放的皇后比较是否冲突
        for (int i = 0; i < n; i++) {
            //arr[i] == arr[n] 比较是否在同一列上
            //Math.abs(n - i) == Math.abs(arr[n] - arr[i]) 比较是否在同一斜线上
            //因为i代表的就是皇后，而且i是递增的，所以不可能在同一行上，所以不用比较是否在同一上上
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    //将皇后摆放的位置输出
    private void print() {
        count++;
        for (int item : arr) {
            System.out.print(item + " ");
        }
        System.out.println();
    }


}
