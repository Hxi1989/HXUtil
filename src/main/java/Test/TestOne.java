package Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

//        List l = new ArrayList();
//        l.add("a");
//        l.add("b");
//        l.add("c");
//
//        System.out.println(String.join(",",l));
//
//        String a = List.of(l.toString()).get(0);
//        System.out.println(a);

//        String s = "https://a/a-prd/images/2023-03-15/a.png?AWSAccessKeyId=a&Expires=a&Signature=a%2BwiGJU%3D";
//
//        System.out.println(s.substring(0,s.indexOf("?")));

//        Date a = new Date(2023,4,1);
//        Date b = new Date();
//        Date c = new Date();
//
//        System.out.println(a.toString()+"\n"+b.toString()+"\n"+a.before(b));

        String s = "1234";
        char a = (char) (97+ new Random().nextInt(25));
        char b = (char) (97+ new Random().nextInt(25));
        char c = (char) (65+ new Random().nextInt(25));
        char d = (char) (65+ new Random().nextInt(25));
        System.out.println(a+b);
        System.out.println(""+a+c+s+b+d);
    }
}
