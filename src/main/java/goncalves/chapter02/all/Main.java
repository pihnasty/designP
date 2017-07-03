package goncalves.chapter02.all;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 7 with Glassfish 4
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
public class Main {

  public static void main(String[] args) throws NamingException {

    Weld weld = new Weld();
    WeldContainer container = weld.initialize();

    BookService bookService = container.instance().select(BookService.class).get();

    Book book = bookService.createBook("H2G2", 12.5f, "Geeky scifi Book");

    System.out.println(book);

    weld.shutdown();
  }
}