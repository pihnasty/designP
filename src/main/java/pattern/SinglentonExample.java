package pattern;

/**
 * Created by Pihnastyi.O on 10/10/2016.
 */
public class SinglentonExample {
    public static void main(String[] arg){

        System.out.println("1=     "+Singlenton.getInstance());
        System.out.println("2=     "+Singlenton.getInstance());

    }

}



class  Singlenton {
    private static  Singlenton ourInstance = new  Singlenton();

    public static  Singlenton getInstance() {
        return ourInstance;
    }

    private  Singlenton() {
    }
}
