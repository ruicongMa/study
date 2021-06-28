package cn.base.class01;

/**
 * @author Mark
 * @date 2021/6/26 下午11:41
 */
public class Code09_EvenTimesAndOddTimes {

    // 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    // 求一个数的二进制位上有多少个1
    public static int bit1Count(int num) {
        int count = 0;
        while (num != 0) {
            int rightOne = num & -num; // num & ((~num) + 1)
            count++;
            num ^= rightOne;// 每次去掉最右侧的1
        }
        return count;
    }

    // 怎么把一个int类型的数，提取出最右侧的1来
    public static void rightOne(int N) {
        System.out.println(N & ((~N) + 1));
    }

    // 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
    // arr中，有两种数，出现奇数次
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        // eor = a^b
        // eor != 0
        // eor某位置上必然存在1，把最右侧的1提取出来
        int rightOne = eor & (-eor);
        int onlyOne = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0) {
                onlyOne ^= arr[i];
            }
        }
        System.out.println(onlyOne + " " + (onlyOne ^ eor));
    }

    public static void main(String[] args) {
        // int[] arr1 = {3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1};
        // printOddTimesNum1(arr1);
        //
        // System.out.println(6 & -6);

        // int a = 8;
        // // 求 a的相反数 -8;
        // // 相反数为取反 + 1
        // int b = ~8 + 1;
        // System.out.println(b);
        // System.out.println(a & b);
        // System.out.println(11 ^ -11);
        // int num = 15;
        // System.out.println(bit1Count(num));

        int a = 11;
        int b = 3;
        // 1011
        // 0011
        // 1000 = 8
        System.out.println(a ^ b);
    }
}
