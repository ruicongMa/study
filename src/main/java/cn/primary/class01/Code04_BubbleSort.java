package cn.primary.class01;

/**
 * @author Mark
 * @date 2021/6/13 17:03
 */
public class Code04_BubbleSort {

    /**
     * 相邻两位，两两互换
     * 0 ~ n-1
     * 0 ~ n-2
     * 0 ~ n-3
     * 0 ~ end
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // 外层循环控制循环多少次
        for (int end = N - 1; end >= 0; end--) {
            // 内存循环控制两两交换
            // 0 1  1 2  2 3 ...
            for (int second = 1; second <= end; second++) {
                if (arr[second - 1] > arr[second]) {
                    swap(arr, second - 1, second);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {10, 5, 8, 2, 1, 4, 9, 3, 2, 0};
        print(arr);
        bubbleSort(arr);
        print(arr);
    }
}
