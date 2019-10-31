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
     * 求两个列表的最长公共子序列，并返回最长的公共子序列
     *
     * 算法详细解释参考链接  https://www.jianshu.com/p/4b5d7deef7fe
     * @param str1
     * @param str2
     * @return
     */
    public String getMaxPublicSubSequence(String str1, String str2) {

        int str1Length = str1.length(), str2Length = str2.length();

        //这里为了好计算，给dp数组多初始化一行和一列
        int[][] dp = new int[str1Length + 1][str2Length + 1];



        for (int i = 1; i <= str1Length; ++i) {
            for (int j = 1; j <= str2Length; ++j) {
                //如果当前比较的两个元素相等，那么当前最长的公共子序列长度为 dp[i - 1][j - 1] + 1
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    //否则就要看dp[i - 1][j] 和 dp[i][j - 1]谁大了
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }

            }
        }

        /**
         * 找到最长的子序列，通过自底向上的方法找到最长
         */
        int i = str1Length, j = str2Length;
        String result = "";
        while (i >= 1 && j >= 1) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                result = str1.charAt(i - 1) + result;
                --i;
                --j;
            }  else if (dp[i - 1][j] > dp[i][j - 1]) {
                --i;
            } else {
                --j;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        System.out.println(algorithm.getMaxPublicSubSequence("BDCABA","ABCBDAB"));
    }
}
