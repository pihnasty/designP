package tests.core.list;
import java.util.Comparator;
import java.util.stream.Stream;
public class StreamApp {

    public static void main(String[] args) {

        Stream<Phone> phoneStream = Stream.of(new Phone("iPhone 6 S", "Apple", 600),
                new Phone("Lumia 950", "Microsoft", 500),
                new Phone("LG G 5", "LG",450),
                new Phone("ASUS Zenfone 2", "ASUS",150),
                new Phone("Lumia 640", "Microsoft", 200));

        phoneStream.sorted(new PhoneComparator())
                .forEach(p->System.out.printf("%s (%s) - %d \n",
                        p.getName(), p.getCompany(), p.getPrice()));



    }
}
class PhoneComparator implements Comparator<Phone>{

    public int compare(Phone a, Phone b){

        return a.getName().toUpperCase().compareTo(b.getName().toUpperCase());
    }
}


class Phone{

    private String name;
    private String company;
    private int price;

    public Phone(String name, String comp, int price){
        this.name=name;
        this.company=comp;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public String getCompany() {
        return company;
    }
}
