package com.moon.suanfa.链表;

import java.util.Stack;

public class _2_ {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {


        return null ;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(2);
        ListNode b = new ListNode(4);
        ListNode c = new ListNode(3);
        a.next = b;
        b.next = c;

        ListNode a1 = new ListNode(5);
        ListNode b1 = new ListNode(6);
        ListNode c1 = new ListNode(4);
        a1.next = b1;
        b1.next = c1;
        _2_ mm=new _2_();
        ListNode listNode = mm.addTwoNumbers(a, a1);
        while (listNode!=null){
            System.out.println(listNode.val);
            listNode=listNode.next;
        }
    }



}
