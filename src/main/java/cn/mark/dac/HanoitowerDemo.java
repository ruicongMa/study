package cn.mark.dac;

/**
 * 汉诺塔实现
 *
 * @author Mark
 * @create 2020/3/4
 */
public class HanoitowerDemo {

    public static void main(String[] args) {
        hanoitower(5, 'A', 'B', 'C');
    }

    /**
     * 实现汉诺塔，采用分治算法
     * 1.如果只有一个盘，从A塔移动到C塔
     * 2.如果n>=2个盘，我们总是可以看成是两个盘，1）最下边的一个盘 2）上边所有盘
     * 3.将上边所有的盘从A塔移动B塔，将最下边的盘从A塔移动到C塔，再将B塔所有的盘从B塔移动到C塔
     *
     * @param num 多少个盘
     * @param a   A塔
     * @param b   B塔
     * @param c   C塔
     */
    public static void hanoitower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘从" + a + "=>" + c);
        } else { //分三步
            //第一步，将A塔最上边所有的盘从A塔移动到B塔
            hanoitower(num - 1, a, c, b);
            //第二步，将A塔最下边的盘从A塔移动到C塔
            System.out.println("第" + num + "个盘从" + a + "=>" + c);
            //第三步，将B塔所有的盘从B塔移动到C塔
            hanoitower(num - 1, b, a, c);
        }
    }
}

