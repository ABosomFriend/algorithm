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
     * 获取旋转数组中给定值的下标
     * 1.旋转数组中可能有重复元素
     * @param array
     * @param target
     * @return
     */
    public int getRotateArrayTargetIndex(int[] array, int target) {

        int length = array.length;
        int left = 0, right = length - 1;

        while (left <= right) {
            int middle = (left + right) >> 1;
            //如果在中间节点找到了就直接返回
            if (array[middle] == target) {
                return middle;
            }

            /**
             * 如果left，middle，right三个值相等。我们并不能判断是前半段递增还是后半段递增
             * 所以就选择依次遍历
             * 例如: 2 1 2 2 2   2 2 2 1 2
             */
            if (array[left] == array[middle] && array[middle] == array[right]) {
                --right;
                continue;
            }

            //前半段是非递减的  4 4 3
            if (array[middle] >= array[left]) {

                //说明目标值在前半段
                if (target < array[middle] && target >= array[left]) {
                    right = middle - 1;
                } else {
                    //说明目标值在后半段
                    left = middle + 1;
                }

                //后半段是递增的
            } else {
                //说明目标值在后半段
                if (target > array[middle] && target <= array[right]) {
                    left = middle + 1;
                } else {
                    //目标值在前半段
                    right = middle - 1;
                }
            }


        }

        //没找到目标值
        return -1;
    }


    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        int[] array = new int[]{2,1,2,2,2};
        int target = 1;
        System.out.println(algorithm.getRotateArrayTargetIndex(array, target));
    }
}
