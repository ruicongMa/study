package cn.mark.search;

/**
 * @author Mark
 * @date 2020/4/7 9:03
 */
public class Exec_03 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 6, 9};
        System.out.println(binarySearch_demo01(arr, 8));
        System.out.println(binarySearch_demo02(arr, 6));
        System.out.println(binarySearch_demo03(arr, 6));
    }

    /**
     * 查找第一个大于目标值的数
     *
     * @param arr
     * @param target
     * @return
     */
    public static int binarySearch_demo03(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    /**
     * 查找最后一个小于目标值的数
     *
     * @param arr
     * @param target
     * @return
     */
    public static int binarySearch_demo02(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right - 1;
    }

    /**
     * 查找第一个不小于目标值的数（大于等于目标数的值）
     *
     * @param arr
     * @param target
     * @return
     */
    public static int binarySearch_demo01(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
