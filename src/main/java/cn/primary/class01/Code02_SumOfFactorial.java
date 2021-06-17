package cn.primary.class01;

/**
 * @author Mark
 * @date 2021/6/13 16:07
 */
public class Code02_SumOfFactorial {

    public static void main(String[] args) {
        System.out.println(f1(2));
        System.out.println(f2(2));
    }

    /**
     * 1! + 2! + 3! + ... + N!
     */

    /**
     * 方法一：
     * 1! = 1 * 1
     * 2! = 1 * 2
     * 3! = 1 * 2 * 3
     * 4! = 1 * 2 * 3 * 4
     */
    public static long f1(int n) {
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += factorial(i);
        }
        return ans;
    }

    public static long factorial(int n) {
        long ans = 1;
        for (int i = 1; i <= n; i++) {
            ans *= i;
        }
        return ans;
    }

    /**
     * 方法二：
     * 1! = 1 * 1
     * 2! = 1! * 2
     * 3! = 2! * 3
     */
    public static long f2(int n) {
        long ans = 0;
        long cur = 1;
        for (int i = 1; i <= n; i++) {
            cur *= i;
            ans += cur;
        }
        return ans;
    }
}
