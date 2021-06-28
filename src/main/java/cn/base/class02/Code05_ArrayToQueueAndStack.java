package cn.base.class02;


/**
 * @author Mark
 * @date 2021/6/28 上午9:41
 */
public class Code05_ArrayToQueueAndStack {

    // 数组实现队列 先进先出（尾部添加、头部取出）
    public static class MyQueue<T> {
        private Object[] arr;
        private int offerIndex;
        private int pollIndex;
        private int size;
        private final int limit;

        public MyQueue(int limit) {
            this.arr = new Object[limit];
            this.offerIndex = 0;
            this.pollIndex = 0;
            this.size = 0;
            this.limit = limit;
        }

        public boolean isFull() {
            return size == limit;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 入队
        public void offer(Object data) {
            if (isFull()) {
                throw new RuntimeException("队列已满，不能再添加了！");
            }
            size++;
            arr[offerIndex] = data;
            offerIndex = nextIndex(offerIndex);
        }

        // 出队
        public T poll() {
            if (isEmpty()) {
                throw new RuntimeException("队列已空，不能再取出了！");
            }
            size--;
            T ans = (T) arr[pollIndex];
            pollIndex = nextIndex(pollIndex);
            return ans;
        }

        // 如果现在的下标是i，返回下一个位置
        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }
    }

    // 数组实现栈 先进后出
    public static class MyStack<T> {
        private Object[] arr;
        private int index = 0;
        private int size = 8;

        public MyStack(int size) {
            this.size = size;
            this.arr = new Object[size];
        }

        public boolean isEmpty() {
            return index == 0;
        }

        public boolean isFull() {
            return index == size;
        }

        // 添加
        public void push(Object data) {
            if (isFull()) {
                throw new RuntimeException("栈满了，不能再添加了~");
            }
            arr[index++] = data;
        }

        // 弹出
        public T pop() {
            if (isEmpty()) {
                throw new RuntimeException("栈空了，不能再弹出了~");
            }
            return (T) arr[--index];
        }
    }

    public static void main(String[] args) {
        // MyStack<Integer> stack = new MyStack<>(10);
        // for (int i = 1; i <= 10; i++) {
        //     stack.push(i);
        // }
        // while (!stack.isEmpty()) {
        //     System.out.print(stack.pop() + " ");
        // }
        // System.out.println();
        // for (int i = 0; i < 6; i++) {
        //     stack.push(i);
        // }
        // while (!stack.isEmpty()) {
        //     System.out.print(stack.pop() + " ");
        // }
        // System.out.println();

        MyQueue<Integer> queue = new MyQueue<>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        Integer poll = queue.poll();
        System.out.println(poll);

        queue.offer(4);
        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + " ");
        }
        System.out.println();
    }
}
