package cn.mark.sort;

import java.util.Arrays;

/**
 * 时间复杂度为：O(n^2)
 * 插入排序思路：
 * 把n个待排序的元素看成为一个有序表和一个无序表，开始时有序表中只包含一个元素，无序表中包含有n-1个元素，
 * 排序过程中每次从无序表中取出第一个元素，把它的排序码依次与有序表元素的排序码进行比较，
 * 将它插入到有序表中的适当位置，使之成为新的有序表。
 *
 * @author Mark
 * @create 2020/2/26
 */
public class InsertSortDemo {

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, -2, -1};
        // insertSort(arr);
        insertSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertValue = arr[i];
            int insertIndex = i - 1;
            for (; insertIndex >= 0; insertIndex--) {
                if (insertValue > arr[insertIndex]) {
                    break;
                } else {
                    arr[insertIndex + 1] = arr[insertIndex];
                }
            }
            arr[insertIndex + 1] = insertValue;
        }
    }

    /**
     * 插入排序
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        //外层循环控制有多少个元素需要插入
        for (int i = 1; i < arr.length; i++) {
            //需要往有序表中插入的元素
            int insertValue = arr[i];
            //定义当前元素从它的前一个元素位置开始进行比较
            int insertIndex = i - 1;
            //当前插入的值依次和前边的元素比较，如果当前插入的值大于他前边的值或者insertIndex<0，则说明找到要插入的位置了
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //如果当前插入元素的位置就是当前元素的位置，则不需要插入
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertValue;
            }
        }
    }
}
