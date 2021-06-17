package cn.primary.class01;

/**
 * @author Mark
 * @date 2021/6/13 16:33
 */
public class Code03_SelectSort {

    /**
     * 0 ~ N-1 从第0位到N-1位选择一个最小数，放到第0位上
     * 1 ~ N-1 从第1位到N-1位选择一个最小数，放到第1位上
     * 2 ~ N-1 从第2位到N-1位选择一个最小数，放到第2位上
     */
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // 外层循环控制循环次数
        for (int i = 0; i < N; i++) {
            // 假设每次循环第一个数为最小
            int mixValueIndex = i;
            // 找最小数
            for (int j = i + 1; j < N; j++) {
                mixValueIndex = arr[j] < arr[mixValueIndex] ? j : mixValueIndex;
            }
            // 交换位置
            swap(arr, i, mixValueIndex);
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
        selectSort(arr);
        print(arr);
    }
}
