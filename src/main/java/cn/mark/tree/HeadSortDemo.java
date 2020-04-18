package cn.mark.tree;

import java.util.Arrays;

/**
 * 堆排序介绍：
 * <p>
 * 堆排序是利用堆这种数据结构而设计的一种排序算法，堆排序是一种选择排序，它的最坏，最好，平均时间复杂度均为O(nlogn)，它也是不稳定排序。
 * 堆是具有以下性质的完全二叉树：每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆, 注意 : 没有要求结点的左孩子的值和右孩子的值的大小关系。
 * 每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆
 * <p>
 * 堆排序的思路：
 * 1.将待排序序列构造成一个大顶堆
 * 2.此时，整个序列的最大值就是堆顶的根节点。
 * 3.将其与末尾元素进行交换，此时末尾就为最大值。
 * 4.然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了。
 * <p>
 * 可以看到在构建大顶堆的过程中，元素的个数逐渐减少，最后就得到一个有序序列了.
 *
 * @author Mark
 * @create 2020/3/2
 */
public class HeadSortDemo {

    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
        headSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void headSort(int[] arr) {
        //将无序数组构成一个大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            headAdjust(arr, i, arr.length);
        }
        //逐步将每个最大值的根节点与末尾元素交换，并且再调整二叉树，使其成为大顶堆
        for (int j = arr.length - 1; j >= 0; j--) {
            //将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端
            int temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            //交换之后，重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素
            headAdjust(arr, 0, j);
        }
    }

    /**
     * 堆调整的过程，采用大顶堆
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整，length在逐步减少
     */
    public static void headAdjust(int[] arr, int i, int length) {
        //先取出当前元素在数组中的值
        int temp = arr[i];
        //k=2*i+1从索引为i的非叶子节点的左节点开始
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            //如果左节点的值小于右节点的值，则k指向右节点的索引
            if ((k + 1) < length && arr[k] < arr[k + 1]) {
                k++;//指向右节点的索引
            }
            //如果子节点的值（左节点或者右节点）大于父节点的值，则把较大的值赋值给当前节点
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;//继续循环比较
            } else {//符合大顶堆特点，则不需要调整
                break;
            }
        }
        arr[i] = temp;//将temp放入到调整后的位置
    }

}
