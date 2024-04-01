package Algorithm;

import java.util.Arrays;

/**
 * @author hx
 * @version 1.0
 * @date 2024/4/1 11:06
 */
public class CountingSort {
    /**
     * 计数排序
     *最佳情况：T(n) = O(n+k)  最差情况：T(n) = O(n+k)  平均情况：T(n) = O(n+k)
     * @param array
     * @return
     */
    public static int[] CountingSort(int[] array) {
        if (array.length == 0) return array;
        int bias, min = array[0], max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max)
                max = array[i];
            if (array[i] < min)
                min = array[i];
        }
        bias = 0 - min;
        int[] bucket = new int[max - min + 1];
        Arrays.fill(bucket, 0);
        for (int i = 0; i < array.length; i++) {
            bucket[array[i] + bias]++;
        }
        int index = 0, i = 0;
        while (index < array.length) {
            if (bucket[i] != 0) {
                array[index] = i - bias;
                bucket[i]--;
                index++;
            } else
                i++;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] num = {22,44,66,88,99,35,75,1};
        int[] num2 = CountingSort(num);
        for(int i: num2){
            System.out.println(i);
        }
    }

}
