package tests;

import java.util.ArrayList;


/**
 * Created by Max on 21.12.2016.
 */
public class LinkedExample {
    static public void main (String [] args ) {

        // refExample();
        refExample2(10);
    }
    static public void refExample(){
        Node ref = new Node (0,null);
        for (int k=1; k<10; k++) {
            ref = new Node (k,ref);
        }

        System.out.println("refBegin="+ref);
        //     System.out.println("refBeginValue="+ref.value);

        printRef(ref);

        System.out.println("refEnd="+ref);
        System.out.println("refEndValue="+ref.value);
    }


    static public void refExample2(int max){
        Node head = new Node (max,null);
        Node ref = head;
        for (int k=max; k>0; k--) {
            ref.next = new Node (k-1,null);
            ref = ref.next;
            System.out.println(ref.value);
        }

        System.out.println("ref.value="+head.value);



        //     System.out.println("refBeginValue="+ref.value);

        printRef(head);


        System.out.println("refEndValue="+ref.value);
    }



    private static void printRef(Node ref) {
        while (ref!=null) {
            System.out.println(ref.value);

            ref=ref.next;

            //``if(ref!=null)   ref.value=0;
        }
    }

}


class Node {
    public int value;
    public Node next;
    public Node(int value, Node next){
        this.value=value;
        this.next = next;
    }
}
