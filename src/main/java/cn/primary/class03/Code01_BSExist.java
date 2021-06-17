package cn.primary.class03;

import java.util.Arrays;

/**
 * @author Mark
 * @date 2021/6/17 21:46
 */
public class Code01_BSExist {

    // 有序数组
    public static boolean find(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int L = 0;
        int R = arr.length - 1;
        while (L <= R) {
            int middle = (L + R) / 2;
            if (arr[middle] == num) {
                return true;
            } else if (arr[middle] < num) {
                L = middle + 1;
            } else {
                R = middle - 1;
            }
        }
        return false;
    }

    // for test
    public static boolean test(int[] arr, int num) {
        for (int cur : arr) {
            if (cur == num) {
                return true;
            }
        }
        return false;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int len = (int) (Math.random() * maxSize) + 1;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            int ans = (int) (Math.random() * maxValue);
            arr[i] = ans;
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 1000000;
        int maxSize = 100;
        int maxValue = 1000;
        boolean succeed = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int num = (int) (Math.random() * maxValue);
            if (find(arr, num) != test(arr, num)) {
                System.out.println("出错了！");
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
