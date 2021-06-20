package cn.primary.class04;

/**
 * @author Mark
 * @date 2021/6/19 下午7:19
 */
public class Code03_DoubleLinkedListDeque {

    public static class Node<V> {
        public V value;
        public Node<V> next;
        public Node<V> last;

        public Node(V data) {
            this.value = data;
        }
    }

    public static class MyDeque<V> {
        private Node<V> head;
        private Node<V> tail;
        private int size;

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        // 头部添加
        public void pushHead(V data) {
            Node<V> cur = new Node<>(data);
            if (head == null) {
                head = tail = cur;
            } else {
                cur.next = head;
                head.last = cur;
                head = cur;
            }
            size++;
        }

        // 尾部添加
        public void pushTail(V data) {
            Node<V> cur = new Node<>(data);
            if (head == null) {
                head = tail = cur;
            } else {
                tail.next = cur;
                cur.last = tail;
                tail = cur;
            }
            size++;
        }

        // 头部取出
        public V pollHead() {
            V ans = null;
            if (head == null) {
                return ans;
            }
            size--;
            ans = head.value;
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
                head.last = null;
            }
            return ans;
        }

        // 尾部取出
        public V pollTail() {
            V ans = null;
            if (head == null) {
                return ans;
            }
            size--;
            ans = tail.value;
            if (head == tail) {
                head = tail = null;
            } else {
                tail = tail.last;
                tail.next = null;
            }
            return ans;
        }

        // 查看头部
        public V peekHead() {
            return head != null ? head.value : null;
        }

        // 查看尾部
        public V peekTail() {
            return tail != null ? tail.value : null;
        }
    }

}
