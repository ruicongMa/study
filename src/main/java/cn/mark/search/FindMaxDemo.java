package cn.mark.search;

/**
 * 递归实现数组中的最大值
 *
 * @author Mark
 * @create 2020/2/28
 */
public class FindMaxDemo {

    public static void main(String[] args) {
        int[] arr = {-20, 6, -1, 4, 10};
        System.out.println(findMaxByRecursion(arr, 0));
    }

    /**
     * 递归查找数组中的最大值思路：
     * 1.将数组分为两部分，第一个元素为第一部分，剩余部分为第二部分，返回两个部分的较大者
     * 2.递归终止条件：当第二部分长度等于1，也即使第二部分仅有最后一个元素时，返回该元素
     *
     * @param arr 原始数组
     * @param i   索引
     * @return
     */
    public static int findMaxByRecursion(int[] arr, int i) {
        if (i == arr.length - 1) {
            return arr[i];
        }
        return Math.max(arr[i], findMaxByRecursion(arr, i + 1));
    }

}
