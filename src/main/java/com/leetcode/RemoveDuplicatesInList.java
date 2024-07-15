package com.leetcode;

class ListNode {
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            builder.append(cur.val + " -> ");
            cur = cur.next;
        }
        builder.append("⏚");
        return builder.toString();
    }
}

public class RemoveDuplicatesInList {

    ListNode reverse(ListNode n) {
        ListNode l = null;
        ListNode cur = n;
        while (cur != null) {
            ListNode t = cur.next;
            cur.next = l;
            l = cur;
            cur = t;
        }
        return l;
    }

    public ListNode deleteDuplicates(ListNode head) {

        if (head == null) {
            return null;
        }

        boolean flag = false;

        ListNode cur = head;
        ListNode x = cur.next;

        cur.next = null;
        ListNode list = cur;

        cur = x;

        while (cur != null) {
            if (!flag && cur.val == list.val) {
                ListNode t = cur.next;
                cur.next = null;
                cur = t;
                flag = true;
            } else if (flag && cur.val == list.val) {
                ListNode t = cur.next;
                cur.next = null;
                cur = t;
            } else if (flag && cur.val != list.val) {
                list = list == null ? null : list.next;
                // ListNode t = cur.next;
                // cur.next = list;
                // list = cur;
                // cur = t;
                flag = false;
            } else {
                ListNode t = cur.next;
                cur.next = list;
                list = cur;
                cur = t; // moved to next pointer
            }
        }

        if (flag) {
            list = list.next;
        }

        return reverse(list);

    }

    ListNode create(int val) {
        ListNode n = new ListNode();
        n.val = val;
        return n;

        
    }

    public static void main(String[] args) {

        RemoveDuplicatesInList main = new RemoveDuplicatesInList();

        ListNode h = main.create(1);
        h.next = main.create(2);
        h.next.next = main.create(3);
        h.next.next.next = main.create(3);
        h.next.next.next.next = main.create(4);
        h.next.next.next.next.next = main.create(4);
        h.next.next.next.next.next.next = main.create(5);
        

        ListNode x = main.deleteDuplicates(h);
        while (x != null) {
            System.out.printf("%s ", x.val);
            x = x.next;
        }

        

    }
}
