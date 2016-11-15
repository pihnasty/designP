package pattern;

/**
 * Created by Max on 15.11.2016.
 */
public class IteratorExample {

    MyCollection m = new MyCollection();

    Iterator iter = m.getIterator();

    static public void main (String[] args) {
        MyCollection m = new MyCollection();

        Iterator iter = m.getIterator();
        while (iter.hasNext() ) {
            System.out.println(iter.next());
        }
    }






}


interface Iterator {
    Object next();
    boolean hasNext();
}


interface Collection {
    Iterator getIterator();
}

class MyCollection implements  Collection{
        String [] list =  {"First","Second","Tried"};
        int count=0;

    @Override
    public Iterator getIterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator{

        @Override
        public Object next() {
            return list[count++];
        }

        @Override
        public boolean hasNext() {
            return count<list.length ? true : false;
        }
    }
}
