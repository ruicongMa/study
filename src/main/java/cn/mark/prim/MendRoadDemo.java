package cn.mark.prim;

import java.util.Arrays;

/**
 * 利用普利姆算法解决修路问题
 * 如何修路保证各个村庄都能连通，并且总的修建公路总里程最短?
 *
 * @author Mark
 * @create 2020/3/5
 */
public class MendRoadDemo {

    public static void main(String[] args) {
        MinTree minTree = new MinTree();
        char[] vertexArr = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};
        //创建村庄图
        Graph graph = minTree.createGraph(vertexArr, weight);
        //显示村庄图
        minTree.showGraph(graph);
        minTree.prim(graph, 0);
    }
}

//定义最小生成树->村庄的图
class MinTree {

    /**
     * 普利姆算法解决村庄修路问题
     *
     * @param graph       村庄图
     * @param vertexIndex 村庄在图中的索引 A->0 B->1
     */
    public void prim(Graph graph, int vertexIndex) {
        //定义数组visited[]来标记顶点是否访问过
        int[] visited = new int[graph.vertexs];
        //标记当前顶点（村庄）为访问过
        visited[vertexIndex] = 1;
        //h1,h2记录两个顶点的小标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;//标记两个顶点之间边的最小值
        for (int k = 1; k < graph.vertexs; k++) {//普利姆算法，N个顶点就有N-1条边，所以需要循环1到顶点个数-1次
            //每次循环确定当前节点i与哪个节点距离最近（确定一条边）
            for (int i = 0; i < graph.vertexs; i++) {//i节点表示被访问过的节点
                for (int j = 0; j < graph.vertexs; j++) {//j表示为被访问过的节点
                    //如果当前节点i访问过，且相邻节点j未被访问，且他们之间的距离小于上次两个节点的距离，则满足
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条边是最小
            System.out.println("边<" + graph.vertexArr[h1] + "," + graph.vertexArr[h2] + "> 权值:" + minWeight);
            //将当前这个结点标记为已经访问
            visited[h2] = 1;
            //minWeight 重新设置为最大值 10000
            minWeight = 10000;
        }
    }

    /**
     * 创建图
     *
     * @param vertexArr 顶点集合
     * @param weight    邻接矩阵
     */
    public Graph createGraph(char[] vertexArr, int[][] weight) {
        Graph graph = new Graph(vertexArr.length);
        for (int i = 0; i < vertexArr.length; i++) {
            //初始化图的顶点
            graph.vertexArr[i] = vertexArr[i];
            //初始化邻接矩阵
            for (int j = 0; j < vertexArr.length; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
        return graph;
    }

    /**
     * 显示图
     *
     * @param graph
     */
    public void showGraph(Graph graph) {
        for (int[] row : graph.weight) {
            System.out.println(Arrays.toString(row));
        }
    }
}

//定义图
class Graph {
    int vertexs;//顶点的个数
    char[] vertexArr;//存储顶点
    int[][] weight;//二维数组表示邻接矩阵

    //vertexs代表顶点的个数
    public Graph(int vertexs) {
        this.vertexs = vertexs;
        this.vertexArr = new char[vertexs];
        this.weight = new int[vertexs][vertexs];
    }
}

