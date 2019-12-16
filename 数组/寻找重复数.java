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
     * 寻找重复数
     * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int left = 1, right = nums.length;
        while (left < right) {
            int middle = (left + right) >> 1;
            //寻找数字在[left,middle]中的个数
            int total = 0;
            for (int i = 0; i < nums.length; ++i) {
                if (nums[i] >= left && nums[i] <= middle) {
                    ++total;
                }
            }


            if (total > middle - left + 1) {
                //说明重复的数字在[left,middle]这个区间
                right = middle;
            } else {
                //说明重复的数字在(middle,right]这个区间
                left = middle + 1;
            }
        }

        return left;
    }

    /**
     * 原理：如果存在重复的数字，那么下标一定会成为一个环。
     * 例如: 5 3 4 5 1 2
     * 形成的环 0-->5-->2-->4-->1-->3-->5
     * 这个时候就可以用快慢指针来做了
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {

        int slow = 0, fast = 0;

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        int result = 0;
        while (result != fast) {
            result = nums[result];
            fast = nums[fast];
        }

        return result;

    }


}
