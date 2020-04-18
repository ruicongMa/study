package cn.mark.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 图的两种表示方式：1.二维数组（邻接矩阵）2.链表（邻接表）
 * 图的遍历方式：1.深度优先搜索（dfs） 2.广度优先搜索（bfs）
 *
 * @author Mark
 * @create 2020/3/3
 */
public class GraphDemo {

    public static void main(String[] args) {
        String[] vertexs = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(vertexs.length);
        //循环插入顶点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        //A-B A-C B-C B-D B-E
        //插入边
        graph.insertEdges(0, 1, 1);
        graph.insertEdges(0, 2, 1);
        graph.insertEdges(1, 2, 1);
        graph.insertEdges(1, 3, 1);
        graph.insertEdges(1, 4, 1);

        //显示图（邻接矩阵）
        graph.showGraph();
        //深度优先遍历
        graph.dfs();
        System.out.println();
        //广度优先遍历
        graph.bfs();
    }
}

//定义一个无向图
class Graph {
    private List<String> vertexs;//存储顶点
    private int[][] edges;//用二维数组，邻接矩阵表示图
    private int numOfEdges;//有多少条边
    private boolean[] isVisited;//记录深度优先顶点是否被访问过

    //n：顶点的个数
    public Graph(int n) {
        //初始化矩阵
        this.edges = new int[n][n];
        this.vertexs = new ArrayList<>(n);
    }

    public void bfs() {
        this.isVisited = new boolean[getNumOfVertex()];
        //对所有节点进行广度优先搜索
        for (int v = 0; v < getNumOfVertex(); v++) {
            if (!isVisited[v]) {
                bfs(isVisited, v);
            }
        }
    }

    /**
     * 节点：顶点
     * 广度优先搜索算法思路如下：
     * 1.访问初始结点v并标记结点v为已访问。
     * 2.结点v入队列
     * 3.当队列非空时，继续执行，否则算法结束。
     * 4.出队列，取得队头结点u。
     * 5.查找结点u的第一个邻接结点w。
     * 6.若结点u的邻接结点w不存在，则转到步骤3；否则循环执行以下三个步骤：
     * 6.1 若结点w尚未被访问，则访问结点w并标记为已访问。
     * 6.2 结点w入队列
     * 6.3 查找结点u的继w邻接结点后的下一个邻接结点w，转到步骤6
     *
     * @param isVisited 记录节点是否被访问过
     * @param v         第v个节点
     */
    private void bfs(boolean[] isVisited, int v) {
        //输出当前节点
        System.out.print(getVertexByIndex(v) + "=>");
        //标记节点v为访问过
        isVisited[v] = true;
        //入队列，记录节点的下标
        Queue<Integer> queue = new LinkedList<>();
        //加入到队列
        queue.offer(v);
        while (!queue.isEmpty()) {
            //取出队列头
            int u = queue.poll();
            //查找节点u的第一个邻接节点w
            int w = getFirstNeighbour(u);
            while (w != -1) {//节点w存在
                //未被访问
                if (!isVisited[w]) {
                    System.out.print(getVertexByIndex(w) + "=>");
                    isVisited[w] = true;
                    //入队列
                    queue.offer(w);
                }
                //访问过，则查找u的邻接节点w的下一个邻接节点
                w = getNextNeighbour(u, w);
            }
        }
    }

    /**
     * 节点：顶点
     * 深度优先搜索算法思路如下：
     * 1.访问初始结点v，并标记结点v为已访问。
     * 2.查找结点v的第一个邻接结点w。
     * 3.若w存在，则继续执行4，如果w不存在，则回到第1步，将从v的下一个结点继续。
     * 4.若w未被访问，对w进行深度优先遍历递归（即把w当做另一个v，然后进行步骤123）。
     * 5.若w被访问过，则查找邻接结点w的下一个邻接结点，转到步骤3。
     *
     * @param isVisited 记录节点是否被访问过
     * @param v         第v个节点
     */
    private void dfs(boolean[] isVisited, int v) {
        //输出当前节点v
        System.out.print(getVertexByIndex(v) + "=>");
        //记录当前节点v被访问过
        isVisited[v] = true;
        //获取节点v的第一个邻接节点w
        int w = getFirstNeighbour(v);
        while (w != -1) {
            //w存在且为被访问过，则对w进行深度优先遍历递归
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //w存在且已经访问过，则查找w的下一个邻接节点
            w = getNextNeighbour(v, w);
        }
    }

    public void dfs() {
        this.isVisited = new boolean[getNumOfVertex()];
        //遍历所有顶点（节点），进行dfs
        for (int v = 0; v < getNumOfVertex(); v++) {
            if (!isVisited[v]) {
                this.dfs(isVisited, v);
            }
        }
    }

    /**
     * 得到第v个节点的第一个邻接节点的下标
     *
     * @param v 第v个节点的下标
     * @return 如果没找到，则返回-1
     */
    public int getFirstNeighbour(int v) {
        for (int i = 0; i < vertexs.size(); i++) {
            if (edges[v][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接节点的下标（v1,v2）获取下一个邻接节点
     *
     * @param v1 前一个邻接节点的行坐标
     * @param v2 前一个邻接节点的列坐标
     * @return 如果没找到，则返回-1
     */
    public int getNextNeighbour(int v1, int v2) {
        for (int i = v2 + 1; i < vertexs.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 显示图
     */
    public void showGraph() {
        for (int[] edgesArr : edges) {
            for (int edges : edgesArr) {
                System.out.print(edges + " ");
            }
            System.out.println();
        }
    }

    /**
     * 根据索引获取顶点值
     *
     * @param index 顶点对应的索引
     * @return
     */
    public String getVertexByIndex(int index) {
        return vertexs.get(index);
    }

    /**
     * 返回顶点的个数
     *
     * @return
     */
    public int getNumOfVertex() {
        return vertexs.size();
    }

    /**
     * 返回边的个数
     *
     * @return
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 插入顶点
     *
     * @param vertex 顶点
     */
    public void insertVertex(String vertex) {
        vertexs.add(vertex);
    }

    /**
     * 插入边
     *
     * @param v1     第一个顶点的下标
     * @param v2     第二个顶点的下标
     * @param weight 权值
     */
    public void insertEdges(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        this.numOfEdges++;
    }

}
