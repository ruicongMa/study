package cn.base.class02;

/**
 * @author Mark
 * @date 2021/6/27 下午10:43
 */
public class Code03_LinkedListToQueueAndStack {

    // 单项链表实现队列 尾部添加、头部取出，即满足先进先出
    public static class Node<T> {
        T val;
        Node<T> next;

        public Node(T val) {
            this.val = val;
        }
    }

    public static class MyQueue<T> {
        Node<T> head;
        Node<T> tail;
        int size;

        public boolean isEmpty() {
            return size == 0;
        }

        public void offer(T data) {
            Node cur = new Node(data);
            if (tail == null) {
                head = tail = cur;
            } else {
                tail.next = cur;
                tail = cur;
            }
            size++;
        }

        public T poll() {
            T ans = null;
            if (head != null) {
                ans = head.val;
                head = head.next;
                size--;
            } else {
                tail = null;
            }
            return ans;
        }

        public T peek() {
            if (head == null) {
                return null;
            }
            return head.val;
        }
    }

    // 单向链表实现栈，先进后出，后进先出，头部添加、头部移除即可实现栈
    public static class MyStack<T> {
        Node<T> head;
        int size;

        public boolean isEmpty() {
            return size == 0;
        }

        public void push(T data) {
            Node cur = new Node(data);
            if (head != null) {
                cur.next = head;
            }
            head = cur;
            size++;
        }

        public T pop() {
            T ans = null;
            if (head != null) {
                ans = head.val;
                head = head.next;
                size--;
            }
            return ans;
        }
    }

}
