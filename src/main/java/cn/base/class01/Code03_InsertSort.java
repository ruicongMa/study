package cn.base.class01;

import java.util.Arrays;

/**
 * @author Mark
 * @date 2021/6/26 下午8:49
 */
public class Code03_InsertSort {

    // 插入排序，类似打扑克牌

    public static void main(String[] args) {
        System.out.println("test begin");
        int testTimes = 100000;
        int maxLength = 100;
        int maxValue = 10000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArr(maxLength, maxValue);
            int[] copyArr = copyArr(arr);
            insertSort(arr);
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
                System.out.println(Arrays.toString(arr));
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

    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        for (int i = 1; i < N; i++) {
            int insertNumIndex = i;
            while ((insertNumIndex - 1) >= 0 && arr[insertNumIndex] < arr[insertNumIndex - 1]) {
                swap(arr, insertNumIndex, insertNumIndex - 1);
                insertNumIndex--;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
