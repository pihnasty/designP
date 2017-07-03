package goncalves.chapter02.ex04;


import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 7 with Glassfish 4
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
public class BookService04 {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Inject
  @Default
  private NumberGenerator04 numberGenerator;

  // ======================================
  // =          Business methods          =
  // ======================================

  public Book04 createBook(String title, Float price, String description) {
    Book04 book = new Book04(title, price, description);
    book.setIsbn(numberGenerator.generateNumber());
    return book;
  }

  public static void main(String[] args){

        BookService04 bookService04 = new BookService04();
        bookService04.createBook("myBook",75.0f,"Very well");
  }

}
