package cn.base.class01;

import java.util.Arrays;

/**
 * @author Mark
 * @date 2021/6/26 下午9:18
 */
public class Code04_BSExist {

    // 在一个有序数组中，找某个数是否存在
    public static boolean bsExist(int[] arr, int num) {
        if (arr == null) {
            return false;
        }
        int L = 0;
        int R = arr.length - 1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] > num) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return false;
    }

    public static int[] generateRandomArr(int maxLength, int maxValue) {
        int len = (int) (Math.random() * maxLength) + 1;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            int value = (int) (Math.random() * maxValue) + 1;
            arr[i] = value;
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        int testTimes = 100000;
        int maxLength = 100;
        int maxValue = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArr(maxLength, maxValue);
            Arrays.sort(arr);
            boolean isSuccess = true;
            for (int j = 0; j < arr.length; j++) {
                boolean isExist = bsExist(arr, arr[j]);
                if (!isExist) {
                    isSuccess = false;
                    break;
                }
            }
            if (!isSuccess) {
                System.out.println("funk !!!");
                break;
            }
        }
        System.out.println("test end");
    }
}
