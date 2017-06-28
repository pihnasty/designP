package goncalves.chapter02.ex04;


import javax.enterprise.inject.Default;
import java.util.Random;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 7 with Glassfish 4
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
@Default
public class IsbnGenerator04 implements NumberGenerator04 {

  // ======================================
  // =          Business methods          =
  // ======================================

  public String generateNumber() {
    return "13-84356-" + Math.abs(new Random().nextInt());
  }
}