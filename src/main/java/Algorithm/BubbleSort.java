package Algorithm;

import java.util.Arrays;

/**
 * @author hx
 * @version 1.0
 * @date 2022/8/12 14:38
 */
public class BubbleSort {

    /**
     * 冒泡排序 学习用
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array){
        if(array.length == 0) {
            return array;
        }else{
            for(int i=0;i<array.length;i++){
                for(int j=0;j<array.length - 1 - i;j++){
                    if(array[j] < array[j+1]){ //判断，如果后一位大于前一位，进行位置调换。 一种是由大到小，一种由小到大
                        int temp = array[j+1];
                        array[j+1] = array[j];
                        array[j] = temp;
                    }
                }
            }
            return array;
        }
    }

    public static void main(String[] args) {
        int[] num = {1,22,44,66,88,99,35,75};
        System.out.println(Arrays.toString(bubbleSort(num)));

    }

}
