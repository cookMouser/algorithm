package linkedlist;

public class LinkedList {

    public static ListNode removeElement(ListNode head, int val) {
        ListNode current = head;

        while (current != null) {
            if (current.val == val && current == head) {
                head.next = null;
                head = current.next;
                continue;
            } else {
                ListNode next = current.next;
                if (next == null) {
                    break;
                }
                if (next.val == val) {
                    current.next = next.next;
                    next.next = null;
                }
            }

        }
        return head;
    }

    public static ListNode removeElement2(ListNode head, int val) {
        ListNode tempHead = new ListNode(1);
        tempHead.next = head;

        ListNode current = tempHead;
        while (current != null && current.next != null) {
            ListNode next = current.next;
            if (next.val == val) {
                current.next = next.next;
                next.next = null;
            }
            current = current.next;
        }
        return tempHead.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(3);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(6);
        ListNode listNode = removeElement2(head, 6);
        ListNode current = listNode;
        while (current != null) {
            System.out.print(current.val + ",");
            current = current.next;
        }
    }





    public static class ListNode {
        int val;  // 节点上存储的元素
        ListNode next;  // 指向下一个节点的指针
        public ListNode(int val) {
            this.val = val;
        }
    }
}
