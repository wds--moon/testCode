package com.moon.suanfa.链表;

class Node {
    public Integer num;
    public Node next;

    public Node(Integer num) {
        this.num = num;
    }

    public Node() {
    }


}

public class linkedRev {

    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        a.next = b;
        b.next = c;
        Node head=a;
        do {
            System.out.println(head.num);
            head = head.next;
        } while (head!= null);

        /**
         * 反序
         */
        Node index=null;
        Node newNode=null;
        do{
            index=a.next;
            a.next=null;
            if(newNode==null){
                newNode=a;
            }else{
                a.next=newNode;
            }
            if(index!=null){
                newNode=a;
                a=index;
            }
        }
        while (index!=null);

        do {
            System.out.println(a.num);
            a = a.next;
        } while (a!= null);

    }

}
