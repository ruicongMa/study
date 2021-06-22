package cn.primary.class05;

import java.util.HashSet;

/**
 * @author Mark
 * @date 2021/6/21 下午2:18
 */
public class Code01_BitMap {

    public static class BitMap {
        private long[] bits;

        public BitMap(int max) {
            // long 占8个字节，共占64位，不存在默认0，存在就为1
            // 0~0 需要1个
            // 0~63 需要1个
            // 0~64 需要2个
            // 所以数组的长度等于 (max + 64) / 64
            this.bits = new long[(max + 64) >> 6];
        }

        // 添加元素，找位置，设置为1
        public void add(int num) {
            // 1.算出num在数组中位置 比如bits[i]  num / 64 -> num >> 6 哪个整数
            // 2.算出num在bits[i]上的64位上在哪个位置上，数组每个元素都是long，一个long占用64位空间  num % 64 -> num & 63
            // num % 64 计算结果落在 0~63，00000000 ~ 01111111，即num & 63计算结果也是落在0~63里
            bits[num >> 6] |= (1L << (num & 63));
        }

        // 删除元素，找位置，设置为0
        public void delete(int num) {
            bits[num >> 6] &= ~(1L << (num & 63));
        }

        // 判断是否存在这个数
        public boolean contains(int num) {
            return (bits[num >> 6] & (1L << (num & 63))) != 0;
        }
    }

    public static void main(String[] args) {
        int a = 170;
        System.out.println(a / 64);
        System.out.println(a % 64);
        System.out.println((170 + 64) / 64);
        long[] bits = new long[3];
        bits[2] = bits[2] | (1L << (a & 63));
        System.out.println(bits[2]);

        System.out.println("测试开始！");
        int max = 10000;
        BitMap bitMap = new BitMap(max);
        HashSet<Integer> set = new HashSet<>();
        int testTime = 10000000;
        for (int i = 0; i < testTime; i++) {
            int num = (int) (Math.random() * (max + 1));
            double decide = Math.random();
            if (decide < 0.333) {
                bitMap.add(num);
                set.add(num);
            } else if (decide < 0.666) {
                bitMap.delete(num);
                set.remove(num);
            } else {
                if (bitMap.contains(num) != set.contains(num)) {
                    System.out.println("Oops!");
                    break;
                }
            }
        }
        for (int num = 0; num <= max; num++) {
            if (bitMap.contains(num) != set.contains(num)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }
}
