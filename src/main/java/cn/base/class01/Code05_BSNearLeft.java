package cn.base.class01;

import java.util.Arrays;

/**
 * @author Mark
 * @date 2021/6/26 下午10:27
 */
public class Code05_BSNearLeft {

    // 在一个有序数组中，找>=某个数最左侧的位置
    public static int nearestIndex(int[] arr, int num) {
        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= num) {
                R = mid - 1;
                index = mid;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        int testTimes = 100000;
        int maxLength = 100;
        int maxValue = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArr(maxLength, maxValue);
            Arrays.sort(arr);
            int num = (int) (Math.random() * maxValue) + 1;
            if (nearestIndex(arr, num) != test(arr, num)) {
                System.out.println("fuck !!!");
                break;
            }
        }
        System.out.println("test end");
    }

    public static int test(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= num) {
                return i;
            }
        }
        return -1;
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
}
