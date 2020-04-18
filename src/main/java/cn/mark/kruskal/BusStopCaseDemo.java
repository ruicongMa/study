package cn.mark.kruskal;

import java.util.Arrays;

/**
 * 利用克鲁斯卡尔解决公交站问题
 * <p>
 * 克鲁斯卡尔算法重点需要解决两个问题：
 * 1.对图的所有边按照权值大小进行排序
 * 2.将添加到最小生成树中时，怎么判断是否形成了回路？
 *
 * @author Mark
 * @create 2020/3/5
 */
public class BusStopCaseDemo {

    public static void main(String[] args) {
        char[] vertexsArr = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int INF = Grap.INF;
        //克鲁斯卡尔算法的邻接矩阵
        int[][] weight = {
          /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
    /*A*/ {0, 12, INF, INF, INF, 16, 14},
    /*B*/ {12, 0, 10, INF, INF, 7, INF},
    /*C*/ {INF, 10, 0, 3, 5, 6, INF},
    /*D*/ {INF, INF, 3, 0, 4, INF, INF},
    /*E*/ {INF, INF, 5, 4, 0, 2, 8},
    /*F*/ {16, 7, 6, INF, 2, 0, 9},
    /*G*/ {14, INF, INF, INF, 8, 9, 0}};

        Grap grap = new Grap(vertexsArr, weight);
        //显示图
        // grap.showGrap();

        //生成最小生成树
        EData[] eData = buildMinTreeByKruskal(grap);

        System.out.println(Arrays.toString(eData));

    }

    /**
     * 利用克鲁斯卡尔算法生成最小生成树
     *
     * @param grap 图
     * @return
     */
    public static EData[] buildMinTreeByKruskal(Grap grap) {
        int[] ends = new int[grap.edges];//保存已有最小生成树中每个顶点对应的终点
        //保存最后生成的最小生成树
        EData[] rest = new EData[grap.edges];
        //获取图中所有的边集合
        EData[] eDatasArr = getEDatas(grap);
        //将图中所有的边的权值进行排序（从小到大）
        bubbleSortEDatas(eDatasArr);
        //遍历所有边，将边添加到最小生成树时，判断加入的边是否构成回路，没有则加入
        int index = 0;
        for (int i = 0; i < eDatasArr.length; i++) {
            //获取当前边的起点对应的索引
            int p1 = grap.getVertexPosition(eDatasArr[i].start);
            //获取当前边的终点对应的索引
            int p2 = grap.getVertexPosition(eDatasArr[i].end);
            //获取p1在最小生成树中对应的终点
            int m = getEnd(ends, p1);
            //获取p2在最小生成树中对应的终点
            int n = getEnd(ends, p2);
            //判断是否构成回路
            if (m != n) {
                //设置m在最小生成树中的终点为n
                ends[m] = n;
                rest[index++] = eDatasArr[i];
            }
        }
        return Arrays.copyOfRange(rest, 0, index);
    }

    /**
     * 获取小标为i的顶点对应的终点的下标
     *
     * @param ends 记录了各个顶点对应终点的下标是哪个
     * @param i    顶点对应的下标
     * @return 下标为i的这个顶点对应的终点的下标
     */
    private static int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    /**
     * 对所有边的权值进行从小到大排序（冒泡排序）
     *
     * @param eDataArr 边集合
     */
    private static void bubbleSortEDatas(EData[] eDataArr) {
        for (int i = 0; i < eDataArr.length - 1; i++) {
            for (int j = 0; j < eDataArr.length - 1 - i; j++) {
                if (eDataArr[j].weight > eDataArr[j + 1].weight) {
                    EData eData = eDataArr[j];
                    eDataArr[j] = eDataArr[j + 1];
                    eDataArr[j + 1] = eData;
                }
            }
        }
    }

    /**
     * 根据图获取所有边的集合[[A,B,12],[B,F,7]...]
     *
     * @param grap 图
     * @return 图所有的边
     */
    private static EData[] getEDatas(Grap grap) {
        EData[] eData = new EData[grap.edges];
        int index = 0;
        for (int i = 0; i < grap.weight.length; i++) {
            //把自己跟自己过滤掉 j=i+1
            for (int j = i + 1; j < grap.weight[0].length; j++) {
                if (grap.weight[i][j] != Grap.INF) {
                    char start = grap.vertexsArr[i];
                    char end = grap.vertexsArr[j];
                    eData[index++] = new EData(start, end, grap.weight[i][j]);
                }
            }
        }
        return eData;
    }

}

//定义EData，它的对象实例表示一条边
class EData {
    char start;//边的一个点
    char end;//边的另外一个点
    int weight;//边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    //重写toString, 便于输出边信息
    @Override
    public String toString() {
        return "EData [<" + start + ", " + end + ">= " + weight + "]";
    }
}

//定义图
class Grap {
    int edges;//边的个数
    char[] vertexsArr;//顶点数组
    int[][] weight;//二维数组表示邻接矩阵
    public static final int INF = Integer.MAX_VALUE;//表示两个顶点不通

    public Grap(char[] vertexsArr, int[][] weight) {
        this.vertexsArr = vertexsArr;
        this.weight = weight;
        //统计边的个数
        for (int i = 0; i < weight.length; i++) {
            //把自己跟自己过滤掉 j=i+1
            for (int j = i + 1; j < weight[0].length; j++) {
                if (weight[i][j] != INF) {
                    edges++;
                }
            }
        }
    }

    /**
     * 获取顶点对应的下标
     *
     * @param vertex 顶点
     * @return 如果找不到，则返回-1
     */
    public int getVertexPosition(char vertex) {
        for (int i = 0; i < vertexsArr.length; i++) {
            if (vertexsArr[i] == vertex) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 显示邻接矩阵
     */
    public void showGrap() {
        for (int[] row : this.weight) {
            System.out.println(Arrays.toString(row));
        }
    }
}

