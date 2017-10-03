package tests.FuntionalInterface.funcinterface;

/**
 * Created by Pihnastyi.O on 10/2/2017.
 */
public class CallableNoRerurn {
    public static void main(String[] args) throws Exception {
        FunctionVoidNoArgument functionVoidNoArgument = new FunctionVoidNoArgument();
        FunctionVoid functionVoid = () -> functionVoidNoArgument.prepare();
        functionVoidNoArgument.show(functionVoid);
    }
}


class FunctionVoidNoArgument {
    public void prepare() throws DbLoaderException {
        System.out.println("prepare()");
    }
    public void show (FunctionVoid  functionVoid) throws Exception {
        functionVoid.run();
    }
}




@FunctionalInterface
interface FunctionVoid {
    void run() throws Exception;
}



interface FunctionVoid2 extends Runnable  {
    @Override
    void run() ;
}

class DbLoaderException extends Exception  {

}