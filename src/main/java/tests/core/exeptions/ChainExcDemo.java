package tests.core.exeptions;

import java.io.FileNotFoundException;
import javax.xml.transform.TransformerException;

// listing 15
// Demonstrate exception chaining.
class ChainExcDemo {
  static void demoproc() {
    // create an exception
    NullPointerException e =
        new NullPointerException("top layer");

    // add a cause

    ArithmeticException a = new ArithmeticException("cause");

    a.addSuppressed(new TransformerException(""));

    a.initCause(new FileNotFoundException());


    e.initCause(a);

    e.printStackTrace();

    throw e;
  }

  public static void main(String args[]) {
    try {
      demoproc();
    } catch(NullPointerException e) {
      // display top level exception
      System.out.println("Caught: " + e);

      // display cause exception
      System.out.println("Original cause: " +
          e.getCause());
    }
  }
}
