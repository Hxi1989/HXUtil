package Test;

/**
 * @author hx
 * @version 1.0
 * @date 2022/10/8 23:01
 */
public class SZTest {

    /**
     * 形参 与 实参
     * @param args
     */
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5};

        ti(a);

        for(int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }

        String s = "Hello";

        ts(s);

        System.out.println(s);

    }

    public static void ti(int[] b){
        b[0] = 6 ;
    }

    public static void ts(String ss){
        ss = "Bye" ;
    }
}
