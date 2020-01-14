package com.yizijun.utils;


import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author yizijun
 * @version 1.0.0
 * @since 2019-10-26
 */
public class Algorithm {

    /**
     * 换一种思路，实际上不需要加法，只需要 nums[0] + nums[1] + nums[2] - nums[0]，
     * 再写的清晰一些，用sum[i]表示从0到i所有元素的和，那么nums[1] + nums[2] = sum[2] - sum[0]。
     * 这里的sum[i]实际上就是一种前缀和的思路，只需要遍历一次就可以知道所有的前缀和，
     * 存在map里，用的时候就可以实现在常数时间的查找。
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> helper = new HashMap<>();
        helper.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (helper.containsKey(sum - k)) {
                count += helper.get(sum - k);
            }

            helper.put(sum, helper.getOrDefault(sum,0 ) + 1);
        }
        return count;
    }



    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
    }


}
