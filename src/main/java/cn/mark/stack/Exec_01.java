package cn.mark.stack;

import java.util.Stack;

/**
 * 设计一个有getMin的栈
 *
 * @author Mark
 * @date 2020/4/9 9:02
 */
public class Exec_01 {

    public static void main(String[] args) {
        GetMinStack getMinStack = new GetMinStack();
        getMinStack.push(2);
        getMinStack.push(1);
        getMinStack.push(3);
        getMinStack.push(0);

        System.out.println(getMinStack.pop());

        System.out.println("getMin=" + getMinStack.getMin());
    }

    static class GetMinStack {
        private Stack<Integer> stack = new Stack<>();
        private int min;

        public void push(int x) {
            if (stack.isEmpty()) {
                min = x;
                stack.push(0);
            } else {
                //计算差值
                int compare = x - min;
                stack.push(compare);
                //如果差值小于0，显然x成为最小值，否则最小值不变
                min = compare < 0 ? x : min;
            }
        }

        public int pop() {
            int top = stack.peek();
            //如果top小于0，显然最小值也一并会被删除，此时更新最小值
            min = top < 0 ? (min - top) : min;
            return stack.pop();
        }

        public int getMin() {
            return min;
        }
    }
}
