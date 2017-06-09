package pattern.creational;

/** "Product" */
class Pizza {
    private String dough = "";
    private String sauce = "";
    private String topping = "";

    public void setDough(String dough)     { this.dough = dough; }
    public void setSauce(String sauce)     { this.sauce = sauce; }
    public void setTopping(String topping) { this.topping = topping; }
}


/** "Abstract Builder" */
abstract class PizzaBuilder {
    protected Pizza pizza;

    public Pizza getPizza() { return pizza; }
    public void createNewPizzaProduct() { pizza = new Pizza(); }

    public abstract void buildDough();
    public abstract void buildSauce();
    public abstract void buildTopping();
}

/** "ConcreteBuilder" */
class HawaiianPizzaBuilder extends PizzaBuilder {
    public void buildDough()   { pizza.setDough("cross"); }
    public void buildSauce()   { pizza.setSauce("mild"); }
    public void buildTopping() { pizza.setTopping("ham+pineapple"); }
}

/** "ConcreteBuilder" */
class SpicyPizzaBuilder extends PizzaBuilder {
    public void buildDough()   { pizza.setDough("pan baked"); }
    public void buildSauce()   { pizza.setSauce("hot"); }
    public void buildTopping() { pizza.setTopping("pepperoni+salami"); }
}


/** "Director" */
class Waiter {
    private PizzaBuilder pizzaBuilder;

    public void setPizzaBuilder(PizzaBuilder pb) { pizzaBuilder = pb; }
    public Pizza getPizza() { return pizzaBuilder.getPizza(); }

    public void constructPizza() {
        pizzaBuilder.createNewPizzaProduct();
        pizzaBuilder.buildDough();
        pizzaBuilder.buildSauce();
        pizzaBuilder.buildTopping();
    }
}


/** A customer ordering a pizza. */
class BuilderExample {
    public static void main(String[] args) {
        Waiter waiter = new Waiter();
        PizzaBuilder hawaiianPizzaBuilder = new HawaiianPizzaBuilder();
        waiter.setPizzaBuilder(hawaiianPizzaBuilder);
        waiter.constructPizza();

        Pizza pizza = waiter.getPizza();}

}


/*
1.--------------------------------------------------------------------------
Interface Builder<T>    https://docs.oracle.com/middleware/1221/jdev/api-reference-esdk/oracle/javatools/patterns/Builder.html
T build()  throws java.lang.Exception

All Known Implementing Classes:
AbstractPaletteObjectBuilder, DefaultPaletteGroup.Builder, DefaultPaletteItem.Builder, DefaultPalettePage.Builder, DefaultPaletteSection.Builder

2.--------------------------------------------------------------------------
Class Locale.Builder    https://docs.oracle.com/javase/7/docs/api/java/util/Locale.Builder.html
Locale aLocale = new Builder().setLanguage("sr").setScript("Latn").setRegion("RS").build();

3.--------------------------------------------------------------------------
SAXParser - Director
ContentHandler - BuilderSAXParser - Director
ContentHandler - Builder

// Create Director
SAXParser parser = new org.apache.xerces.parsers.SAXParser();
// Create Concrete Builder (our own class)
IdentingContentHandler handler = new IndentingContentHandler();
// Set Buidler to Director
parser.setContentHandler(handler);
// Build
parser.parse(new InputSource(new FileReader(fileName));
// Get indented XML as String from handler
String identedXML = handler.getResult();



*/