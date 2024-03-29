package Algorithm;

import java.util.Arrays;

/**
 * @author hx
 * @version 1.0
 * @date 2022/8/22 18:43
 */
public class InsertionSort {
    /**
     * 插入排序
     * @param array
     * @return
     */
    public static int[] insertionSort(int[] array) {
        if (array.length == 0)
            return array;
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] num = {99,35,75,1};
        System.out.println(Arrays.toString(num));
        Long l1 = System.currentTimeMillis();
//        System.out.println(l1);
        System.out.println(Arrays.toString(insertionSort(num)));
//        System.out.println(System.currentTimeMillis()-l1);
    }
}
