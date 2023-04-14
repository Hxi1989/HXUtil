package Algorithm;

import java.util.Arrays;

/**
 * @author hx
 * @version 1.0
 * @date 2023/4/14 14:53
 */
public class ShellSort {
    /**
     * 希尔排序
     *
     * @param array
     * @return
     */
    public static int[] ShellSort(int[] array) {
        int len = array.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = array[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = temp;
            }
            gap /= 2;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] num = {22,44,66,88,99,35,75,1};
        System.out.println(Arrays.toString(ShellSort(num)));
    }
}
