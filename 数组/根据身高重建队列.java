package com.yizijun.utils;


import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author yizijun
 * @version 1.0.0
 * @since 2019-10-26
 */
public class Algorithm {

    /**
     * 根据身高重建队列
     * 题目地址:https://leetcode-cn.com/problems/queue-reconstruction-by-height/
     *
     * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。

     注意：
     总人数少于1100人。

     示例

     输入:
     [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

     输出:
     [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
     */


    /**
     * 解题思路：先排序再插入
     * 1.排序规则：按照先H高度降序，K个数升序排序
     * 2.遍历排序后的数组，根据K插入到K的位置上
     * <p>
     * 核心思想：高个子先站好位，矮个子插入到K位置上，前面肯定有K个高个子，矮个子再插到前面也满足K的要求
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
            }
        });
        List<int[]> result = new LinkedList<>();
        for (int[] i : people) {
            result.add(i[1], i);
        }

        return result.toArray(new int[][]{});
    }

    public static void main(String[] args) {

        System.out.println(0.1 + 0.2);
    }


}
