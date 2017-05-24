package shild.chapter14;

// Demonstrate TwoGen.
class SimpGen2 {
    public static void main(String args[]) {

        TwoGen2<Integer, String> tgObj =  new TwoGen2<Integer, String>(88);

        tgObj.setob2("Oleh");


        // Show the types.
        tgObj.showTypes();

        // Obtain and show values.
        int v = tgObj.getob1();
        System.out.println("value: " + v);


        String str = tgObj.getob2();
        System.out.println("value: " + str);
    }
}
