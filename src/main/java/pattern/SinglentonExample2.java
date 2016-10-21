package pattern;

/**
 * Created by Pihnastyi.O on 10/10/2016.
 */
public class SinglentonExample2 {
    public static void main(String[] arg){

        System.out.println("1=     "+Singlenton.getInstance());
        System.out.println("2=     "+Singlenton.getInstance());

    }

}


class  Singlenton2 {
    private static  Singlenton2 ourInstance = new  Singlenton2();

    public static  Singlenton2 getInstance() {
        if (ourInstance==null) { ourInstance = new Singlenton2(); }
        return ourInstance;
    }

    private  Singlenton2() {
    }
}



