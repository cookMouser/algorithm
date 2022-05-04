package shuati;

public class lc24 {

    private class ListNode {
        private int val;
        private ListNode next;

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        ListNode() {
        }
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        while (pre.next != null && pre.next.next != null) {
            ListNode temp = pre.next;
            pre.next = pre.next.next;
            temp.next = pre.next.next;
            pre.next.next = temp;
            pre = pre.next.next;
        }
        return dummy.next;
    }
}
