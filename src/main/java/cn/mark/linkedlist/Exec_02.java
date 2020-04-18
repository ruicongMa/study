package cn.mark.linkedlist;

import java.util.Stack;

/**
 * 反转题
 *
 * @author Mark
 * @date 2020/4/7 19:13
 */
public class Exec_02 {

    public static void main(String[] args) {
        String str = "((()))";
        System.out.println(isValid(str));
        System.out.println(isValid2(str));
        System.out.println(longestValidParentheses(")()())"));
    }

    public static int longestValidParentheses(String s) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                //下标入栈
                stack.push(i);
            } else {
                //出栈
                stack.pop();
                //看栈顶是否为空，为空的话就不能作差了
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

    /**
     * 时间复杂度为O(n)、空间复杂度为O(1)
     *
     * @param s
     * @return
     */
    public static boolean isValid2(String s) {
        if (s == null || s.length() < 1) {
            return true;
        }
        int n = s.length();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                sum++;
            } else {
                if (sum == 0) {
                    return false;
                } else {
                    sum--;
                }
            }
        }
        return sum == 0 ? true : false;
    }

    /**
     * 时间复杂度为O(n)、空间复杂度为O(n)
     *
     * @param str
     * @return
     */
    public static boolean isValid(String str) {
        if (str == null || str.length() < 1) {
            return true;
        }
        int n = str.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            char c = str.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }

}
