package cn.primary.class01;

/**
 * @author Mark
 * @date 2021/6/13 17:39
 */
public class Code04_InsertSort {

    /**
     * 插入排序类似玩扑克牌，每次来的牌插入之前已经排好序的牌中，类似插入一样。
     */
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // 外层循环控制循环多少次
        for (int end = 1; end < N; end++) {
            // 接收要插入的数
            int newNumIndex = end;
            while ((newNumIndex - 1) >= 0 && arr[newNumIndex - 1] > arr[newNumIndex]) {
                swap(arr, newNumIndex - 1, newNumIndex);
                newNumIndex--;
            }
        }
    }

    public static void insertSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        for (int end = 1; end < N; end++) {
            for (int pre = end - 1; pre >= 0 && arr[pre] > arr[pre + 1]; pre--) {
                swap(arr, pre, pre + 1);
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
        insertSort2(arr);
        print(arr);
    }
}
