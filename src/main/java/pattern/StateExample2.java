package pattern;

public class StateExample2 {
    static public void main(String[] args) {
        Human h = new Human();
        Activity a = new Work();
        h.setActivity(a);
        for (int i=0; i<10; i++) h.doSomething();

    }
}

interface Activity {
    void doSomething(Human h);
}

class Work implements Activity {
    private int i = 1;

    public void doSomething(Human h) {
        System.out.println("Work");
        if (i < 3) { h.setActivity(this); i++;}
        else h.setActivity(new WeekEnd());

    }
}

class WeekEnd implements Activity {

    public void doSomething(Human h) {
        System.out.println("WeekEnd");
        h.setActivity(new Work());
    }
}


class Human {
    private Activity a;

    public void setActivity(Activity a) {
        this.a = a;

    }

    public void doSomething() {
        a.doSomething(this);
    }
}
