package tests.core.list;

import com.amazon.sct.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListTest {
    public static void main(String[] args) {
        ArrayListTest arrayListTest = new ArrayListTest();

        List<String> list = arrayListTest.getList();

/*
        System.out.println(arrayListTest.separatedByCommasOriginal(list));
        System.out.println(arrayListTest.separatedByCommas(list));


        System.out.println(arrayListTest.separatedByNewLineOriginal(list));
        System.out.println(arrayListTest.separatedByNewLine(list));



        System.out.println(arrayListTest.separatedByCommasWithNewLineOriginal(list));
        System.out.println("_____________________________");
        System.out.println(arrayListTest.separatedByCommasWithNewLine(list));



        System.out.println(arrayListTest.addBracketsWithNewLine(list,1));
        System.out.println("_____________________________");
        System.out.println(arrayListTest.addBracketsWithNewLine(list));


        System.out.println(arrayListTest.addBracketsWithNewLine(list,1));
        System.out.println("_____________________________");
        System.out.println(arrayListTest.addBracketsWithNewLine(list));

                System.out.println(arrayListTest.separatedByCommasWithoutBrackets(list,1));
        System.out.println("_____________________________");
        System.out.println(arrayListTest.separatedByCommasWithoutBrackets(list));

 */

        System.out.println(arrayListTest.separatedByCommasWithoutSpace(list,1));
        System.out.println("_____________________________");
        System.out.println(arrayListTest.separatedByCommasWithoutSpace(list));






    }
}

class ArrayListTest {
    private List<String> list;
    public ArrayListTest () {
        list = new ArrayList(){{ add("First");add("Two");add("Three");add("four");
        }};
    }
    public String separatedByCommasOriginal(List<String> list) {
        StringBuilder res = new StringBuilder();
        for (String s : list) {
            res.append(s);
            res.append(", ");
        }
        if (res.length() > 0) {
            res.delete(res.length() - 2, res.length());
        }
        return res.toString();
    }

    public String separatedByCommas(List<String> list) {
        return list.stream().collect(Collectors.joining(", "));
    }

    public static String separatedByNewLineOriginal(List<String> list) {
        return StringUtils.join(list,
            System.lineSeparator(),
            System.lineSeparator(),
            "");
    }

    public static String separatedByNewLine(List<String> list) {
        return list.stream().collect(Collectors.joining(System.lineSeparator(),System.lineSeparator(),""));
    }

    public static String separatedByCommasWithNewLineOriginal(List<String> list) {
        return StringUtils.join(list,
            "(" + System.lineSeparator(),
            "," + System.lineSeparator(),
            System.lineSeparator() + ")");
    }

    public static String separatedByCommasWithNewLine(List<String> list) {
        return list.stream().collect(Collectors.joining("," + System.lineSeparator(), "(" + System.lineSeparator(), System.lineSeparator() + ")"));
    }

    public static String addBracketsWithNewLine(List<String> list, int i) {
        return StringUtils.join(list,
            "(",
            System.lineSeparator(),
            ")");
    }

    public static String addBracketsWithNewLine(List<String> list) {
        return list.stream().collect(Collectors.joining(System.lineSeparator(), "(", ")"));
    }

    public static String separatedByCommasWithoutBrackets(List<String> list , int i) {
        return StringUtils.join(list,
            System.lineSeparator(),
            "," + System.lineSeparator(),
            System.lineSeparator());
    }

    public static String separatedByCommasWithoutBrackets(List<String> list ) {
        return list.stream().collect(Collectors.joining("," + System.lineSeparator()
            , System.lineSeparator(), System.lineSeparator()));
    }

    public static String separatedByCommasWithoutSpace(List<String> list, int i) {
        StringBuilder res = new StringBuilder();
        for (String s : list) {
            res.append(s);
            res.append(",");
        }
        if (res.length() > 0) {
            res.delete(res.length() - 1, res.length());
        }
        return res.toString();
    }

    public static String separatedByCommasWithoutSpace(List<String> list) {
          return list.stream().collect(Collectors.joining(","));
    }




    public List<String> getList() {
        return list;
    }
}

