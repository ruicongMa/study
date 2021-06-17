package com.mark.hashtest;

/**
 * @author Mark
 * @date 2020/9/5 08:05
 */
public class MyHashMapTest {

    public static void main(String[] args) {
        Node[] nodes = new Node[3];
        nodes[1] = new Node(10);
        Node node20 = new Node(20);
        nodes[1].next = node20;
        Node node30 = new Node(30);
        node20.next = node30;
        NodeArray nodeArray = new NodeArray(nodes);

        Node[] nodesNew = new Node[4];
        NodeArray nodeArrayNew = new NodeArray(nodesNew);

        while(nodes[1] != null){
            Node next = nodes[1].next;
            nodes[1].next = nodesNew[1];
            nodesNew[1] = nodes[1];
            nodes[1] = next;
        }

        System.out.println(nodeArrayNew);
        System.out.println(nodesNew);

    }
}

class NodeArray{
    private Node[] nodes;

    public NodeArray(Node[] nodes) {
        this.nodes = nodes;
    }

    public Node[] getNodes() {
        return nodes;
    }
}

class Node {
    public int value;
    public Node next;

    public Node(int value) {
        this.value = value;
    }

}
