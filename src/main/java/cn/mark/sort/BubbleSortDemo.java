package cn.mark.sort;

import java.util.Arrays;

/**
 * 时间复杂度为：O(n^2)
 * 冒泡排序思路：
 * 1.需要进行arr.length-1趟
 * 2.每趟需要比较arr.length-1-i次
 * 3.每趟比较完后，就确定一个最大值
 * @author Mark
 * @create 2020/2/25
 */
public class BubbleSortDemo {

    public static void main(String[] args) {
        int[] arr = {-1, -2, 10, 9, 20};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 冒泡排序
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        //外层循环需要进行arr.length-1趟
        int temp = 0;
        boolean flag = false;//标识每趟是否进行过比较
        for (int i = 0; i < arr.length - 1; i++) {
            //内层循环每趟需要进行arr.length-1-i次比较
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }
}
