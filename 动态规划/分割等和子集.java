package com.yizijun.utils;


/**
 * @author yizijun
 * @version 1.0.0
 * @since 2019-10-26
 */
public class Algorithm {


    /**
     * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * 把这个问题转化一下，也就是问: 是否可以从这个数组中挑选出一些正整数，使得这些数的和等于整个数组元素的和的一半,而且每个数只能够用一遍
     * 这就是0-1背包的变种
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        //如果sum为奇数直接返回false
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum >> 1;
        boolean[][] dp = new boolean[nums.length][target + 1];

        //初始化第0行
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }

        //初始化第0列, 主要用来匹配j == nums[i]
        for (int i = 0; i < nums.length; ++i) {
            dp[i][0] = true;
        }

        for (int i = 1; i < nums.length; ++i) {
            for (int j = 1; j <= target; ++j) {

                //先赋值上一步的结果
                dp[i][j] = dp[i - 1][j];

                if (j >= nums[i]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }

            }
        }

        return dp[nums.length - 1][target];
    }


    /**
     * 优化dp的空间复杂度
     *
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        //如果sum为奇数直接返回false
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum >> 1;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 0; i < nums.length; ++i) {
            for (int j = target; j >= nums[i]; --j) {
                dp[j] = dp[j] || dp[j - nums[i]];

                if (dp[target]) {
                    return true;
                }
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {


    }


}
