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
     * 求一个字符串中无重复字符最长字串
     *
     * 核心思想：运用滑动窗口的思想，如果当前遍历的元素没有在滑动窗口中，那么滑动窗口右边界往右移动一位。
     * 如果当前遍历元素在滑动窗口中，那么滑动窗口的左边界往右移动一位，直到滑动窗口中不包含当前元素。
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        boolean[] visit = new boolean[256];
        int end = 0;
        for (int i = 0, length = s.length(); i < length; ++i) {
            //当前元素没在滑动窗口中，滑动窗口右边界往右移动一位
            if (!visit[s.charAt(i)]) {
                visit[s.charAt(i)] = true;
                maxLength = Math.max(maxLength, i - end + 1);
            } else {
                //当前元素在滑动窗口中，那么滑动窗口的左边界往右移动一位，直到滑动窗口中不包含当前元素。
                visit[s.charAt(end)] = false;
                ++end;
                --i;
            }
        }
        return maxLength;
    }


    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        System.out.println(algorithm.lengthOfLongestSubstring("abcabcbb"));
    }
}
