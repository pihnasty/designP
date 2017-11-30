package tests.threadtest.ThreadTest;

import javafx.concurrent.Task;
import javafx.util.Callback;

import java.util.concurrent.ExecutionException;

import static java.lang.Thread.sleep;

/**
 * Created by Pihnastyi.O on 11/17/2017.
 */
public class taskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        PermissionDb2AllCategoriesServiceCallback p = new PermissionDb2AllCategoriesServiceCallback();
        System.out.println("main before");
        p.call(new Exception());
        System.out.println("main after");
        TaskP taskP = new TaskP();

        new Thread(taskP).start();


    }
}

class PermissionDb2AllCategoriesServiceCallback implements Callback<Throwable, Boolean>  {

    @Override
    public Boolean call(Throwable param) {
        System.out.println("PermissionDb2AllCategoriesServiceCallback before");
        TaskP taskP = new TaskP();
        new Thread(taskP).start();

        try {  if (taskP.call()==true) System.out.println("taskP.get()==true");   } catch (Exception e) {             e.printStackTrace();         }


        try {
            sleep(100);   } catch (InterruptedException e) {       e.printStackTrace();       }
        System.out.println("PermissionDb2AllCategoriesServiceCallback after");
        return true;
    }
}


  class TaskP extends Task<Boolean> {


      @Override
      protected Boolean call() throws Exception {
          System.out.println("TaskP before");
          sleep(10000);
          System.out.println("TaskP after");
          return true;
      }
  }