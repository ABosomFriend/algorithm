package com.yizijun.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author yizijun
 * @version 1.0.0
 * @since 2019-10-26
 */
public class Algorithm {


    public int lengthOfLIS2(int[] nums) {
        int result = 0;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //dp[i]表示以nums[i]结尾的最长子序列长度，必须包含nums[i]这个元素
            dp[i] = Math.max(dp[i], 1);
            //最后找到最大值
            result = Math.max(result, dp[i]);
        }
        return result;
    }


    public int lengthOfLIS(int[] nums) {
        int result = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            if (list.size() == 0 || nums[i] > list.get(list.size() - 1)) {
                list.add(nums[i]);
                result = Math.max(result, list.size());
            } else {
                list.set(findIndex(list, nums[i]), nums[i]);
            }
        }
        return result;
    }

    /**
     * 找到大于等于target的最小元素的下标
     * @param list
     * @param target
     * @return
     */
    private int findIndex(List<Integer> list, int target) {

        int left = 0, right = list.size() - 1;
        while (left < right) {
            int middle = (left + right) >> 1;
            if (list.get(middle) < target) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        return left;
    }




    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(2);
        ListNode listNode4 = new ListNode(1);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        System.out.println(solution.lengthOfLIS(new int[]{4,10,4,3,8,9}));

    }


}
