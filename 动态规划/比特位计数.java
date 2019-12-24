package com.yizijun.utils;

/**
 *
 *
 * @author yizijun
 * @version 1.0.0
 * @since 2019-10-26
 */
public class Algorithm {


    /**
     * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
     * @param num
     * @return
     */
    public int[] countBits(int num) {

        int[] res = new int[num + 1];

        for (int i = 1; i <= num; ++i) {
            int total = 0, j = i;
            while (j > 0) {
                if((j & 1) == 1) {
                    ++total;
                }
                j >>= 1;
            }
            res[i] = total;
        }

        return res;
    }

    /**
     * 最后设置位是从右到左第一个为1的位。使用 x &= x - 1 将该位设置为0，就可以得到以下状态转移函数：
     * @param num
     * @return
     */
    public int[] countBits2(int num) {

        int[] res = new int[num + 1];

        for (int i = 1; i <= num; ++i) {
            res[i] = res[i & (i - 1)] + 1;
        }

        return res;
    }

    /**
     * 奇数：二进制表示中，奇数一定比前面那个偶数多一个 1，因为多的就是最低位的 1。
     * 偶数：二进制表示中，偶数中 1 的个数一定和除以 2 之后的那个数一样多。因为最低位是 0，除以 2 就是右移一位，也就是把那个 0 抹掉而已，所以 1 的个数是不变的。
     * @param num
     * @return
     */
    public int[] countBits3(int num) {

        int[] res = new int[num + 1];

        for (int i = 1; i <= num; ++i) {
            //如果是奇数
            if ((i & 1) == 1) {
                res[i] = res[i - 1] + 1;
            } else {
                res[i] = res[i >> 1];
            }
        }

        return res;
    }



}
