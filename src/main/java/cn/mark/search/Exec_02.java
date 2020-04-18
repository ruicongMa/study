package cn.mark.search;

/**
 * 位运算装逼指南
 * https://mp.weixin.qq.com/s?__biz=Mzg2NzA4MTkxNQ==&mid=2247485441&idx=1&sn=2dd6e5e4b34bab5127ef5462bf5a90a6&chksm=ce4043d5f937cac3f0e5b4d0f90e52f4b56eba07f85ae63da41e7ea66d236e5c59cd24cc441b&token=739597699&lang=zh_CN#rd
 *
 * @author Mark
 * @date 2020/4/6 16:42
 */
public class Exec_02 {

    public static void main(String[] args) {
        // test02();
        // test03();
        test04(2, 4);
    }

    /**
     * m的n次方
     */
    public static void test04(int m, int n) {
        int res = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                res *= m;
            }
            n = n >> 1;
            m = m * m;
        }
        System.out.println("res=" + res);
    }

    /**
     * 找出没有重复的数
     * 给你一组整型数据，这些数据中，其中有一个数只出现了一次，其他的数都出现了两次，让你来找出一个数。
     */
    public static void test03() {
        /**
         * 出现两次的数异或之后会变成0，那个出现一次的数，和0异或之后就等于它本身。
         */
        int[] arr = {1, 2, 3, 4, 5, 1, 2, 3, 4};
        int temp = arr[0];
        for (int i = 1; i < arr.length; i++) {
            temp = temp ^ arr[i];
        }
        System.out.println(temp);
    }

    /**
     * 交换两个数
     */
    public static void test02() {
        /**
         * 定义辅助变量
         */
        int x = 2;
        int y = 3;
        // int temp = x;
        // x = y;
        // y = temp;
        // System.out.println("x=" + x);
        // System.out.println("y=" + y);

        /**
         * 采用异或运算
         * 两个相同的数异或之后结果会等于0，即 n ^ n = 0
         * 任何数与0异或等于它本身，即 n ^ 0 = n
         */
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
        System.out.println("x=" + x);
        System.out.println("y=" + y);
    }

    /**
     * 判断奇偶数
     */
    public static void test01() {
        /**
         * 判断一个数是奇数还是偶数，一般这样判断
         */
        int n = 3;
        if (n % 2 == 1) {
            //n是奇数
        }
        /**
         * 如果把n以二进制的形式展示的话，其实我们只需要判断最后一个二进制位是1还是0就行了，如果是1的话，代表是奇数，如果是0则代表是
         * 偶数
         */
        int temp = n & 1;
        if (temp == 1) {
            System.out.println(n + "是奇数");
        }
    }
}
