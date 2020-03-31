package aws;

import aws.collection.LinkedList;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Brackets3 {

     static int check(String s) {
         Map<Character,Character> chs = new HashMap();
         chs.put('}','{');
         chs.put(']','[');


         Stack<Character> stack = new Stack<>();
         for (int i = 0; i < s.length(); i++) {
             char ch = s.charAt(i);
             if (ch == '{' || ch == '[') {
                stack.push(ch);
             }
             if (ch == '}' || ch == ']') {
                 if(stack.isEmpty()) {
                     return i;
                 }
                 if(stack.peek().equals(chs.get(ch))) {
                     stack.pop();
                 } else {
                    return i;
                 }
             }
         }
         return -1;
    }

    public static void main (String [] strings){
        String s = "[[[[[[}]]]] ";
        System.out.println(Brackets3.check(s));
    }
}
