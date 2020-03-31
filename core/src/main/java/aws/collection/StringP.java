package aws.collection;

public class StringP {
    public static void main(String[] args) {
        char chs [] = new char [] {'q','w','e','r','d'};
        "123456789".getChars(2,4,chs,3);
        System.out.println(chs);

        System.out.println(reverse(153423646));



    }
    public static int reverse(int x) {
        StringBuilder s =  new StringBuilder(String.valueOf(x));
        String str = s.reverse().toString();
        while (str.length()>1 && str.charAt(0)=='0') {
            str=str.substring(1);
        }

        if(str.length()>1&&str.charAt(str.length()-1)=='-') {
            str ="-"+str.substring(0,str.length()-1);
        }
        if(str.compareTo(new Integer(Integer.MAX_VALUE).toString())>0 || str.compareTo(new Integer(Integer.MIN_VALUE).toString())<0) {
            throw new IllegalArgumentException();
        }
        return Integer.parseInt(str);
    }

}

