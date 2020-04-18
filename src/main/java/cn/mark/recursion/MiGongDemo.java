package cn.mark.recursion;

/**
 * 采用递归回溯解决迷宫问题
 *
 * @author Mark
 * @create 2020/2/25
 */
public class MiGongDemo {

    public static void main(String[] args) {
        //定义一个二维数组表示迷宫地图
        int[][] map = new int[8][7];
        //第一行和最后一行元素都为1，表示墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //第一列和最后一列元素都为1，表示墙
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置墙
        map[3][1] = 1;
        map[3][2] = 1;
        map[1][2] = 1;
        map[2][2] = 1;

        System.out.println("迷宫地图如下：");
        for (int[] row : map) {
            for (int item : row) {
                System.out.print(item + " ");
            }
            System.out.println();
        }

        System.out.println("小球开始走路，地图如下：");
        setWay(map, 1, 1);

        for (int[] row : map) {
            for (int item : row) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    /**
     * 采用递归回溯给小球走路
     * 约定如下：
     * 1）i,j 表示从地图哪个位置开始 (1,1)
     * 2）如果小球到达map[6][5]位置，则说明通过找到
     * 3）map[i][j]为0表示路还未走过，为1表示墙，为2表示路可以走，为3表示改点已经走过，但走不通
     * 4）走路策略：下->右->上->左
     *
     * @param map 迷宫地图
     * @param i   开始位置行坐标
     * @param j   开始位置列坐标
     * @return true：表示此路可以走通，false：表示此路不通
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            //如果当前这个点还未走过
            if (map[i][j] == 0) {
                //假设map[i][j] = 2 可以走
                map[i][j] = 2;
                //按照规定好的走路策略
                if (setWay(map, i + 1, j)) {//往下走路
                    return true;
                } else if (setWay(map, i, j + 1)) {//往右走路
                    return true;
                } else if (setWay(map, i - 1, j)) {//往上走路
                    return true;
                } else if (setWay(map, i, j - 1)) {//往左走路
                    return true;
                } else {
                    ////说明该点是走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // 可能存在 1 2 3
                return false;
            }
        }
    }
}
