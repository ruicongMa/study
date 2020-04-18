package cn.mark.search;

/**
 * @author Mark
 * @date 2020/4/6 16:27
 */
public class Exec_01 {

    public static void main(String[] args) {
        System.out.println(pow(2, 13));
    }

    public static int pow(int x, int n) {
        int res = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                res *= x;
            }
            n = n >> 1;
            x = x * x;
        }
        return res;
    }
}
