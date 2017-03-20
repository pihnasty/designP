package g._108_InnerNested;

import java.util.ArrayList;
import java.util.List;

public class InnerNested {
    public static void main(String[] args) {

        EventSource eventSource = new EventSource();
        App app = new App();

    }
}

interface Listener {
    void onEvent(String msg);
}

class MyListener implements Listener {

    @Override
    public void onEvent(String msg) {

    }
}

class EventSource {
    private final List<Listener> listeners = new ArrayList<>();

    void addListener(Listener listener) {
        listeners.add(listener);
    }

    void sendMessage(String msg) {
        int i =0;
        for (Listener listener : listeners) listener.onEvent("Message =" + i++);
    }

}

class App {

    void init(EventSource eventSource) {
        Listener listener = new MyListener(this);
        eventSource.addListener(listener);
    }

    public void reach (String msg) {
        System.out.println("I have massege "+msg);
    }

    class MyListener implements Listener {
        App app;

        MyListener(App app) {
            this.app = app;
        }

        @Override
        public void onEvent(String msg) {
            reach(msg);
        }
    }

}