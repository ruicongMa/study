package cn.primary.class08;

import java.util.Stack;

/**
 * @author Mark
 * @date 2021/6/24 下午12:30
 */
public class Code01_PartitionAndQuickSort {

    public static void splitNum1(int[] arr) {
        int lessEqualR = -1;
        int index = 0;
        int N = arr.length - 1;
        while (index <= N) {
            if (arr[index] <= arr[N]) {
                swap(arr, ++lessEqualR, index++);
            } else {
                index++;
            }
        }
    }

    public static void splitNum2(int[] arr) {
        int N = arr.length;
        int lessR = -1;
        int index = 0;
        int moreL = N - 1;
        while (index < moreL) {
            if (arr[index] < arr[N - 1]) {
                swap(arr, ++lessR, index++);
            } else if (arr[index] > arr[N - 1]) {
                swap(arr, --moreL, index);
            } else {
                index++;
            }
        }
        swap(arr, moreL, N - 1);
    }

    // arr[L...R]范围上，拿arr[R]做划分值，
    // L....R < = >
    public static int[] partition(int[] arr, int L, int R) {
        int lessR = L - 1;
        int moreL = R;
        int index = L;
        while (index < moreL) {
            if (arr[index] < arr[R]) {
                swap(arr, ++lessR, index++);
            } else if (arr[index] > arr[R]) {
                swap(arr, --moreL, index);
            } else {
                index++;
            }
        }
        swap(arr, moreL, R);
        return new int[]{lessR + 1, moreL};
    }

    public static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] equalE = partition(arr, L, R);
        process(arr, L, equalE[0] - 1);
        process(arr, equalE[1] + 1, R);
    }

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        Stack<Job> stack = new Stack<>();
        stack.push(new Job(0, arr.length - 1));
        while (!stack.isEmpty()) {
            Job cur = stack.pop();
            int[] equals = partition(arr, cur.L, cur.R);
            if (equals[0] > cur.L) { // 有 < 区域
                stack.push(new Job(cur.L, equals[0] - 1));
            }
            if (equals[1] < cur.R) { // 有 > 区域
                stack.push(new Job(equals[1] + 1, cur.R));
            }
        }
    }

    public static class Job {
        public int L;
        public int R;

        public Job(int l, int r) {
            L = l;
            R = r;
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 2, 4, 8, 9, 3, 4};
        quickSort2(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
