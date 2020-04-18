package cn.leetcode;

/**
 * https://blog.csdn.net/chen_xinjia/article/details/69258706
 *
 * @author Mark
 * @create 2020/3/11
 */
public class FindMedianSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {3, 5, 8, 9};
        // int[] nums1 = {1,3};
        int[] nums2 = {1, 2, 7, 10, 11, 12};
        // int[] nums2 = {2};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    /**
     * 0	1	2	3	4	5
     * L1  R1
     * 数组1(num1)	3	5 | 8	9		      cut1:左边有几个元素
     * L2  R2
     * 数组2（num2)	1	2	7 | 10	11	12    cut2:左边有几个元素
     * <p>
     * num3        1 2 3 5 7 | 8 9 10 11 12     (7+8)/ 2 = 7.5
     * <p>
     * num3 => num1 + num2 => num1
     * <p>
     * L1<=R2
     * L2<=R1
     * <p>
     * L1>R2 cut1 << (cutL,cut1-1)
     * L2>R1 cut1 >> (cut1+1,cutR)
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int len = nums1.length + nums2.length;
        int cut1 = 0;
        int cut2 = 0;
        int cutL = 0;
        int cutR = nums1.length;
        while (cut1 <= nums1.length) {
            cut1 = (cutR - cutL) / 2 + cutL;
            cut2 = len / 2 - cut1;
            double L1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            double L2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            double R1 = (cut1 == nums1.length) ? Integer.MAX_VALUE : nums1[cut1];
            double R2 = (cut2 == nums2.length) ? Integer.MAX_VALUE : nums2[cut2];
            if (L1 > R2) {
                cutR = cut1 - 1;
            } else if (L2 > R1) {
                cutL = cut1 + 1;
            } else {
                if (len % 2 == 0) {//偶数
                    L1 = L1 > L2 ? L1 : L2;
                    R1 = R1 < R2 ? R1 : R2;
                    return (L1 + R1) / 2;
                } else {//奇数
                    R1 = R1 < R2 ? R1 : R2;
                    return R1;
                }
            }
        }
        return -1;
    }
}
