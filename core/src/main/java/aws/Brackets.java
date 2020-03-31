package aws;

import aws.collection.LinkedList;

import java.util.Stack;

public class Brackets {

     static int check(String s) {
        LinkedList list = new LinkedList();
        Stack<Character> stack = new Stack();
        for (int i=0; i<s.length(); i++)  {
            char ch = s.charAt(i);
            if('{'==ch) {
                stack.push(ch);
            }
            if('}'==ch) {
                if(stack.isEmpty()) {
                    return i;
                }
                stack.pop();
            }
            System.out.println(ch+ "    "+ stack.size());

        }
     return stack.size()==0 ? -1 : s.length();
    }

    public static void main (String [] strings){
        String s = " {    {     {     }   }   {        }";
        System.out.println(Brackets.check(s));
    }
}
