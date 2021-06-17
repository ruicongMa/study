package cn.primary.class02;

/**
 * @author Mark
 * @date 2021/6/16 上午10:07
 */
public class Code01_PreSum {

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 7};

        RangeSum1 rangeSum1 = new RangeSum1(arr);
        System.out.println(rangeSum1.rangeSum(2, 4));

        RangeSum2 rangeSum2 = new RangeSum2(arr);
        System.out.println(rangeSum2.rangeSum(2, 4));

        RangeSum3 rangeSum3 = new RangeSum3(arr);
        System.out.println(rangeSum3.rangeSum(2, 4));
    }

    public static class RangeSum1 {

        private int[] arr;

        public RangeSum1(int[] array) {
            this.arr = array;
        }

        public int rangeSum(int L, int R) {
            int sum = 0;
            for (int i = L; i <= R; i++) {
                sum += arr[i];
            }
            return sum;
        }
    }

    public static class RangeSum2 {

        private int[] preSum;

        public RangeSum2(int[] arr) {
            preSum = new int[arr.length];
            preSum[0] = arr[0];
            for (int i = 1; i < arr.length; i++) {
                preSum[i] = preSum[i - 1] + arr[i];
            }
        }

        public int rangeSum(int L, int R) {
            return L == 0 ? preSum[R] : preSum[R] - preSum[L - 1];
        }

    }

    public static class RangeSum3 {

        private int[][] arr;

        public RangeSum3(int[] array) {
            arr = new int[array.length][array.length];
            arr[0][0] = array[0];
            for (int i = 1; i < array.length; i++) {
                for (int j = 1; j < arr.length; j++) {
                    if (i > j) {
                        continue;
                    }
                    arr[i][j] = (i == j) ? array[i] : arr[i][j - 1] + array[j];
                }
            }
        }

        public int rangeSum(int L, int R) {
            return arr[L][R];
        }
    }

}
