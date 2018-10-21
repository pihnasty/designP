package lang.longp;


public class LongP {

    public static final int SCHEMA_ID = 1600;
    public static void main(String[] args) {

        LongP longP = new LongP();
        System.out.println(longP.isPrintable("5"));
    }

    public boolean isPrintable(String id) {
        return 1<2 && Long.parseLong(id) < SCHEMA_ID;
    }

}
