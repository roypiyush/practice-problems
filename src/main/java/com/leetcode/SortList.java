package com.leetcode;

public class SortList {
    public static void main(String[] args) {
        ListNode head = new ListNode(-1,
                new ListNode(5,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(0)))));

        ListNode sorted = new SortList().sortList(head);
        System.out.println(sorted);

    }

    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    ListNode mergeSort(ListNode node) {
        if (node == null) {
            return null;
        }
        if (node.next == null) {
            return node;
        }

        ListNode mid = mid(node);
        // split
        ListNode midNext = mid.next;
        mid.next = null;

        ListNode n1 = mergeSort(node);
        ListNode n2 = mergeSort(midNext);
        return merge(n1, n2);

    }

    ListNode merge(ListNode n1, ListNode n2) {
        ListNode head = null;
        ListNode cur = null;

        while (n1 != null && n2 != null) {

            if (n1.val <= n2.val) {
                ListNode n1Next = n1.next;
                n1.next = null;
                if (head == null) {
                    head = n1;
                    cur = n1;
                } else {
                    cur.next = n1;
                    cur = cur.next;
                }
                n1 = n1Next;

            } else {
                ListNode n2Next = n2.next;
                n2.next = null;

                if (head == null) {
                    head = n2;
                    cur = n2;
                } else {
                    cur.next = n2;
                    cur = cur.next;
                }
                n2 = n2Next;
            }

        }

        if (n1 != null) {
            cur.next = n1;
        }

        if (n2 != null) {
            cur.next = n2;
        }

        return head;
    }


    ListNode mid(ListNode head) {
        ListNode slow = head;
        ListNode fast = slow.next == null ? null : slow.next.next;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next == null ? null : fast.next.next;
        }
        return slow;
    }
}
