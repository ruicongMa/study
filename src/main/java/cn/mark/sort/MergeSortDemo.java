package cn.mark.sort;

import java.util.Arrays;

/**
 * 归并排序思路：
 * 归并排序（MERGE-SORT）是利用归并的思想实现的排序方法，
 * 该算法采用经典的分治（divide-and-conquer）策略（分治法将问题分(divide)成一些小的问题然后递归求解，
 * 而治(conquer)的阶段则将分的阶段得到的各答案"修补"在一起，即分而治之)。
 *
 * @author Mark
 * @create 2020/2/27
 */
public class MergeSortDemo {

    public static void main(String[] args) {
        // int arr[] = {0, 8, 4, 5, 7, 1, 3, 6, 2, -1, -10};
        int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};
        int temp[] = new int[arr.length]; //归并排序需要一个额外空间
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 分解+合并
     *
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归分解
            mergeSort(arr, left, mid, temp);
            //向右递归分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并
     *
     * @param arr   原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边有序序列的初始索引
     * @param temp  临时数组，用于中转数据
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //初始化i，左边有序序列的初始索引
        int i = left;
        //初始化j，右边有序序列的初始索引
        int j = mid + 1;
        //初始化temp索引
        int t = 0;
        //第一步把左右两边有序序列中的数据按照规则填充到temp中，直到左右两边中的数据有一边处理完毕
        while (i <= mid && j <= right) {
            //如果左边有序列表中的数据小于等于右边有序列表中的数据，则把左边有序列表中的数据填充到temp中，并记录temp的索引
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                i += 1;
                t += 1;
            } else {
                //反之，则把右边有序列表中的数据填充到temp中，并记录temp的索引
                temp[t] = arr[j];
                j += 1;
                t += 1;
            }
        }
        //第二步把剩余一边的有序列表中的数据依次填充到temp中
        while (i <= mid) {
            temp[t] = arr[i];
            i += 1;
            t += 1;
        }
        while (j <= right) {
            temp[t] = arr[j];
            j += 1;
            t += 1;
        }
        //第三步将temp中的值拷贝到arr中，注意并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }

}
