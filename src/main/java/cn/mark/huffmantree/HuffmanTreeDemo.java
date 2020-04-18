package cn.mark.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树：wpl最小的就是赫夫曼树。
 * wpl：weighted path length 树的带权路径长度规定为所有叶子节点的带权路径之和，即为wpl
 * 权值越大的节点离根节点越近的二叉树才是最优二叉树
 *
 * @author Mark
 * @create 2020/3/2
 */
public class HuffmanTreeDemo {

    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = buildHuffmanTree(arr);
        root.preOrder();
    }

    /**
     * 构成赫夫曼树的步骤：
     * 1.从小到大进行排序, 将每一个数据，每个数据都是一个节点 ， 每个节点可以看成是一颗最简单的二叉树
     * 2.取出根节点权值最小的两颗二叉树
     * 3.组成一颗新的二叉树, 该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
     * 4.再将这颗新的二叉树，以根节点的权值大小 再次排序， 不断重复  1-2-3-4 的步骤，直到数列中，所有的数据都被处理，就得到一颗赫夫曼树
     *
     * @param arr 待构建的数组
     * @return 赫夫曼根节点
     */
    public static Node buildHuffmanTree(int[] arr) {
        List<Node> list = new ArrayList<>();
        for (int value : arr) {
            list.add(new Node(value));
        }
        while (list.size() > 1) {
            //将数据从小到大排序
            Collections.sort(list);
            //获取左节点
            Node leftNode = list.get(0);
            //获取右节点
            Node rightNode = list.get(1);
            //构建新节点
            Node parentNode = new Node(leftNode.value + rightNode.value);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            //移除左节点和右节点
            list.remove(leftNode);
            list.remove(rightNode);
            //把新节点添加list
            list.add(parentNode);
        }
        return list.get(0);
    }
}

//节点类
class Node implements Comparable<Node> {
    int value;
    Node left;//左节点
    Node right;//右节点

    public Node(int value) {
        this.value = value;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {//从小到大排序
        return this.value - o.value;
    }
}