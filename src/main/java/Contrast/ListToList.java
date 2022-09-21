package Contrast;

import java.util.*;

/**
 * @author hx
 * @version 1.0
 * @date 2022/9/20 11:45
 */
public class ListToList {
    public static void main(String[] args) {

        List listOne = new ArrayList<>(); //数据一
        List listTwo = new ArrayList<>(); //数据二

        Random random = new Random();
//        random.nextInt(100);

        for(int i = 0 ; i < 100000 ; i++){
            listOne.add("test"+":"+random.nextInt(100000)); //添加测试数据一
        }

        for(int i = 0 ; i < 100000 ; i++){
            listTwo.add("test"+":"+random.nextInt(100000)); //添加测试数据二
        }

        System.out.println(listOne+"\r\n"+listTwo);

        //需要确定 list 是否有重复
        Set setOne = new HashSet<>();
        setOne.addAll(listOne);
        listOne.clear();
        listOne.addAll(setOne);
        Set setTwo = new HashSet<>();
        setTwo.addAll(listTwo);
        listTwo.clear();
        listTwo.addAll(setTwo);

        System.out.println("\r\n"+listOne+"\r\n"+listTwo);

        ListToList ltl = new ListToList();
        List ll = ltl.ltlequal(listOne,listTwo);
        System.out.println(ll);

        List lls = ltl.ltldef(listOne,listTwo);
        System.out.println(lls);

        List llss = ltl.ltldef(listTwo,ll);
        System.out.println(llss);


    }

    /**
     * //需求：以数据一为基础，判断数据二中数据是否重复,输出重复数据
     * @param listOne
     * @param listTwo
     * @return
     */
    public List ltlequal(List listOne,List listTwo){

        Set setOne = new HashSet<>();
        setOne.addAll(listOne);  //可以通过addAll把list集合转换为set集合
        List listEqual = new ArrayList<>(); //存放缺失的数据
        int setOneSize = setOne.size(); //数据一大小
        for(int i = 0 ; i < listTwo.size() ; i++){
            setOne.add(listTwo.get(i));
            if(setOne.size() == setOneSize ){  //判断大小是否改变 ，改变表示数据缺失，没改变表示数相同。
                listEqual.add(listTwo.get(i)); //存放缺失了哪些数据
                setOneSize = setOne.size(); //更新基础集合大小
            }
        }

        //打印缺失数据
//        for(int i = 0 ; i < listEqual.size() ; i++)
//        {
//            System.out.println(listEqual.get(i));
//        }
        return listEqual;

    }

    /**
     * //需求，以数据一为基础，对比数据二是否数据缺失，输出缺失数据
     * @param listOne
     * @param listTwo
     * @return
     */
    public List ltldef(List listOne,List listTwo){

        Set setTwo = new HashSet<>();
        setTwo.addAll(listTwo); //可以通过addAll把list集合转换为set集合
        List listNot = new ArrayList<>(); //存放缺失的数据
        int setTwoSize = setTwo.size(); //数据一大小
        for(int i = 0 ; i < listOne.size() ; i++){
            setTwo.add(listOne.get(i));
            if(setTwo.size() > setTwoSize ){ //判断大小是否改变 ，改变表示数据缺失
                listNot.add(listOne.get(i)); //存放缺失了哪些数据
                setTwoSize = setTwo.size(); //更新基础集合大小
            }
        }

        //打印缺失数据
//        for(int i = 0 ; i < listNot.size() ; i++)
//        {
//            System.out.println(listNot.get(i));
//        }
        return listNot;

    }
}
