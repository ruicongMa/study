package cn.mark.string;

import java.util.Arrays;

/**
 * @author Mark
 * @create 2020/3/8
 */
public class StringDemo {

    public static void main(String[] args) {
        String str1 = "abcwerthelloyuedefagabc";
        String str2 = "cvhellox";
        System.out.println(getMaxSameStr(str1, str2));
    }

    /**
     * 获取两个字符串中最大相同字串
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String getMaxSameStr(String str1, String str2) {
        String maxStr = str1.length() >= str2.length() ? str1 : str2;
        String minStr = str1.length() < str2.length() ? str1 : str2;
        int length = minStr.length();
        for (int i = 0; i < length; i++) {
            for (int x = 0, y = length - i; y <= length; x++, y++) {
                String subStr = minStr.substring(x, y);
                if (maxStr.contains(subStr)) {
                    return subStr;
                }
            }
        }
        return null;
    }
}
