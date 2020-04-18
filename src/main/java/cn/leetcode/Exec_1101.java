package cn.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Mark
 * @create 2020/3/9
 */
public class Exec_1101 {

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

        Exec_1101 exec_1101 = new Exec_1101();
        exec_1101.earliestAcq(logs,6);
    }

    int[] parents;
    Map<Integer, Integer> times = new HashMap<>();

    int find(int i) {
        int p = parents[i];

        if (p != i) {
            parents[i] = find(p);
        }
        return parents[i];
    }

    public int earliestAcq(int[][] logs, int N) {
        parents = new int[N];
        for (int k = 0; k < N; k++) {
            parents[k] = k;
        }

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
            int l = find(log[1]);
            int r = find(log[2]);
            if (l != r) {
                parents[l] = r;
                max = log[0];
            }
        }

        for (int k = 1; k < parents.length; k++) {
            if (find(k) != find(0)) {
                return -1;
            }
        }

        return max;
    }

}
