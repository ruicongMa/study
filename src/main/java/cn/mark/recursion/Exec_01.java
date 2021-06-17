package cn.mark.recursion;

/**
 * 尾递归思想：
 *
 * @author Mark
 * @date 2020/10/26 14:32
 */
public class Exec_01 {

    public static int test(int n) {
        if (n == 1) return 1;
        return n * test(n - 1);
    }

    public static int tailTest(int n, int result) {
        if (n == 1) return result;
        return tailTest(n - 1, n * result);
    }

    public static void main(String[] args) {
        System.out.println(test(5));
        System.out.println(tailTest(5, 1));
    }
}
