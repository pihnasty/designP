package stochastic;

import java.util.Random;
import java.util.function.Supplier;

public class DistributionLaw  {
    private double averageProcessingTime;
    private double minProcessingTime;
    private double maxProcessingTime;
    private double standardDeviation;

    private Supplier randomValue;

    public DistributionLaw() {

    }

    public DistributionLaw setUniformLaw(double minProcessingTime, double maxProcessingTime) {
        this.minProcessingTime = minProcessingTime;
        this.maxProcessingTime = maxProcessingTime;
        Random random = new Random();
        randomValue = () -> random.nextDouble()*(maxProcessingTime-minProcessingTime)  + minProcessingTime;
        return this;
    }

    public double getRandomValue() {
        return (double)randomValue.get();
    }



}
