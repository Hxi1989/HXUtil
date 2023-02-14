package Test;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author OPENAI
 * @version 1.0
 * @date 2023/2/14 9:57
 * chatgpt 红包随机代码
 */
public class RedPacket {
//    public static void main(String[] args) {
//        //创建一个红包数组
//        ArrayList<Integer> list = new ArrayList<Integer>();
//        //设置红包总金额
//        int sum = 1000;
//        //每个红包最小金额
//        int min = 1;
//        //计算最大红包
//        int max = sum/100*2;
//        //循环100次
//        for (int i = 0; i < 100; i++) {
//            //产生随机数
//            int random = new Random().nextInt(max) + min;
//            //红包总金额减少
//            sum -= random;
//            //将红包加入集合
//            list.add(random);
//        }
//        //最后一个红包金额为剩余金额
//        list.add(sum);
//        //循环输出红包金额
//        for (Integer integer : list) {
//            System.out.println(integer);
//        }
//    }
///////////////////////////////////////////////////
//    public static void main(String[] args) {
//        int totalMoney = 100; // 总金额
//        int count = 100; // 红包数量
//        int[] result = create(totalMoney, count);
//        for (int i = 0; i < result.length; i++) {
//            System.out.println("第" + (i + 1) + "个红包：" + result[i] + "元");
//        }
//    }
//
//    public static int[] create(int totalMoney, int count) {
//        int[] result = new int[count];
//        int lastMoney = totalMoney;
//        Random random = new Random();
//        for (int i = 0; i < count - 1; i++) {
//            // 计算出最大可调度金额
//            int max = lastMoney - (count - 1 - i);
//            // 生成随机红包
//            int money = random.nextInt(max);
//            // 对红包金额进行处理，防止出现0元红包
//            money = money <= 0 ? 1 : money;
//            // 将调度的金额存入数组
//            result[i] = money;
//            // 计算剩余金额
//            lastMoney -= money;
//        }
//        // 将最后剩余的金额存入数组
//        result[count - 1] = lastMoney;
//        return result;
//    }
/////////////////////////////////////////////////////////////////
//    //生成红包金额
//    public static int getRedpackets(int totalMoney, int count) {
//        if (totalMoney < 0 || count < 0) {
//            return 0;
//        } else {
//            if (totalMoney < count) {
//                return 1;
//            } else {
//                int max = (int) (totalMoney / count * 2);
//                Random random = new Random();
//                return random.nextInt(max) + 1;
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        int totalMoney = 100;
//        int count = 100;
//        int[] redPackets = new int[count];
//        int redMoney;
//        for (int i = 0; i < count; i++) {
//            redMoney = getRedpackets(totalMoney, count - i);
//            redPackets[i] = redMoney;
//            totalMoney -= redMoney;
//            System.out.println("第" + (i + 1) + "个红包：" + redMoney + "元，余额：" + totalMoney + "元");
//        }
//    }
/////////////////////////////////////////////////////////////
//    //总金额以分为单位
//    private static final int MONEY = 1000;
//    //支付红包个数
//    private static final int NUMBER = 10;
//
//    //生成红包最小金额
//    private static final int MINMONEY = 1;
//
//    //最大的红包金额
//    private static final int MAXMONEY = 200;
//
//    public ArrayList<Integer> divideRedPacket(){
//        ArrayList<Integer> list = new ArrayList<Integer>();
//        //总金额
//        int money = MONEY;
//        //剩余金额
//        int lastMoney = MONEY;
//        //每个红包最大的金额为剩余金额/剩余人数*2
//        int max = lastMoney / (NUMBER - list.size()) * 2;
//        max = max > MAXMONEY ? MAXMONEY : max;
//        Random random = new Random();
//        for(int i = 0;i < NUMBER - 1;i++){
//            //生成一个随机数
//            int redPacket = random.nextInt(max) + MINMONEY;
//            //剩余金额
//            lastMoney -= redPacket;
//
//            list.add(redPacket);
//        }
//        list.add(lastMoney);
//        return list;
//    }
//
//    public static void main(String[] args){
//        RedPacket redPacket = new RedPacket();
//        ArrayList<Integer> list = redPacket.divideRedPacket();
//        System.out.println(list);
//    }
/////////////////////////////////////////////
}
