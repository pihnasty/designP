package stochastic;

import java.util.concurrent.LinkedBlockingDeque;

public class Equipment {

    private static int count = 0;
    private int number;
    private DistributionLaw distributionLaw;

    private LinkedBlockingDeque<Detail> bunker;

    public Equipment( ) {
        number = ++count;
      //bunker.poll().

    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Equipment setDistributionLaw(DistributionLaw distributionLaw) {
        this.distributionLaw = distributionLaw;
        return this;
    }
}
