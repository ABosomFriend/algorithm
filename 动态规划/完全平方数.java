package com.yizijun.utils;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 完全平方数
 *
 * @author yizijun
 * @version 1.0.0
 * @since 2019-10-26
 */
public class Algorithm {


    /**
     * 假设最小公式值m = ƒ(n)
     * 那么n的值满足下列公式 ∑(A[i] * A[i]) = n
     * 令 k 为满足最小值 m 的时候，最大的平方数  。 令  d + k * k; = n ;  d >= 0;
     * 注意：一定要是满足m最小的时候的k值,一味的取最大平方数,就是贪心算法了
     * 得出 f(d) + f(k*k) = f(n);
     * 显然 f(k*k) = 1; 则  f(d) + 1 = f(n); 因为 d = n - k*k;
     * 则可以推出ƒ(n - k * k) + 1 = ƒ(n) ;  且 k * k <= n;
     * @param n
     * @return
     */
    public int numSquares(int n) {

        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; ++i) {
            /**
             * 最坏情况i个1相加
             */
            dp[i] = i;
            for (int k = 1; i - (k * k) >= 0; ++k) {
                dp[i] = Math.min(dp[i], dp[i - k * k] + 1);
            }
        }

        return dp[n];
    }


    /**
     * 运用bfs的思想求完全平方数
     * 思路: https://leetcode-cn.com/problems/perfect-squares/solution/yan-du-you-xian-sou-suo-java-by-1874-14/
     * @param n
     * @return
     */
    public int numSquares2(int n) {

        Queue<Node> queue = new LinkedList<>();
        boolean[] visit = new boolean[n + 1];
        queue.add(new Node(n, 0));

        //用来标记访问过的节点，后续就不用再次访问了
        visit[n] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int i = 1;; ++i) {

                int currentValue = node.value - i * i;

                if (currentValue < 0) {
                    break;
                }

                //当第一次出现0时就是最短路径
                if (currentValue == 0) {
                    return node.step + 1;
                }

                if (!visit[currentValue]) {
                    queue.add(new Node(currentValue, node.step + 1));
                    visit[currentValue] = true;
                }
            }
        }

        return 0;
    }

    class Node {

        private int value;

        private int step;

        public Node(int value, int step) {
            this.value = value;
            this.step = step;
        }
    }


    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(2);
        ListNode listNode4 = new ListNode(1);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        System.out.println(algorithm.numSquares2(12));

    }


}
