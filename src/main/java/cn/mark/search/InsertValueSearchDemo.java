package cn.mark.search;


/**
 * 插值查找思路：
 * 1.插值查找算法类似于二分查找，不同的是插值查找每次从自适应mid处开始查找
 * 2.int mid = left + (right – left) * (findVal – arr[left]) / (arr[right] – arr[left])
 *
 * @author Mark
 * @create 2020/2/28
 */
public class InsertValueSearchDemo {

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println(insertValueSearch(arr, 0, arr.length - 1, 100));
    }

    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        //如果左边或右边查找完毕或者查找的值小于数组中最小值或者大于数组中最大值
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        if (findVal > arr[mid]) {//向右递归查找
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < arr[mid]) {//向左递归查找
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
