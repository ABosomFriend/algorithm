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
     * 验证链表是否为回文链表
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {


        if (head == null || head.next == null) {
            return true;
        }

        ListNode pre = null;
        ListNode slow = head, fast = head;

        //翻转前面的链表，切分为两个链表
        while (fast != null && fast.next != null) {
            ListNode slowNext = slow.next;
            ListNode fastNext = fast.next;
            slow.next = pre;
            pre = slow;
            slow = slowNext;
            fast = fastNext.next;
        }

        //注意这里是以slow来截断链表
        if (fast == null) {
            //偶数个节点
            fast = slow;
            slow = pre;
        } else {
            //基数个节点
            fast = slow.next;
            slow = pre;
        }

        while (fast != null && slow != null) {
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }

        return true;
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
        System.out.println(algorithm.isPalindrome(listNode1));
    }


}
