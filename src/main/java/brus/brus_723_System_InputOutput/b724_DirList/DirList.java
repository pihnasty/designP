package brus.brus_723_System_InputOutput.b724_DirList;

//: io/DirList.java
// Вывод списка каталогов с использованием регулярных выражений.
// {Args: "D.*\.java"}
import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class DirList {
    public static void main(String[] args) {
        File path = new File("src/main/java/brus/brus_723_System_InputOutput/b724_DirList");
        String[] list;
        String strPath = "src/main/java/brus/brus_723_System_InputOutput/b724_DirList";
        System.out.println(strPath);
        if(args.length == 0)
          list = path.list();
        else
            args[0] = "[A-z]";
            list = path.list(new DirFilter(args[0]));

     //   Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
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