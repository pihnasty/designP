package aws;

import aws.collection.LinkedList;

import java.util.Stack;

public class Brackets2 {

     static int check(String s, int i, char head, int rec) {


         for (int k=i+1; k<s.length(); k++) {
             char current = s.charAt(k);

             if (head =='{') {
                 if(current=='}') {

                 }
                 if (current==']') {
                     return i;
                 }
             }
             if (head == '[') {
                 if (current == ']') {

                 }
                 if (current == '}') {
                     return i;
                 }
             }
                 if(current=='['||current=='{' ){
                         return check(s, k, current, rec++);
                 }
         }
         return rec == 0 ? -1 : s.length();
    }

    public static void main (String [] strings){
        String s = "  [] { ]}";
        System.out.println(Brackets2.check(s, 0, s.charAt(0),0));
    }
}
