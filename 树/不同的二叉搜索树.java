package com.yizijun.utils;


/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * @author yizijun
 * @version 1.0.0
 * @since 2019-10-26
 */
public class Algorithm {


    /**
     * 卡塔兰数
     * @return
     */
    public int numTrees3(int n) {
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }


    /**
     * 算法解释参考:https://leetcode-cn.com/problems/unique-binary-search-trees/solution/bu-tong-de-er-cha-sou-suo-shu-by-leetcode/
     *
     * @param n
     * @return
     */
    public int numTrees2(int n) {

        /**
         * G[n]  长度为n的序列的不同二叉搜索树个数
         * F[i]  令f(i)为以i为根的二叉搜索树的个数
         *
         * G[n] = F[1] + F[2] + .... + F[n]
         */

        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                //(G[j - 1] * G[i - j]) = F[j]
                //G[n] = F[1] + F[2] + .... + F[n]
                G[i] += (G[j - 1] * G[i - j]);
            }
        }
        return G[n];
    }


    /**
     * 用递归的做法会导致求很多重复的中间结果
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        return getNumber(0, n - 1);
    }

    private int getNumber(int left, int right) {

        if (left >= right) {
            return 1;
        }

        int totalNumber = 0;

        for (int i = left; i <= right; ++i) {
            int leftNumber = getNumber(left, i - 1);
            int rightNumber = getNumber(i + 1, right);
            totalNumber += (leftNumber * rightNumber);
        }

        return totalNumber;
    }

    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        System.out.println(algorithm.numTrees2(5));
    }
}
