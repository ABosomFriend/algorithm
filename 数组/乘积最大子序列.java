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
     * 乘积最大子序列,这个序列是连续的
     * 题解:采用双向遍历法
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int result = nums[0];
        int leftMax = 1;
        for (int i = 0; i < nums.length; ++i) {
            leftMax *= nums[i];
            result = Math.max(result, leftMax);
            //遇到0置为1
            leftMax = leftMax == 0 ? 1 : leftMax;
        }

        int rightMax = 1;
        for (int i = nums.length - 1; i >= 0; --i) {
            rightMax *= nums[i];
            result = Math.max(result, rightMax);
            //遇到0置为1
            rightMax = rightMax == 0 ? 1 : rightMax;
        }

        return result;
    }

    /**
     * 采用动态规划
     * @param nums
     * @return
     */
    public int maxProduct2(int[] nums) {
        int result = nums[0], max = nums[0], min = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            /**
             * 如果当前数小于0，交换max和min
             * 由于存在负数，那么会导致最大的变最小的，最小的变最大的
             */
            if (nums[i] < 0) {
                int temp = max;
                max = min;
                min = temp;
            }

            max = Math.max(max * nums[i], nums[i]);
            min = Math.min(min * nums[i],nums[i]);

            result = Math.max(max,result);
        }

        return result;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(1);
        ListNode listNode3 = new ListNode(5);
        ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        System.out.println(solution.maxProduct(new int[]{-4,-3,-2}));

    }
}
