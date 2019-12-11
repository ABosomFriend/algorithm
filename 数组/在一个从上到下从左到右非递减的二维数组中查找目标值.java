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
     * 从一个二维数组中找到目标值，该二维数组从左到右数据非递减，从上到下数组非递减
     * 思路: 以右上角点为顶点, 以顶点为中点分成了x和y方向两个非递减的序列，这样可以通过二分查找，然后再每层递进查找.
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix.length <= 0 || matrix[0].length <= 0) {
            return false;
        }


        for (int i = 0; i < matrix.length; ++i) {

            //防止下标越界
            if (matrix[0].length - i - 1 < 0) {
                break;
            }

            int middle = matrix[i][matrix[0].length - i - 1];
            if (middle == target) {
                return true;
            } else if (middle < target) {
                int left = i + 1, right = matrix.length - 1;
                while (left <= right) {
                    middle = (left + right) >> 1;
                    if (matrix[middle][matrix[0].length - i - 1] == target) {
                        return true;
                    } else if (matrix[middle][matrix[0].length - i - 1] > target) {
                        right = middle - 1;
                    } else {
                        left = middle + 1;
                    }

                }
            } else {
                int left = 0, right = matrix[0].length - i - 1;
                while (left <= right) {
                    middle = (left + right) >> 1;
                    if (matrix[i][middle] == target) {
                        return true;
                    } else if (matrix[i][middle] > target) {
                        right = middle - 1;
                    } else {
                        left = middle + 1;
                    }

                }
            }
        }

        return false;
    }


    /**
     * 从一个二维数组中找到目标值，该二维数组从左到右数据非递减，从上到下数组非递减
     *
     * 思路:从左下角或者右上角迭代这个二维数组，找到目标值,不能够从左上角或者右下角迭代
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix2(int[][] matrix, int target) {

        int x = matrix.length;

        if (x <= 0) {
            return false;
        }

        int y = matrix[0].length;

        if (y <= 0) {
            return false;
        }

        //从左下角迭代这个数组

        int cx = x - 1, cy = 0;

        while (cx >= 0 && cy < y) {
            if (matrix[cx][cy] == target) {
                return true;
            } else if (matrix[cx][cy] < target) {
                cy++;
            } else {
                cx--;
            }
        }

        return false;
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

        System.out.println(solution.searchMatrix(new int[][]{{-1},{-1}}, -2));

    }


}
