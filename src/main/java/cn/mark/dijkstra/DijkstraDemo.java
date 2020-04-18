package cn.mark.dijkstra;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法求最短路径
 *
 * @author Mark
 * @create 2020/3/5
 */
public class DijkstraDemo {

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵
        int[][] matrix = new int[vertexs.length][vertexs.length];
        final int N = 65535;// 表示不可以连接
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        //创建Graph对象
        Graph graph = new Graph(vertexs, matrix);
        //显示图
        graph.showGraph();

        graph.dijkstra(6);

        graph.showDijkstraResult();

    }
}

//已访问顶点集合
class VisitedVertex {
    int[] visitedArr;//记录各个顶点的访问情况，1表示访问过，0表示未访问过，会动态更新
    int[] preVisitedArr;//每个下标对应的值为前一个顶点下标, 会动态更新
    int[] dis;// 记录出发顶点到其他所有顶点的距离,比如G为出发顶点，就会记录G到其它顶点的距离，会动态更新，求的最短距离就会存放到dis

    /**
     * 构造器
     *
     * @param vertexs 顶点的个数
     * @param index   出发顶点对应的下标，比如G顶点，对应的下标为6
     */
    public VisitedVertex(int vertexs, int index) {
        this.visitedArr = new int[vertexs];
        this.preVisitedArr = new int[vertexs];
        this.dis = new int[vertexs];
        //初始化dis数组
        Arrays.fill(dis, 65535);
        //设置出发节点已访问过
        this.visitedArr[index] = 1;
        //将出发顶点距离设置为0
        this.dis[index] = 0;
    }

    /**
     * 判断顶点是否被访问过
     *
     * @param vertexIndex 顶点下标
     * @return true：访问过 false：为被访问过
     */
    public boolean isVisited(int vertexIndex) {
        return this.visitedArr[vertexIndex] == 1;
    }

    /**
     * 更新出发顶点到顶点index的距离
     *
     * @param index 顶点的下标
     * @param len   出发顶点到index顶点的距离
     */
    public void updateDis(int index, int len) {
        this.dis[index] = len;
    }

    /**
     * 更新pre这个顶点的前驱顶点为index顶点
     *
     * @param pre   下标为pre的顶点
     * @param index
     */
    public void updatePre(int pre, int index) {
        this.preVisitedArr[pre] = index;
    }

    /**
     * 功能:返回出发顶点到index顶点的距离
     *
     * @param index
     */
    public int getDis(int index) {
        return this.dis[index];
    }

    /**
     * 继续选择并返回新的访问节点，比如G完后，A点作为新的访问节点（注意不是出发顶点）
     *
     * @return
     */
    public int updateVisitedArr() {
        int min = 65535;//假设最短路径为65535
        int index = 0;//最短路径的索引
        //循环找到未被访问过，且离顶点距离最短的节点
        for (int i = 0; i < visitedArr.length; i++) {
            if (visitedArr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        //更新index节点为访问过
        visitedArr[index] = 1;
        return index;
    }

    /**
     * 显示最后输出结果
     */
    public void showResult() {
        System.out.println("===================================");
        for (int i : visitedArr) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : preVisitedArr) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : dis) {
            System.out.print(i + " ");
        }
        System.out.println();
        //为了好看最后的最短距离，我们处理
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertex[count] + "(" + i + ") ");
            } else {
                System.out.println("N ");
            }
            count++;
        }
        System.out.println();
        System.out.println("===================================");
    }

}

//定义图
class Graph {
    char[] vertexs;//顶点数组
    int[][] matrix;//二维数组表示邻接矩阵
    VisitedVertex vv; //已经访问的顶点的集合

    public Graph(char[] vertexs, int[][] matrix) {
        this.vertexs = vertexs;
        this.matrix = matrix;
    }

    //显示图
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 显示迪杰斯特拉结果
     */
    public void showDijkstraResult() {
        this.vv.showResult();
    }

    /**
     * 迪杰斯特拉算法
     *
     * @param index 索引为index的顶点
     */
    public void dijkstra(int index) {
        this.vv = new VisitedVertex(vertexs.length, index);
        update(index);//更新index顶点到周围顶点的距离和前驱
        for (int i = 1; i < vertexs.length; i++) {
            index = this.vv.updateVisitedArr();//选择新的访问节点
            update(index);//更新index顶点到周围顶点的距离和前驱
        }
    }

    /**
     * 更新索引为index的顶点到周围顶点的距离和周围顶点的前驱顶点的下标
     *
     * @param index 索引为index的顶点
     */
    private void update(int index) {
        int len = 0;
        //遍历matrix[index]这行的数据
        for (int j = 0; j < matrix[index].length; j++) {
            //len = 出发顶点到index的距离 + 从index顶点到j顶点的距离
            len = vv.getDis(index) + matrix[index][j];
            //如果j顶点未被访问过且len小于出发顶点到j的距离，就需要更新
            if (!vv.isVisited(j) && len < vv.getDis(j)) {
                vv.updatePre(j, index);//更新j顶点的前驱为index顶点
                vv.updateDis(j, len);//更新出发顶点到j的距离为len
            }
        }
    }

}