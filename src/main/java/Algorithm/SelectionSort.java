package Algorithm;

import java.util.Arrays;

/**
 * @author hx
 * @version 1.0
 * @date 2022/8/12 16:21
 */
public class SelectionSort {

    /**
     * 选择排序：找到最小或者最大，替换当前啊循环的位置，列表以此循环。
     * @param array
     * @return
     */
    public static int[] selectionSort(int[] array) {
        if (array.length == 0)
            return array;
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) //找到最小的数,或者找到最大的数
                    minIndex = j; //将最小数的索引保存
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] num = {1,22,44,66,88,99,35,75};
        System.out.println(Arrays.toString(selectionSort(num)));

    }

}
