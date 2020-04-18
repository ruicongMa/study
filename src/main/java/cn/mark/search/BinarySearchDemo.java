package cn.mark.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找：前提数组是有序的
 *
 * @author Mark
 * @create 2020/2/28
 */
public class BinarySearchDemo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println(binarySearchByRecursion(arr, 0, arr.length - 1, 30));
        System.out.println(binarySearchNoRecursion(arr, 1));

        int[] arr2 = {1, 8, 10, 89, 1000, 1000, 1234};
        System.out.println(binarySearchByRecursion2(arr2, 0, arr.length - 1, 1000));

    }

    /**
     * 非递归二分查找：前提数组有序，该方法针对数组是升序
     *
     * @param arr
     * @param findValue
     * @return
     */
    public static int binarySearchNoRecursion(int[] arr, int findValue) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;//int mid = left + (right - left) / 2; //防止溢出
            if (arr[mid] == findValue) {
                return mid;
            } else if (arr[mid] > findValue) {
                //向左查找
                right = mid - 1;
            } else {
                //右查找
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 采用递归进行二分查找
     *
     * @param arr       原数组
     * @param left      左下标
     * @param right     右下标
     * @param findValue 要查找的值
     * @return 如果找到则返回该值对应的下表，否则返回-1
     */
    public static int binarySearchByRecursion(int[] arr, int left, int right, int findValue) {
        if (left > right) { // 说明整个数组左边或右边都查找完毕了,还未找到，则返回-1
            return -1;
        }
        int mid = (left + right) / 2; //int mid = left + (right - left) / 2; //防止溢出
        int midValue = arr[mid];
        if (findValue > midValue) {//向右递归查找
            return binarySearchByRecursion(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {//向左递归查找
            return binarySearchByRecursion(arr, left, mid - 1, findValue);
        } else {
            return mid;
        }
    }

    /**
     * {1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000.
     *
     * @param arr       原数组
     * @param left      左下标
     * @param right     右下标
     * @param findValue 要查找的值
     * @return 下标集合，未找到返回空集合
     */
    public static List<Integer> binarySearchByRecursion2(int[] arr, int left, int right, int findValue) {
        if (left > right) { // 说明整个数组左边或右边都查找完毕了,还未找到，则返回-1
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (findValue > midValue) {//向右递归查找
            return binarySearchByRecursion2(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {//向左递归查找
            return binarySearchByRecursion2(arr, left, mid - 1, findValue);
        } else {
            List<Integer> list = new ArrayList<>();
            //向mid索引值的左边扫描，将满足条件的元素下标加入到list中
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findValue) {
                    break;
                }
                list.add(temp);
                temp -= 1;
            }
            //加入mid
            list.add(mid);
            //向mid索引值的右边扫描，将满足条件的元素下标加入到list中
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findValue) {
                    break;
                }
                list.add(temp);
                temp += 1;
            }
            return list;
        }
    }

}
