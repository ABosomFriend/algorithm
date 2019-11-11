package com.yizijun.utils;


/**
 * description
 *
 * @author yizijun
 * @version 1.0.0
 * @since 2019-10-26
 */
public class Algorithm {

    /**
     * 通过矩阵乘法求斐波拉契数列第n位
     *
     * 参考题解 https://leetcode-cn.com/problems/climbing-stairs/solution/pa-lou-ti-by-leetcode/
     * @param n
     * @return
     */
    public int climbStairs(int n) {

        //用来表示F(1) = {{f(1),f(0)},{f(0),f(-1)}}
        int [][] basic = new int[][]{{1,1},{1,0}};
        int[][] result = calculate(basic, n);
        return result[0][0];
    }

    private int[][] calculate(int[][] basic, int n) {
        //定义一个基本矩阵，相当于常数1
        int[][] res = new int[][]{{1,0},{0,1}};

        //快速幂相乘
        while (n > 0) {

            if ((n & 1) == 1) {
                res = calcuResult(res, basic);
            }
            basic = calcuResult(basic,basic);

            n >>= 1;
        }

        return res;
    }

    /**
     * 矩阵乘法
     * @param a
     * @param b
     */
    private  int[][] calcuResult(int[][] a, int[][] b) {
        int temp[][] = new int[2][2];
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                temp[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }

        return temp;
    }


    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        System.out.println(algorithm.climbStairs(4));
    }
}
