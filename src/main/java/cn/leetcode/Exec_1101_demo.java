package cn.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Mark
 * @create 2020/3/9
 */
public class Exec_1101_demo {

    public static void main(String[] args) {
        int[][] logs = {
                {20190101, 0, 1},
                {20190104, 3, 4},
                {20190107, 2, 3},
                {20190211, 1, 5},
                {20190224, 2, 4},
                {20190301, 0, 3},
                {20190312, 1, 2},
                {20190322, 4, 5}};

        Exec_1101_demo exec_1101 = new Exec_1101_demo(6);


        PriorityQueue<int[]> queue = new PriorityQueue<>(logs.length, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        for (int[] log : logs) {
            queue.offer(log);
        }

        int max = 0;
        while (!queue.isEmpty()) {
            int[] log = queue.poll();
            int l = exec_1101.find(log[1]);
            int r = exec_1101.find(log[2]);
            if (l != r) {
                exec_1101.union(l,r);
                max = log[0];
            }
        }

    }

    private int[] s;
    private int count;

    public Exec_1101_demo(int elements) {
        s = new int[elements];
        this.count = elements;
        for(int i=0;i<elements;i++){
            s[i] = i;
        }
    }

    public void union(int root1,int root2){
        if(find(root1) == find(root2)){
            return;
        }
        if(s[root2] < s[root1]){
            s[root1] = root2;
        }else {
            if(s[root1] == s[root2]){
                s[root1]--;
            }
            s[root2] = root1;
        }
        count--;
    }

    public int find(int x){
        if(s[x] == x){
            return x;
        }
        return s[x] = find(s[x]);
    }

}
