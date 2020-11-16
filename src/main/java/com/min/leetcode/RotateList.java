package com.min.leetcode;

public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        int length = getLength(head);
        if (length == 0) {
            return head;
        }
        int rotateCnt = k % length;

        if (length == 2 && rotateCnt == 1) {
            ListNode temp = head;

            head = temp.next;
            head.next = temp;
            temp.next = null;

            return head;
        }

        return rotate(head, length - rotateCnt);
    }

    public ListNode getTail(ListNode head) {
        ListNode temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        return temp;
    }

    public int getLength(ListNode head) {
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            length++;
        }
        return length;
    }

    public ListNode rotate(ListNode head, int rotateCnt) {
        ListNode tail = getTail(head);
        tail.next = head;
        for (int i = 1; i < rotateCnt; i++) {
            head = head.next;
        }

        ListNode temp = head;
        head = head.next;
        temp.next = null;

        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
