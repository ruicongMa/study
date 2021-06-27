package cn.base.class02;

/**
 * @author Mark
 * @date 2021/6/27 下午11:40
 */
public class Code04_DoubleLinkedListDeque {

    public static class DoubleNode<T> {
        T val;
        DoubleNode<T> pre;
        DoubleNode<T> next;

        public DoubleNode(T data) {
            this.val = data;
        }
    }

    // 队列
    public static class MyQueue<T> {
        MyDeque<T> deque;

        public MyQueue() {
            this.deque = new MyDeque<>();
        }

        // 添加
        public void offer(T data) {
            deque.offerFromHead(data);
        }

        // 取出
        public T poll() {
            return deque.pollFromTail();
        }
    }

    // 栈
    public static class MyStack<T> {
        MyDeque<T> deque;

        public MyStack() {
            this.deque = new MyDeque<>();
        }

        // 入栈
        public void push(T data) {
            deque.offerFromTail(data);
        }

        // 出栈
        public T pop() {
            return deque.pollFromTail();
        }
    }

    // 双链表实现双端队列（头部既可以添加也可以取出，尾部既可以添加也可以取出）
    public static class MyDeque<T> {
        DoubleNode<T> head;
        DoubleNode<T> tail;
        int size;

        public boolean isEmpty() {
            return size == 0;
        }

        // 头部添加
        public void offerFromHead(T data) {
            DoubleNode cur = new DoubleNode(data);
            if (head == null) {
                head = tail = cur;
            } else {
                cur.next = head;
                head.pre = cur;
                head = cur;
            }
            size++;
        }

        // 头部取出
        public T pollFromHead() {
            T ans = null;
            if (head == null) {
                return ans;
            }
            size--;
            ans = head.val;
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
                head.pre = null;
            }
            return ans;
        }

        // 查看头部
        public T peekHead() {
            return head != null ? head.val : null;
        }

        // 尾部添加
        public void offerFromTail(T data) {
            DoubleNode cur = new DoubleNode(data);
            if (head == null) {
                head = tail = cur;
            } else {
                tail.next = cur;
                cur.pre = tail;
                tail = cur;
            }
            size++;
        }

        // 尾部取出
        public T pollFromTail() {
            T ans = null;
            if (head == null) {
                return ans;
            }
            size--;
            ans = tail.val;
            if (head == tail) {
                head = tail = null;
            } else {
                tail = tail.pre;
                tail.next = null;
            }
            return ans;
        }

        // 查看尾部
        public T peekTail() {
            return tail != null ? tail.val : null;
        }
    }
}
