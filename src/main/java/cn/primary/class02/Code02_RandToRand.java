package cn.primary.class02;

/**
 * @author Mark
 * @date 2021/6/16 下午2:39
 */
public class Code02_RandToRand {

    public static void main(String[] args) {
        int testTimes = 10000000;
        int count = 0;
        double x = 0.1;
        for (int i = 0; i < testTimes; i++) {
            if (Math.random() < x) {
                count++;
            }
        }
        System.out.println("x=" + x + "的概率为：" + (double) count / (double) testTimes);

        System.out.println("==================================");

        count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (Math.random() * 8 < 5) {
                count++;
            }
        }
        System.out.println("小于5的概率为：" + (double) count / (double) testTimes);
        System.out.println((double) 5 / (double) 8);

        System.out.println("==================================");

        int K = 9;
        // [0,k) -> [0,8]
        int[] counts = new int[9];
        for (int i = 0; i < testTimes; i++) {
            int ans = (int) (Math.random() * K);// [0,k-1]
            counts[ans]++;
        }
        for (int i = 0; i < K; i++) {
            System.out.println(i + "这个数，出现了 " + counts[i] + " 次");
        }

        System.out.println("==================================");

        count = 0;
        x = 0.8;
        for (int i = 0; i < testTimes; i++) {
            if (xToXPower2() < x) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
        System.out.println(Math.pow(x, 2));

        System.out.println("==================================");

        count = 0;
        x = 0.8;
        for (int i = 0; i < testTimes; i++) {
            if (xToXPower22() < x) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
        System.out.println((double) 1 - Math.pow((double) 1 - x, 2));

        System.out.println("==================================");

        count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (f2() == 1) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);

        System.out.println("==================================");

        counts = new int[8];
        for (int i = 0; i < testTimes; i++) {
            int num = g();
            counts[num]++;
        }
        for (int i = 0; i < 8; i++) {
            System.out.println(i + "这个数，出现了 " + counts[i] + " 次");
        }

    }

    // 返回一个[0,1)的小数
    // 任意的x，x属于[0,1)，【0,x)范围上的数出现概率由原来的x调整成x平方
    public static double xToXPower2() {
        return Math.max(Math.random(), Math.random());
    }

    public static double xToXPower22() {
        return Math.min(Math.random(), Math.random());
    }

    // lib里的，不能改
    public static int f1() {
        return (int) (Math.random() * 5) + 1;
    }

    // 随机机制，只能用f1，
    // 等概率返回0和1
    public static int f2() {
        int ans = 0;
        do {
            ans = f1();
        } while (ans == 3);
        return ans < 3 ? 0 : 1;
    }

    // 得到000 ~ 111 做到等概率 0 ~ 7等概率返回一个
    public static int f3() {
        return (f2() << 2) + (f2() << 1) + f2();
    }

    // 0 ~ 6等概率返回一个
    public static int f4() {
        int ans = 0;
        do {
            ans = f3();
        } while (ans == 7);
        return ans;
    }

    public static int g() {
        return f4() + 1;
    }

    // 你只能知道，x会以固定概率返回0和1，但是x的内容，你看不到！
    public static int x() {
        return Math.random() < 0.84 ? 0 : 1;
    }

    // 等概率返回0和1
    public static int y() {
        int ans = 0;
        do {
            ans = x();
        } while (ans == x());
        return ans;
    }
}
