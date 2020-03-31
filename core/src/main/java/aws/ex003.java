package aws;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static aws.Solution003.lengthOfLongestSubstring;

public class ex003 {
    public static void main(String[] args) {
        Solution005 solution005 = new Solution005();
        String s = "qqqqeeeeuuuuu";
        solution005.convert(s,4);
        System.out.println( lengthOfLongestSubstring("dvdf") );
    }
}

class Solution003 {
    public static int lengthOfLongestSubstring(String s) {
        char [] chars = s.toCharArray();
        Set<Character> setChars = new HashSet();
        int max= 0;
        for (int i= 0; i<chars.length; i++) {
            if(!setChars.add(chars[i])) {
                max = max<setChars.size() ?setChars.size() : max ;
                setChars = new HashSet();
                setChars.add(chars[i]);
            }
        }
        return max = max<setChars.size() ? setChars.size() : max ;
    }
}

class Solution004 {
    public String convert(String s, int numRows) {

        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }
}

class Solution005 {
    public String convert(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }
}