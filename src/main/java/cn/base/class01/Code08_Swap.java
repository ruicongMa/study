package cn.base.class01;

/**
 * @author Mark
 * @date 2021/6/26 下午11:37
 */
public class Code08_Swap {

    // 如何不用额外变量交换两个数
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a=" + a);
        System.out.println("b=" + b);
    }
}
