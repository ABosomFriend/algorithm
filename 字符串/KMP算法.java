package com.yizijun.utils;



/**
 *
 * @author yizijun
 * @version 1.0.0
 * @since 2019-10-26
 */
public class Algorithm {

    /**
     * kmp算法的关键是求得next数组
     *
     * @param s
     * @param regex
     * @return
     */
    public boolean kmp(String s, String regex) {
        int[] next = getNext(regex);
        int sIdx = 0, rIdx = 0;
        while (sIdx < s.length() && rIdx < regex.length()) {
            if (rIdx == -1 || s.charAt(sIdx) == regex.charAt(rIdx)) {
                ++sIdx;
                ++rIdx;
            } else {
                rIdx = next[rIdx];
            }
        }

        if (rIdx == regex.length()) {
            return true;
        }

        return false;
    }

    /**
     * 获取next数组
     *
     * @param regex
     * @return
     */
    private int[] getNext(String regex) {
        int[] next = new int[regex.length()];
        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < regex.length() - 1) {
            if (k == -1 || regex.charAt(j) == regex.charAt(k)) {
                ++k;
                ++j;
                next[j] = k;
            } else {
                k = next[k];
            }
        }
        return next;
    }



    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        System.out.println(algorithm.kmp("abcdfetdgjlaljfiejljt", "iejljt"));
    }


}
