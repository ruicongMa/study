package cn.base.class01;

/**
 * @author Mark
 * @date 2021/6/26 下午11:23
 */
public class Test {

    public static void main(String[] args) {
        // 异或运算
        System.out.println(3 ^ 2);
        // 异或等于无进位相加
        // 0^N == N  0异或一个数等于这个数
        System.out.println(0 ^ 100);
        // N^N == 0  相同的数异或等于0
        System.out.println(100 ^ 100);

        /**
         *
         * int a = m
         * int b = n
         *
         * a = a^b      a = m^n
         * b = a^b      b = m^n^n   b = m
         * a = a^b      a = m^n^m   a = n
         *
         * a = n
         * b = m
         *
         * a和b完成了交换
         *
         *
         */
    }
}
