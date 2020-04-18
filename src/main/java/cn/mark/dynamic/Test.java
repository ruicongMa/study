package cn.mark.dynamic;

import java.util.Arrays;

/**
 * @author Mark
 * @create 2020/3/23
 */
public class Test {

    public static void main(String[] args) {
        String str = "abcdabds";
        int[] arr = new int[str.length()];
        for(Character character : str.toCharArray()){
            arr[character]++;
        }
        System.out.println(Arrays.toString(arr));
    }
}
