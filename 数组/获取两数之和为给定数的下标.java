package com.yizijun.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author yizijun
 * @version 1.0.0
 * @since 2019-10-26
 */
public class Algorithm {

    /**
     * 两数相加为指定值。
     * 用集合存储被减数结果必须保证原数组中没有重复的值
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {

        Map<Integer,Integer> map = new HashMap<>(10);
        int length = nums.length;
        for (int i = 0; i < length; ++i) {
            //用map来存储第二数的值和第一个数的下标
            map.put(target - nums[i], i);
        }

        for (int i = 0; i < length; ++i) {
            //保证能获取到第二个数，并且第二个数不能和第一个数下标相同.
            if (map.containsKey(nums[i]) && map.get(nums[i]) != i) {
                //保证前后两个数下标的准确性
                if (map.get(nums[i]) < i) {
                    return new int[]{map.get(nums[i]), i};
                } else {
                    return new int[]{i, map.get(nums[i])};
                }
            }
        }

        return new int[]{};
    }


    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        int[] array = new int[]{1,4,5,7,8,9};
        int target = 8;
        System.out.println(Arrays.toString(algorithm.twoSum(array, target)));
    }
}
