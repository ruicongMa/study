package cn.mark.kmp;


/**
 * KMP算法解决字符串匹配问题
 *
 * @author Mark
 * @create 2020/3/4
 */
public class KmpDemo {

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        System.out.println(violenceMatch(str1, str2));
        System.out.println(kmpSearch(str1, str2, kmpNext(str2)));
        System.out.println(str1.indexOf(str2));
    }

    /**
     * kmp匹配
     *
     * @param str1 原字符串
     * @param str2 匹配的字符串
     * @param next str2的分配匹配值
     * @return 如果找到，则返回第一次匹配成功的下标索引，如果未找到，则返回-1
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            //kmp算法公式
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            //找到
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 获取一个字符串（字串）的部分匹配值表
     * 部分匹配表值的求解，涉及到前缀、后缀
     * 部分匹配值就是前缀与后缀共同的最大长度的值
     * "AAB" 前缀[A][AA] 后缀[AB][B] 则AAB的部分匹配值就是0
     *
     * @param dest 匹配的字符串
     * @return dest的部分匹配值表
     */
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;//第一个字符部分匹配值肯定为0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //kmp的核心，如果dest.charAt(i) == dest.charAt(j),则j++
            //如果dest.charAt(i) != dest.charAt(j)，就让j = next[j-1]，直到相等为止
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    /**
     * 暴力匹配字符串匹配问题
     * 效率低
     *
     * @param str1
     * @param str2
     * @return 如果匹配成功，则返回下标，如果匹配不成功，则返回-1
     */
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1Length = str1.length();
        int s2Length = str2.length();
        int i = 0;//i索引指向s1
        int j = 0;//j索引指向s2
        while (i < s1Length && j < s2Length) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == s2Length) {
            return i - j;
        } else {
            return -1;
        }
    }
}
