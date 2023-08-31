package leet_code.dataStructure.day02;


import java.util.ArrayList;
import java.util.List;

// 单链表结构 head， 反转链表结构，并返回反转后的链表结构
public class LinkSolution01 {

    public static void main(String[] args) {
        List list = new ArrayList();
        ListNode head = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5)))));
        System.out.println(head.toString());
        ListNode listNode = reverseList(head);
        System.out.println(listNode.toString());
    }

    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        String str = "ListNode{" + "val=" + val + "};";
        if (next != null) {
            str = str + next.toString();
        }
        return str;
    }
}