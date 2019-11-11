package com.yizijun.utils;


import java.util.Arrays;

/**
 * description
 *
 * @author yizijun
 * @version 1.0.0
 * @since 2019-10-26
 */
public class Algorithm {

    public void sortColors(int[] nums) {
        int left = 0, right = nums.length - 1, index = 0;

        while (index <= right) {

            //与前面的1交换,或者自己与自己交换
            if (nums[index] == 0) {
                int temp = nums[left];
                nums[left] = nums[index];
                nums[index] = temp;
                ++left;
                //这里++index是因为防止第一个数就为0，同时交换了的话index的位置肯定为1，所以++不影响
                ++index;
            } else if (nums[index] == 2) {
                int temp = nums[right];
                nums[right] = nums[index];
                nums[index] = temp;
                --right;
                //这里只所以不++index是因为有可能把0交换到index这个位置，而[start,index]这之间有可能有1。如果跳过index这个数。我们就不能把这个零交换了
            } else {
                ++index;
            }
        }
    }


    /**
     * 思路: 统计0，1元素的个数，然后重写原数组
     * @param nums
     */
    public void sortColors2(int[] nums) {
        int oneTimes = 0, zeroTimes = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 1) {
                oneTimes++;
            } else if (nums[i] == 0) {
                ++zeroTimes;
            }
        }

        for (int i = 0; i < nums.length; ++i) {
            if (zeroTimes > 0) {
                nums[i] = 0;
                --zeroTimes;
            } else if (oneTimes > 0) {
                nums[i] = 1;
                --oneTimes;
            } else {
                nums[i] = 2;
            }
        }
    }


    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        int[] array = new int[]{2,0,1};
        algorithm.sortColors(array);
        System.out.println(Arrays.toString(array));
    }
}
