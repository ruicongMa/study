package cn.mark.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 将二叉搜索树转换成双向链表
 *
 * @author Mark
 * @date 2020/4/15 18:13
 */
public class Exec_06 {

    public static void main(String[] args) {
        HeroNode root = new HeroNode(6, String.valueOf(6));
        HeroNode node4 = new HeroNode(4, String.valueOf(4));
        HeroNode node7 = new HeroNode(7, String.valueOf(7));
        root.left = node4;
        root.right = node7;
        HeroNode node2 = new HeroNode(2, String.valueOf(2));
        HeroNode node5 = new HeroNode(5, String.valueOf(5));
        node4.left = node2;
        node4.right = node5;
        HeroNode node1 = new HeroNode(1, String.valueOf(1));
        HeroNode node3 = new HeroNode(3, String.valueOf(3));
        node2.left = node1;
        node2.right = node3;
        HeroNode node9 = new HeroNode(9, String.valueOf(9));
        node7.right = node9;
        HeroNode node8 = new HeroNode(8, String.valueOf(8));
        node9.left = node8;
        BinaryTree binaryTree = new BinaryTree(root);
        //中序遍历
        binaryTree.midOrder();

        System.out.println("=====================");

        //将二叉搜索树转换成双向链表
        HeroNode head = convert1(root);
        System.out.println(head);
    }

    public static HeroNode convert1(HeroNode root) {
        if (root == null) {
            return null;
        }
        Queue<HeroNode> queue = new LinkedList<>();
        midOrderToLinkedList(root, queue);
        return queue.peek();
    }

    private static void midOrderToLinkedList(HeroNode root, Queue<HeroNode> queue) {
        if (root.left != null) {
            midOrderToLinkedList(root.left, queue);
        }
        queue.offer(root);
        if (root.right != null) {
            midOrderToLinkedList(root.right, queue);
        }
    }
}
