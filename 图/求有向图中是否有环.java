package com.yizijun.utils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * description
 *
 * @author yizijun
 * @version 1.0.0
 * @since 2019-10-26
 */
public class Algorithm {


    /**
     * 通过入度表（广度优先搜索)找有向图中是否存在环
     * @param graph
     * @return
     */
    public boolean canFinish(int[][] graph) {

        int length = graph.length;


        //1.统计每个节点的入度
        int[] in = new int[length];
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
                if (i != j && graph[i][j] == 1) {
                    in[j]++;
                }
            }
        }

        //2.将入度为0的节点入队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < length; ++i) {
            if (in[i] == 0) {
                queue.add(i);
            }
        }

        int result = length;

        while (!queue.isEmpty()) {
            Integer start = queue.poll();
            //3.将以该节点开始的所有节点的入度减1，如果发现入度为0就入队列
            for (int i = 0; i < length; ++i) {
                if (start != i && graph[start][i] == 1 &&
                        --in[i] == 0) {
                    queue.add(i);
                }
            }
            --result;
        }

        //最后判断是否所有节点都入队列、出队列
        return result == 0;
    }



    /**
     * 通过dfs判断有向图中是否有环
     * 借助一个标志列表 flags，用于判断每个节点 i （课程）的状态：
     * 未被 DFS 访问：i == 0；
     * 已被其他节点启动的DFS访问：i == -1；
     * 已被当前节点启动的DFS访问：i == 1

     * 终止条件：
     * 当 flag[i] == -1，说明当前访问节点已被其他节点启动的 DFS 访问，无需再重复搜索，直接返回 True。
     * 当 flag[i] == 1，说明在本轮 DFS 搜索中节点 i 被第 2 次访问，即 课程安排图有环，直接返回 False。
     * 将当前访问节点 i 对应 flag[i] 置 11，即标记其被本轮 DFS 访问过；
     * 递归访问当前节点 i 的所有邻接节点 j，当发现环直接返回 False；
     * 当前节点所有邻接节点已被遍历，并没有发现环，则将当前节点 flag 置为 -1−1 并返回True
     * @param graph
     * @return
     */
    public boolean canFinish2(int[][] graph) {

        int length = graph.length;

        byte[] flag = new byte[length];

        for (int i = 0; i < length; ++i) {
            if (!dfs(graph, flag, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int[][] graph, byte[] flag, int start) {

        if (flag[start] == 1) {
            return false;
        }

        if (flag[start] == -1) {
            return true;
        }

        flag[start] = 1;

        for (int i = 0; i < graph.length; ++i) {
            if (i != start && graph[start][i] == 1 && !dfs(graph,flag ,i )) {
                return false;
            }
        }

        flag[start] = -1;

        return true;
    }


}
