package cn.mark.sort;

import java.util.Arrays;

/**
 * 当数据量较大时，比如80000000万，需要消耗3.3G内存（80000000 * 11 * 4 / 1024 / 1024 / 1024）
 * 基数排序(桶排序)要理解起来并不困难，不过值得注意的是：基数排序对有负数和0的数列难以进行排序
 * 因此，往往有0和负数的数组一般我们都不用基数来进行排序
 * 基数排序的要点就两个：
 * 分配：按照元素的大小来放入不同的桶子里
 * 回收：将桶子里的元素按桶子顺序重新放到数组中
 * 重复.....两个步骤
 * 基数排序思路：
 * 1.定义10个桶，每个桶定义为数组类型，数组的大小都为数组长度
 * 2.定义每个桶存放数据的有效个数
 * 3.依次获取每个数的个位，即要存放到哪个桶中
 * 4.依次从每个桶中取出存放的数据，即第一轮排序完毕
 * 5.依次获取每个数的百位，依次类推
 *
 * @author Mark
 * @create 2020/2/27
 */
public class RadixSortDemo {

    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {
        //获取数组中最大值并求出最大值的位数（需要几轮排序）
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        //获取最大位为几位即为需要几轮循环
        int maxLength = String.valueOf(max).length();
        //定义一个二维数组表示10个桶，每个桶是一个一维数组，桶的大小为数组的大小
        int[][] buckets = new int[10][arr.length];
        //定义一个一维数组，每个元素表示的是每个桶中存放的数据个数
        int[] bucketElements = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //将数组每个元素中的个位存放到相应的桶中
            for (int j = 0; j < arr.length; j++) {
                //获取个位
                int digitOfElement = arr[j] / n % 10;
                buckets[digitOfElement][bucketElements[digitOfElement]] = arr[j];
                bucketElements[digitOfElement]++;
                // 下边三条语句等价于上边两条语句
                // int elementCount = bucketElements[digitOfElement];
                // buckets[digitOfElement][elementCount] = arr[j];
                // bucketElements[digitOfElement] = elementCount + 1;
            }
            //依次将每个桶中的数据放入到arr中
            int index = 0;
            for (int k = 0; k < bucketElements.length; k++) {
                if (bucketElements[k] != 0) {
                    //说明这个桶里有数据，则依次从这个桶中把数据放入到arr中
                    for (int m = 0; m < bucketElements[k]; m++) {
                        arr[index++] = buckets[k][m];
                    }
                    //每个桶取完数据后需要将桶存放的有效数据清零
                    bucketElements[k] = 0;
                }
            }
        }
    }

}
