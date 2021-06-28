package cn.base.class02;

import java.util.Stack;

/**
 * @author Mark
 * @date 2021/6/28 下午2:59
 */
public class Code07_TwoStackImplementQueue {

    // 用栈结构实现队列结构
    public static class MyQueue {
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;

        public MyQueue() {
            this.stackPush = new Stack<>();
            this.stackPop = new Stack<>();
        }

        // 入队
        public void offer(int val) {
            stackPush.push(val);
            pushToPop();
        }

        // 出队
        public int poll() {
            if (stackPush.isEmpty() && stackPop.isEmpty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.pop();
        }

        // 查看头部
        public int peek() {
            if (stackPush.isEmpty() && stackPop.isEmpty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.peek();
        }

        // push栈向pop栈倒入数据
        public void pushToPop() {
            if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }

    }

    public static void main(String[] args) {
        MyQueue test = new MyQueue();
        test.offer(1);
        test.offer(2);
        test.offer(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }
}
