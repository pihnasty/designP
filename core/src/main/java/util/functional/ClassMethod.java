package util.functional;

interface F {
    void rename (Person people, String name);
}

class Person {
    private String name;
    public Person (String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }



    public void run (Person person, String name, F f) {
        f.rename(person, name);
    }
}

public class ClassMethod {
    public static void main(String[] args) {
        Person person = new Person("Oleh");
        person.run( person,"Marina", Person::setName);
        System.out.println(person.getName());
    }
}
