package cn.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Mark
 * @create 2020/3/8
 */
public class Demo {
    public static void main(String[] args) {
        // System.out.println(lengthOfLongestSubstring("abcabcbb"));
        int[] num1 = {3, 5, 8, 9};
        int[] num2 = {1, 2, 7, 10, 11, 12};

        System.out.println(findMedianSortedArrays(num1, num2));
    }

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        for (int i = 0, j = 0; i < n; i++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int MIN_VALUE = 0x80000000;

        int MAX_VALUE = 0x7fffffff;

        int N1 = nums1.length;
        int N2 = nums2.length;
        if (N1 > N2) {// 确保N1是短的部分。
            return findMedianSortedArrays(nums2, nums1);
        }

        if (N1 == 0)
            return ((double) nums2[(N2 - 1) / 2] + (double) nums2[N2 / 2]) / 2;
        int size = N1 + N2;
        int cutL = 0, cutR = N1;
        int cut1 = N1 / 2;
        int cut2 = size / 2 - cut1;

        while (cut1 <= N1) {
            cut1 = (cutR - cutL) / 2 + cutL;
            cut2 = size / 2 - cut1;

            double L1 = (cut1 == 0) ? MIN_VALUE : nums1[cut1 - 1];
            double L2 = (cut2 == 0) ? MIN_VALUE : nums2[cut2 - 1];
            double R1 = (cut1 == N1) ? MAX_VALUE : nums1[cut1];
            double R2 = (cut2 == N2) ? MAX_VALUE : nums2[cut2];
            if (L1 > R2)
                cutR = cut1 - 1;
            else if (L2 > R1)
                cutL = cut1 + 1;
            else {// Otherwise, that's the right cut.
                if (size % 2 == 0) {// 偶数个数的时候
                    L1 = (L1 > L2 ? L1 : L2);
                    R1 = (R1 < R2 ? R1 : R2);
                    return (L1 + R1) / 2;
                } else {
                    R1 = (R1 < R2 ? R1 : R2);
                    return R1;
                }
            }
        }
        return -1;
    }

}
