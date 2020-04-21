package cn.leetcode;

/**
 * 字符串转化整数
 *
 * @author Mark
 * @date 2020/4/18 21:17
 */
public class Exec_08 {

    public static void main(String[] args) {

    }

    public static int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        str = str.trim();
        char firstChar = str.charAt(0);
        int sign = 1;
        int index = 0;
        if (firstChar == '+') {
            sign = 1;
            index++;
        } else if (firstChar == '-') {
            sign = -1;
            index++;
        }
        return 0;
    }
}
