package pattern;

import com.sun.org.apache.xpath.internal.operations.String;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 13.11.2016.
 */
public class ReadOnlyExample {
    public static void main(java.lang.String args[]) {

        TemperatureData temperatureData = new TemperatureData();

        TemperatureListener temperatureListener = new TemperatureListener();
        temperatureData.addListener(temperatureListener);

        TemperatureEvent temperatureEvent = new TemperatureEvent(temperatureData, 50.0);

        temperatureListener.temperatureChanget(temperatureEvent);
    }
}


interface TemperatureIF {
    double getTemperature();

}

class TemperatureData implements TemperatureIF {
    private double temperature;
    private List listeners = new ArrayList<>();

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        fireTemperature();
    }

    public void addListener(TemperatureIF listener) {
        listeners.add(listener);
    }

    public void removeListener(TemperatureIF listener) {
        listeners.remove(listener);
    }


    private void fireTemperature() {
        for (int i = 0; i < listeners.size(); i++) {
            ((TemperatureListener) listeners.get(i)).getTemperature();
        }

    }

}

class TemperatureEvent {
    private TemperatureData temperatureData;

    private double temperature;

    public TemperatureEvent(TemperatureData temperatureData, double temperature) {
        this.temperature = temperature;
        this.temperatureData = temperatureData;

    }

    public TemperatureData getTemperatureData() {
        return temperatureData;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        temperatureData.setTemperature(temperature);
    }
}

class TemperatureListener implements TemperatureIF {
    private TemperatureEvent event;

    public void temperatureChanget(TemperatureEvent event) {
        this.event = event;
        this.event.getTemperatureData().setTemperature(event.getTemperature());
    }

    public TemperatureEvent getEvent() {
        return event;
    }

    @Override
    public double getTemperature() {
        System.out.printf("Temperature   %5.3f ", event.getTemperature());
        return event.getTemperature();
    }
}