package Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hx
 * @version 1.0
 * @date 2022/9/23 16:25
 */
public class TestOne {
    public static void main(String[] args) {
//        String s = " ";
//        System.out.println(s.length());

//        String ss = "1,2,3,4,5";
//        Long l = Long.valueOf(ss.split(",").length);
//        System.out.println(l);

        List l = new ArrayList();
        l.add("a");
        l.add("b");
        l.add("c");

        System.out.println(String.join(",",l));

        String a = List.of(l.toString()).get(0);
        System.out.println(a);
    }
}
