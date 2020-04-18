package cn.mark.sort;

import java.util.Arrays;

/**
 * 希尔排序是对插入排序进一步优化（当插入排序出现插入的数是较小情况时，后移的次数明显增多，对效率有影响）
 * 例如：
 * 数组 arr = {2,3,4,5,6,1} 这时需要插入的数 1(最小), 这样的过程是：
 * {2,3,4,5,6,6}
 * {2,3,4,5,5,6}
 * {2,3,4,4,5,6}
 * {2,3,3,4,5,6}
 * {2,2,3,4,5,6}
 * {1,2,3,4,5,6}
 * <p>
 * 希尔排序思路：
 * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
 * 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止
 *
 * @author Mark
 * @create 2020/2/26
 */
public class ShellSortDemo {

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        // shellSortBySwap(arr);
        shellSortByInsert(arr);

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 采用移位法进行希尔排序
     * 找到合适位置才进行交换
     *
     * @param arr
     */
    public static void shellSortByInsert(int[] arr) {
        // int count = 0;
        //外层循环控制需要几次分组
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
            // System.out.println("希尔排序第" + (++count) + "轮 =" + Arrays.toString(arr));
        }
    }

    /**
     * 采用交换位置进行希尔排序
     * 缺点：交换次数多
     *
     * @param arr
     */
    public static void shellSortBySwap(int[] arr) {
        // int count = 0;
        //外层循环控制需要几次分组
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //每次分组后进行希尔排序
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        int temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            // System.out.println("希尔排序第" + (++count) + "轮 =" + Arrays.toString(arr));
        }
    }

}
