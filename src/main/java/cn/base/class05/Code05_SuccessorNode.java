package cn.base.class05;

import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * 给你一个二叉树中的某个节点，返回该节点的后继节点
 *
 * @author Mark
 * @date 2021/7/5 下午3:40
 */
public class Code05_SuccessorNode {

    // 什么是后继节点？二叉树中序遍历后的结果中找。

    public static class Node {
        int val;
        Node left;
        Node right;
        Node parent;

        public Node(int val) {
            this.val = val;
        }
    }

    // 后继
    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            return getLeftMost(node.right);
        }
        Node parent = node.parent;
        while (parent != null && parent.left != node) {
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    // 前驱
    public static Node getQianQuNode(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left != null) {
            return getRightMost(node.left);
        }
        Node parent = node.parent;
        while (parent != null && parent.right != node) {
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    private static Node getRightMost(Node node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private static Node getLeftMost(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // 中序遍历（左、头、右）
    public static void mid(Node head, LinkedList<Node> linkedList) {
        if (head == null) {
            return;
        }
        mid(head.left, linkedList);
        linkedList.add(head);
        mid(head.right, linkedList);
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.left = node2;
        node1.right = node3;
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node2.left = node4;
        node2.right = node5;
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node3.left = node6;
        node3.right = node7;
        Node node8 = new Node(8);
        node5.left = node8;
        Node node9 = new Node(9);
        node6.right = node9;

        // 中序遍历
        LinkedList<Node> list = new LinkedList<>();
        mid(node1, list);
        System.out.println(list.stream().map(node -> node.val).collect(Collectors.toList()));// [4, 2, 8, 5, 1, 6, 9, 3, 7]

        node8.parent = node5;
        node4.parent = node2;
        node5.parent = node2;
        node2.parent = node1;
        node9.parent = node6;
        node6.parent = node3;
        node7.parent = node3;
        node3.parent = node1;

        // 后继
        System.out.println(getSuccessorNode(node4).val);// 2
        System.out.println(getSuccessorNode(node8).val);// 5
        System.out.println(getSuccessorNode(node1).val);// 6
        System.out.println(getSuccessorNode(node7));// null

        // 前驱
        System.out.println(getQianQuNode(node4));// null
        System.out.println(getQianQuNode(node2).val);// 4
        System.out.println(getQianQuNode(node7).val);// 3
        System.out.println(getQianQuNode(node6).val);// 1

    }

}
