package cn.primary.class01;

/**
 * 打印某个数的二进制
 *
 * @author Mark
 * @date 2021/6/13 14:46
 */
public class Code01_PrintB {

    public static void main(String[] args) {
        print(-5);
        // print(~-5 + 1);
        // System.out.println(Integer.MIN_VALUE);
        // print(Integer.MIN_VALUE);
        // System.out.println(Integer.MAX_VALUE);
        // print(Integer.MAX_VALUE);
    }

    private static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }
}
