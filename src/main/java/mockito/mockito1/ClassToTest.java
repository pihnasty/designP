package mockito.mockito1;

public class ClassToTest {

    private MyDatabase db;

    public ClassToTest(MyDatabase db) {
        this.db = db;
    }

    public boolean query(String query) {
        return "* from t".equals(query.toString());
    }

    public static void main(String[] args) {
        ClassToTest t = new ClassToTest(new MyDatabase());
        System.out.println(t.query("* from t"));
    }
}
