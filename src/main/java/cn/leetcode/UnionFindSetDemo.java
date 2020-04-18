package cn.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Mark
 * @create 2020/3/9
 */
public class UnionFindSetDemo {

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

        System.out.println(earliestAcq(logs, 6));

    }

    public static int earliestAcq(int[][] logs, int N) {
        Arrays.sort(logs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for (int[] log : logs) {
            System.out.println(Arrays.toString(log));
        }

        UnionFindSet unionFindSet = new UnionFindSet(N);

        for (int[] log : logs) {
            unionFindSet.union(log[1], log[2]);
            if (unionFindSet.circles == 1) {
                return log[0];
            }
        }

        return -1;
    }
}

class UnionFindSet {
    int[] parents;
    int[] ranks;
    int circles;

    public UnionFindSet(int n) {
        this.circles = n;
        this.parents = new int[n];
        this.ranks = new int[n];
        for (int i = 0; i < n; i++) {
            this.parents[i] = i;
        }
    }

    public int find(int x) {
        if (x != parents[x]) {
            parents[x] = find(parents[x]);//路径压缩
        }
        return parents[x];
    }

    public void union(int x, int y) {
        int root1 = find(x);
        int root2 = find(y);
        if (root1 != root2) {
            circles--;
        }
        if (ranks[x] > ranks[y]) {
            parents[root2] = root1;
        } else if (ranks[x] < ranks[y]) {
            parents[root1] = root2;
        } else {
            parents[root2] = root1;
            ranks[root1]++;
        }
    }

}
