package cn.mark.lru;

import java.util.HashMap;

/**
 * Java实现LRU（HashMap + 双向链表）
 * 核心步骤：
 * 1.save(key,value),首先在HashMap找到key对应的节点，如果节点存在，更新节点的值，并把这个节点移动到队列头。
 * 如果不存在，需要构造新的节点，并且把节点添加到对列头，如果LRU空间不足，则通过tail淘汰掉对尾的节点，同时在HashMap中移除key。
 * 2.get(key),通过HashMap找到LRU链表节点，因为根据LRU原理，这个节点是最新访问的，所以要把节点插入到队列头，然后返回缓存的值。
 *
 * @author Mark
 * @create 2020/2/21
 */
public class LRUCacheDemo {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.set("key1", 7);
        lruCache.set("key2", 0);
        lruCache.set("key3", 1);
        lruCache.set("key4", 2);
        lruCache.get("key2");
        lruCache.set("key5", 3);
        lruCache.get("key2");
        lruCache.set("key6", 4);

        lruCache.printDoubleLinedNodeInfo();
    }
}

class LRUCache {
    private HashMap<String, DoubleLinkedNode> cache = new HashMap<String, DoubleLinkedNode>();
    private int capacity;
    private DoubleLinkedNode head;
    private DoubleLinkedNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new DoubleLinkedNode("head", -1);
        this.tail = new DoubleLinkedNode("tail", -1);

        this.head.next = this.tail;
        this.tail.pre = this.head;
    }

    public int get(String key) {
        DoubleLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        //说明存在，则把该node移动到链表的头部
        this.moveToHead(node);
        return node.value;
    }

    public void set(String key, int value) {
        DoubleLinkedNode node = cache.get(key);
        if (node == null) {
            DoubleLinkedNode newNode = new DoubleLinkedNode(key, value);
            this.cache.put(key, newNode);
            this.addNode(newNode);

            //如果cache容量大于链表容量则移除链表尾部节点
            if (this.cache.size() > capacity) {
                // pop the tail
                DoubleLinkedNode tail = this.popTail();
                this.cache.remove(tail.key);
            }
        } else {
            //更新值
            node.value = value;
            //移动到链表头
            this.moveToHead(node);
        }
    }

    public void printDoubleLinedNodeInfo() {
        if (this.head != null && this.head.next != null) {
            //定义辅助指针
            DoubleLinkedNode temp = this.head.next;
            while (temp != tail) {
                System.out.println(temp);
                temp = temp.next;
            }
        }
    }

    //删除链表尾部节点(弹出链表尾部元素)
    private DoubleLinkedNode popTail() {
        DoubleLinkedNode tail = this.tail.pre;
        this.removeNode(tail);
        return tail;
    }

    //移动当前节点到链表头部
    private void moveToHead(DoubleLinkedNode node) {
        this.removeNode(node);
        this.addNode(node);
    }

    //往链表头添加节点
    private void addNode(DoubleLinkedNode node) {
        node.pre = head;
        node.next = head.next;

        head.next.pre = node;
        head.next = node;
    }

    //从链表中删除存在的节点，自我删除
    private void removeNode(DoubleLinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
}

class DoubleLinkedNode {
    String key;
    int value;
    DoubleLinkedNode pre;
    DoubleLinkedNode next;

    public DoubleLinkedNode(String key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "DoubleLinkedNode{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}

