package Recommend;

/**
 * @author hx
 * @version 1.0 学习用
 * @date 2024/8/22 11:26
 */
public class ModeSolution {
    public long findMaximumNumber(long k, int x) {
        long l = 1, r = (k + 1) << x;
        while (l < r) {
            long m = (l + r + 1) / 2;
            if (accumulatedPrice(x, m) > k) {
                r = m - 1;
            } else {
                l = m;
            }
        }
        return l;
    }

    public long accumulatedPrice(int x, long num) {
        long res = 0;
        int length = 64 - Long.numberOfLeadingZeros(num);
        for (int i = x; i <= length; i += x) {
            res += accumulatedBitPrice(i, num);
        }
        return res;
    }

    public long accumulatedBitPrice(int x, long num) {
        long period = 1L << x;
        long res = period / 2 * (num / period);
        if (num % period >= period / 2) {
            res += num % period - (period / 2 - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        ModeSolution ms = new ModeSolution();
        System.out.println(ms.findMaximumNumber(32785393306933l,5));
    }
}
