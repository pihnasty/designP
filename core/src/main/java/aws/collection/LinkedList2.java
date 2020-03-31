package aws.collection;

import java.util.Objects;

public class LinkedList2 {

    static ListNode head1, head2;

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int d) {
            val = d;
            next = null;
        }
    }

    public ListNode addTwoNumbers(ListNode first, ListNode second) {
        ListNode resultNode = null;
        ListNode tempNode = null;
        int countDec = 0;
        while (Objects.nonNull(first) || Objects.nonNull(second)) {
            int sum  = countDec + (Objects.nonNull(first) ? first.val : 0 )
                    + (Objects.nonNull(second) ? second.val : 0);
            countDec = sum /10;
            ListNode addNode = new ListNode(sum % 10);
            if (Objects.isNull(resultNode)) {
                resultNode = addNode;
            } else {
                tempNode.next = addNode;
            }
            tempNode = addNode;
            if (Objects.nonNull(first)) {
                first = first.next;
            }
            if (Objects.nonNull(second)) {
                second = second.next;
            }
        }
        return resultNode;
    }


    public static void main(String[] args) {
        LinkedList2 list = new LinkedList2();

        // creating first list
        list.head1 = new ListNode(7);
        list.head1.next = new ListNode(5);
        list.head1.next.next = new ListNode(9);
        list.head1.next.next.next = new ListNode(4);
        list.head1.next.next.next.next = new ListNode(6);
        System.out.print("First List is ");


        // creating seconnd list
        list.head2 = new ListNode(8);
        list.head2.next = new ListNode(4);
        System.out.print("Second List is ");


        // add the two lists and see the result
        ListNode rs = list.addTwoNumbers(head1, head2);
        System.out.print("Resultant List is ");

    }
}