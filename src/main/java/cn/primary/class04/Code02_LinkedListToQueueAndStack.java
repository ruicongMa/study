package cn.primary.class04;

/**
 * @author Mark
 * @date 2021/6/19 下午3:29
 */
public class Code02_LinkedListToQueueAndStack {

    public static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V data) {
            this.value = data;
        }
    }

    public static class MyQueue<V> {
        private Node<V> head;
        private Node<V> tail;
        private int size;

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void offer(V data) {
            Node<V> cur = new Node<>(data);
            if (tail == null) {
                head = tail = cur;
            } else {
                tail.next = cur;
                tail = cur;
            }
            size++;
        }

        public V poll() {
            V ans = null;
            if (head != null) {
                ans = head.value;
                head = head.next;
                size--;
            }
            if (head == null) {
                tail = null;
            }
            return ans;
        }

        public V peek() {
            V ans = null;
            if (head != null) {
                ans = head.value;
            }
            return ans;
        }
    }

    public static class MyStack<V> {
        private Node<V> head;
        private int size;

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void push(V data) {
            Node<V> cur = new Node<>(data);
            if (head != null) {
                cur.next = head;
            }
            head = cur;
            size++;
        }

        public V pop() {
            V ans = null;
            if (head != null) {
                ans = head.value;
                head = head.next;
                size--;
            }
            return ans;
        }

        public V peek() {
            return head != null ? head.value : null;
        }

    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.offer(1);
        queue.offer(3);
        queue.offer(2);
        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + " ");
        }
        System.out.println();
        System.out.println("================");

        MyStack stack = new MyStack();
        stack.push(6);
        stack.push(1);
        stack.push(3);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

}
