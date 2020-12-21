package com.moon.suanfa.链表;

public class _21_ {


    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(4);
        a.next = b;
        b.next = c;

        ListNode a1 = new ListNode(1);
        ListNode b1 = new ListNode(3);
        ListNode c1 = new ListNode(4);
        ListNode d1 = new ListNode(-1);
        ListNode e1 = new ListNode(1);
        ListNode m1 = new ListNode(6);
        a1.next = b1;
        b1.next = c1;
       // c1.next = d1;
       // d1.next = e1;
        //e1.next = m1;

        _21_ qu=  new _21_();

        ListNode listNode = qu.mergeTwoLists(a, a1);


        while (listNode!=null){
            System.out.println(listNode.val);
            listNode=listNode.next;
        }

    }

    private ListNode  mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head=new ListNode(0);
        ListNode index=head;
        if(l1==null){
            return  l2;
        }
        if(l2==null){
            return  l1;
        }
        while (l1!=null&&l2!=null){
            if(l1.val>l2.val){
                index.next=l2;
                l2=l2.next;
            }else{
                index.next=l1;
                l1=l1.next;
            }
            index=index.next;
        }
        if(l1==null){
            index.next=l2;
        }else{
            index.next=l1;
        }
        return head.next;
    }
}
