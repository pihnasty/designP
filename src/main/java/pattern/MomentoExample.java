package pattern;

class Memento {
    private final String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

class Caretaker {
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}

class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento saveState() {
        return new Memento(state);
    }

    public void restoreState(Memento memento) {
        this.state = memento.getState();
    }

    @Override
    public String toString() {
        return "State = "+state;
    }
}

public class MomentoExample {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState("on");
        System.out.println(originator);
        caretaker.setMemento(originator.saveState());

        originator.setState("off");
        System.out.println(originator);

        originator.restoreState(caretaker.getMemento());
        System.out.println(originator);
    }
}

/*
 * Output:
 * State is on
 * State is off
 * State is on
 */
