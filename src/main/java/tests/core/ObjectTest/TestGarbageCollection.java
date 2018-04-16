package tests.core.ObjectTest;

public class TestGarbageCollection {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            TestClass s = new TestClass();
            s.display();
      //      System.gc();
      //      Thread.sleep(1000);
        }
    }
}


class TestClass {
    public static int i = 1;
    public static int j = 1;
    public TestClass() {
        System.out.println("constructor");
        j++;
    }

    public void display() {
        System.out.println("display");
    }
    @Override
    public void finalize() throws InterruptedException {
        System.out.println("destructor----------------------------------------------------------------------"+(i++)+"             "+j++);
        if (i/1000*1000 == i)  Thread.sleep(40000);
        Thread.sleep(100000);
    }
}
