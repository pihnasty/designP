package brus.brus_723_System_InputOutput.b724_DirList;

//: io/DirList.java
// Вывод списка каталогов с использованием регулярных выражений.
// {Args: "D.*\.java"}
import java.util.regex.*;
import java.io.*;
import java.util.*;

public class DirList {
    public static void main(String[] args) {
        File path = new File("src/main/java/brus/brus_723_System_InputOutput/b724_DirList");
        String[] list;
        String strPath = "src/main/java/brus/brus_723_System_InputOutput/b724_DirList";
        System.out.println(strPath);
//        if(args.length == 0)
  //        list = path.list();
//        else
             list = path.list(new DirFilter("^s"));
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for(String dirItem : list)
            System.out.println(dirItem);
    }
}

class DirFilter implements FilenameFilter {
    private Pattern pattern;
    public DirFilter(String regex) {
        pattern = Pattern.compile(regex);
    }
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}