package Recommend;

/**
 * @author hx
 * @version 1.0
 * @date 2024/8/22 9:55
 */
public class Solution {
    public long findMaximumNumber(long k, int x){
        long Eprice=0;
        int n = 0;
        while (Eprice<=k){

            //获取当前价值
            long price=getPrice(n,x);
            //价值和
            n++;
            Eprice+=price;

        }
        return n-2;
    }

    public long getPrice(int num, int x){
        String binaryString = Integer.toBinaryString(num);
        char[] binaryArray = binaryString.toCharArray();
        int i =0;
        int j=1;
        for(int ii=0;ii<binaryArray.length/2;ii++){
            char temp = binaryArray[ii];
            binaryArray[ii] = binaryArray[binaryArray.length-1-ii];
            binaryArray[binaryArray.length-1-ii] = temp;
        }
        for(char a : binaryArray){
//            System.out.print(a);
            if(j%x==0){
                if(a=='1'){
                    i++;
                }
            }
            j++;
        }
//        System.out.println("==");
        return i;
    }

    public static void main(String[] args) {
        Solution sl = new Solution();
        long l = sl.findMaximumNumber(32785393303l,5);
        System.out.println(l);
    }

}
