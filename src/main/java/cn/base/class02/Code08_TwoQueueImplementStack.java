package cn.base.class02;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Mark
 * @date 2021/6/28 下午3:30
 */
public class Code08_TwoQueueImplementStack {

    // 用队列结构实现栈结构
    public static class MyStack<T> {
        private Queue<T> queue;
        private Queue<T> help;

        public MyStack() {
            this.queue = new LinkedList<>();
            this.help = new LinkedList<>();
        }

        // 入栈
        public void push(T val) {
            queue.offer(val);
        }

        // 出栈
        public T pop() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T ans = queue.poll();
            Queue<T> tmp = queue;
            queue = help;
            help = tmp;
            return ans;
        }

        public T peek() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T ans = queue.poll();
            help.offer(ans);
            Queue<T> tmp = queue;
            queue = help;
            help = tmp;
            return ans;
        }

    }

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
    }
}
