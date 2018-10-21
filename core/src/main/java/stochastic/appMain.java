package stochastic;

import java.util.concurrent.LinkedBlockingDeque;

public class appMain {
    public static void main(String[] args) {
        LinkedBlockingDeque<Detail> linkedBlockingDeque = new LinkedBlockingDeque<>(50);

        for (int i =0; i<10; i++) {
            linkedBlockingDeque.add(new Detail());
        }

        linkedBlockingDeque.peekFirst();

        System.out.println("linkedBlockingDeque.getFirst().getNumber()="+linkedBlockingDeque.getFirst().getNumber());
        System.out.println("linkedBlockingDeque.getLast().getNumber()="+linkedBlockingDeque.getLast().getNumber());

        linkedBlockingDeque.poll();

        System.out.println("linkedBlockingDeque.getFirst().getNumber()="+linkedBlockingDeque.getFirst().getNumber());
        System.out.println("linkedBlockingDeque.getLast().getNumber()="+linkedBlockingDeque.getLast().getNumber());

        linkedBlockingDeque.add(new Detail());


        System.out.println("linkedBlockingDeque.getFirst().getNumber()="+linkedBlockingDeque.getFirst().getNumber());
        System.out.println("linkedBlockingDeque.getLast().getNumber()="+linkedBlockingDeque.getLast().getNumber());

        linkedBlockingDeque.poll();

        System.out.println("linkedBlockingDeque.getFirst().getNumber()="+linkedBlockingDeque.getFirst().getNumber());
        System.out.println("linkedBlockingDeque.getLast().getNumber()="+linkedBlockingDeque.getLast().getNumber());
        linkedBlockingDeque.poll();

        System.out.println("linkedBlockingDeque.getFirst().getNumber()="+linkedBlockingDeque.getFirst().getNumber());
        System.out.println("linkedBlockingDeque.getLast().getNumber()="+linkedBlockingDeque.getLast().getNumber());
        linkedBlockingDeque.poll();

        System.out.println("linkedBlockingDeque.getFirst().getNumber()="+linkedBlockingDeque.getFirst().getNumber());
        System.out.println("linkedBlockingDeque.getLast().getNumber()="+linkedBlockingDeque.getLast().getNumber());


        DistributionLaw distributionLaw =  new DistributionLaw();
        distributionLaw.setUniformLaw(5.0,15.0);

        System.out.println(distributionLaw.getRandomValue());
        System.out.println(distributionLaw.getRandomValue());
        System.out.println(distributionLaw.getRandomValue());
        System.out.println(distributionLaw.getRandomValue());
        System.out.println(distributionLaw.getRandomValue());
        System.out.println(distributionLaw.getRandomValue());
        System.out.println(distributionLaw.getRandomValue());
        System.out.println(distributionLaw.getRandomValue());


        ProductLine productLine = new ProductLine();

        for (int i =0; i<10; i++) {
            productLine.addEquipment(new Equipment().setDistributionLaw(new DistributionLaw().setUniformLaw(5.0, 15.0)));
        }



        for (int i =0; i<10; i++) {
            System.out.println("i="+ productLine.getEquipmentList().get(i).getNumber());
        }

        System.out.println("getFirst()="+ productLine.getEquipmentList().get(2));


        productLine.getEquipmentList().get(2);




    }
}
