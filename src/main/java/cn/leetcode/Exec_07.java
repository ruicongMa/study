package cn.leetcode;

/**
 * 整数反转
 *
 * @author Mark
 * @date 2020/4/18 20:39
 */
public class Exec_07 {

    public static void main(String[] args) {
        // 123->321
        System.out.println(integerReverse(-123));
    }

    public static int integerReverse(int x) {
        long rest = 0;
        while (x != 0) {
            rest = rest * 10 + x % 10;
            x /= 10;
            if (rest < Integer.MIN_VALUE || rest > Integer.MAX_VALUE) {
                return 0;
            }
        }
        return (int) rest;
    }
}
