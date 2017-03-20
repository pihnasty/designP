package brus.TIJ4.innerclasses;

/**
 * Created by pom on 26.02.2017.
 */
public class Sequence2 {
    public static void main(String[] args) {
        Sequence sequence = new Sequence(10);
        for(int i = 0; i < 10; i++)
            sequence.add(i);
        Selector selector = sequence.selector();
        while(!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
//       Sequence.SequenceSelector selector2 = new Sequence(10).new SequenceSelector();
//        while(!selector2.end()) {
//            System.out.print(selector2.current() + " ");
//            selector2.next();
//        }


        Parcel3 p = new Parcel3();
        // Must use instance of outer class
        // to create an instance of the inner class:
        Parcel3.Contents c = p.new Contents();
        Parcel3.Destination d = p.new Destination("Tasmania");

        Parcel11.ParcelContents c2 = new Parcel11.ParcelContents();


    }
}
