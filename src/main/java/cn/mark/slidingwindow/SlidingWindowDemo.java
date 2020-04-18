package cn.mark.slidingwindow;

/**
 * https://www.zhihu.com/question/314669016
 * 滑动窗口算法可以用已解决数组/字符串的子元素问题，它可以将嵌套的循环问题，转换为单循环问题，降低时间复杂度。
 *
 * @author Mark
 * @create 2020/3/8
 */
public class SlidingWindowDemo {

    public static void main(String[] args) {
        /**
         *
         * 给定一个整数数组，计算长度为 'k' 的连续子数组的最大总和。
         * int[] arr = {100,200,300,400};
         * k=2
         * 输出 300 + 400 = 700
         */
        int[] arr = {100, 200, 300, 400};
        int k = 2;
        maxSum(arr, k);
        maxSum2(arr, k);
    }

    /**
     * 滑动窗口算法
     * 滑动窗口算法可以将嵌套的循环问题，转换为单循环问题，降低时间复杂度。
     *
     * @param arr
     * @param k
     */
    public static void maxSum2(int[] arr, int k) {
        /**
         * 根据示例：当k等于2时，我们维护一个长度为2的窗口。
         * 窗口内的值的和保存在一个变量中；
         * 通过不断的往右滑动来算出当前窗口的值，并与保存的最大值作比较；
         * 当窗口滑动到最右边的时候终止滑动；
         */
        //计算出第一个窗口的值
        int maxSum = 0;
        for (int i = 0; i < k; i++) {
            maxSum += arr[i];
        }
        int sum = maxSum;
        for (int i = k; i < arr.length; i++) {
            //新窗口的和 = 前一个窗口的和 + 当前窗口的值 - 移除窗口的值
            sum += arr[i] - arr[i - k];
            maxSum = Math.max(sum, maxSum);
        }
        System.out.println(maxSum);
    }

    /**
     * 暴力法
     *
     * @param arr
     * @param k
     */
    public static void maxSum(int[] arr, int k) {
        //需要循环arr.length - k + 1次
        int max = 0;
        for (int i = 0; i < arr.length - k + 1; i++) {
            int currentMaxVal = 0;
            for (int j = 0; j < k; j++) {
                currentMaxVal += arr[i + j];
            }
            max = Math.max(max, currentMaxVal);
        }
        System.out.println(max);
    }

}
