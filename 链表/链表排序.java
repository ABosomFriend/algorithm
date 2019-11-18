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
     * 利用归并排序的思想排序链表
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head, slow = head;

        /**
         * 这里用pre指针来分割，而不用slow指针来分隔，防止如果只有两个节点时死循环,导致栈深度过大异常
         */
        ListNode pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        //从pre切断
        ListNode middle = pre.next;
        pre.next = null;

        //拆左边
        head = sortList(head);
        //拆右边
        middle = sortList(middle);

        //开始合并
        ListNode result = new ListNode(-1), temp = result;

        while (head != null && middle != null) {
            if (head.val <= middle.val) {
                result.next = head;
                head = head.next;
            } else {
                result.next = middle;
                middle = middle.next;
            }
            result = result.next;
        }

        while (head != null) {
            result.next = head;
            head = head.next;
            result = result.next;
        }

        while (middle != null) {
            result.next = middle;
            middle = middle.next;
            result = result.next;
        }

        return temp.next;
    }

    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(1);
        ListNode listNode3 = new ListNode(5);
        ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        ListNode result = algorithm.sortList(listNode1);

        System.out.println(result);
    }
}
