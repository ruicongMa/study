package cn.mark.sort;

import java.util.Arrays;

/**
 * 时间复杂度为：O(n^2)
 * 比冒泡排序快些
 * 选择排序思路：
 * 1.外层循环需要进行arr.length-1次
 * 2.内层循环找最小值需要进行i+1到arr.length次
 * 3.每次内层循环后，就确定了最小值，然后进行交换
 *
 * @author Mark
 * @create 2020/2/26
 */
public class SelectSortDemo {

    public static void main(String[] args) {

        int[] arr = {9, 2, 3, 5, -1};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择排序
     *
     * @param arr
     */
    public static void selectSort(int arr[]) {
        //外层循环需要确定最小值为arr.lengh-1次
        for (int i = 0; i < arr.length - 1; i++) {
            //假设当前为最小值
            int minIndex = i;
            int minValue = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (minValue > arr[j]) {
                    minIndex = j;
                    minValue = arr[j];
                }
            }
            //当每次内循环后就找到了当前最小值，然后进行交换
            //如果假设的当前最小值i发生了变化才进行交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = minValue;
            }
        }
    }
}
