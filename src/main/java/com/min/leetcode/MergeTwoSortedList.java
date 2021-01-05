package com.min.leetcode;

import java.util.PriorityQueue;

public class MergeTwoSortedList {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        while (l1 != null) {
            queue.add(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            queue.add(l2.val);
            l2 = l2.next;
        }

        ListNode temp = new ListNode();
        ListNode answer = temp;
        ListNode before = new ListNode();

        if (queue.isEmpty()) {
            return null;
        }

        while (!queue.isEmpty()) {
            temp.val = queue.poll();
            temp.next = new ListNode();
            before = temp;
            temp = temp.next;

        }

        before.next = null;

        return answer;
    }

    public class ListNode {
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
