package leetcode.easy.n83.remove.dups.sorted.linkedlist;

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
}

class Solution {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, null);
        addNodes(head, 1);
        addNodes(head, 2);
        addNodes(head, 3);
        addNodes(head, 3);

        displayList(head);
        deleteDuplicates(head);
        displayList(head);
    }

    static void addNodes(ListNode head, int val) {
        ListNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new ListNode(val, null);
    }

    static void deleteDuplicates(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode current = head;
        while (current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
    }

    static void displayList (ListNode head) {
        System.out.println();
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}

/*
 * Learning Outcome:
 * In simple sorted linked list, in order to remove the duplicates, we only look whether the next node and the current
 * node has same values, if we have same values we assign the current node with the very next of next node, hence
 * enabling rechecking whether two nodes have same values.
 */