package cn.mark.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * https://mp.weixin.qq.com/s?__biz=Mzg2NzA4MTkxNQ==&mid=2247485287&amp;idx=1&amp;sn=334ca94218932fdb3bd60684f159a8bd&source=41#wechat_redirect
 * <p>
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法？
 * f(n) = f(n-1) + f(n - 2)
 *
 * @author Mark
 * @create 2020/3/23
 */
public class Exec_01 {

    public static void main(String[] args) {
        System.out.println(jumper01(6));
        System.out.println(jumper02(6));
        System.out.println(jumper03(6));
    }

    /**
     * 采用递归
     * 时间复杂度为指数级
     *
     * @param n n级的台阶
     * @return 总共多少种跳法
     */
    public static int jumper01(int n) {
        if (n <= 2) {
            return n;
        }
        return jumper01(n - 1) + jumper01(n - 2);
    }

    static Map<Integer, Integer> map = new HashMap<>();

    /**
     * 动态规划：下一次的计算结果依赖于上一次的计算结果
     * 我们可以把每次计算的结果保存中一个map容器里，把n作为key,f(n)作为value.
     * 然后每次要递归的时候，先查看一下这个f(n)我们是否已经算过了，如果已经算过了，
     * 我们直接从map容器里取出来返回去就可以了
     *
     * @param n n级的台阶
     * @return 总共多少种跳法
     */
    public static int jumper02(int n) {
        if (n <= 2) {
            return n;
        } else {
            if (map.containsKey(n)) {
                return map.get(n);
            } else {
                int m = jumper02(n - 1) + jumper02(n - 2);
                map.put(n, m);
                return m;
            }
        }
    }

    /**
     * 采用斐波那契数列解决
     *
     * @param n n级的台阶
     * @return 总共多少种跳法
     */
    public static int jumper03(int n) {
        if (n <= 2) {
            return n;
        }
        int f1 = 0;
        int f2 = 1;
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum = f1 + f2;
            f1 = f2;
            f2 = sum;
        }
        return sum;
    }
}
