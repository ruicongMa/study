package cn.base.class01;

import java.util.Arrays;

/**
 * @author Mark
 * @date 2021/6/26 下午8:00
 */
public class Code01_SelectSort {

    public static void main(String[] args) {
        System.out.println("test begin");
        int testTimes = 100000;
        int maxLength = 100;
        int maxValue = 10000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArr(maxLength, maxValue);
            int[] copyArr = copyArr(arr);
            selectSort(arr);
            Arrays.sort(copyArr);
            if (arr.length != copyArr.length) {
                System.out.println("funk you !");
                break;
            }
            boolean isEqual = true;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] != copyArr[j]) {
                    isEqual = false;
                    break;
                }
            }
            if (!isEqual) {
                System.out.println("funk you !!!");
                break;
            }
        }
        System.out.println("test end");
    }

    public static int[] copyArr(int[] arr) {
        int[] copyArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copyArr[i] = arr[i];
        }
        return copyArr;
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

    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int minValueIndex = i;
            for (int j = i; j < N; j++) {
                minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
            }
            swap(arr, i, minValueIndex);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
