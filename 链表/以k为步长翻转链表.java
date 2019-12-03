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
     * 以k为步长翻转链表
     * 例如: 1 > 2 > 3 > 4 > 5 > 6  翻转后  3 > 2 > 1 > 6 > 5 > 4
     * @param listNode
     * @param k
     * @return
     */
    public ListNode reserveListNode(ListNode listNode, int k) {

        if (listNode == null) {
            return null;
        }

        int index = 0;
        ListNode pre = null, current = listNode, first = listNode;
        while (current != null && index < k) {
            ListNode next = current.next;
            current.next = pre;
            pre = current;
            current = next;
            ++index;
        }


        //继续翻转接下来的链表
        first.next = reserveListNode(current, k);

        //返回翻转后的头节点
        return pre;
    }


}
