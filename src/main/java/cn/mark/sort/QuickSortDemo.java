package cn.mark.sort;

import java.util.Arrays;

/**
 * 比希尔排序快些，以空间换时间（递归）
 * 快速排序思路：
 * 是对冒泡排序的一种改进。基本思想是：通过一趟排序将要排序的数据分割成独立的两部分，
 * 其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，
 * 以此达到整个数据变成有序序列
 *
 * @author Mark
 * @create 2020/2/27
 */
public class QuickSortDemo {

    public static void main(String[] args) {
        int[] arr = {9, 78, 0, 23, -567, 70};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 快速排序
     * 以中轴为分割线，如果中轴左边的值比中轴值大，则放到中轴右边，如果中轴右边的值比中轴小，则放到中轴左边，以此类推。
     *
     * @param arr
     * @param left  左下标
     * @param right 右下标
     */
    public static void quickSort(int[] arr, int left, int right) {
        //用l，r分别接收左下标，右下标，使l向右移，r向左移
        int l = left;
        int r = right;
        //通过left和right来确定中轴值
        int pivot = arr[(left + right) / 2];
        while (l < r) {
            //从左边开始找，直到左边的值大于等于中轴值即找到了
            while (arr[l] < pivot) {
                l += 1;
            }
            //从右边开始找，直到右边的值小于等于中轴值即找到了
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果l大于等于r则退出，说明已经交换完毕，即中轴左边的值都小于中轴值，中轴右边的值都大于中轴值
            if (l >= r) {
                break;
            }
            //交换位置
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //如果交换后，arr[l] == pivot 则r向左移一次
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换后，arr[r] == pivot 则l向右移一次
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        // 必须 l+=1 r-=1 否则出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //左边递归排序
        if (left < r) {
            quickSort(arr, left, r);
        }
        //右边递归排序
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
