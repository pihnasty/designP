package pattern;

public class StateExample {
    static public void main (String[] args) {
        FM fm = new FM();
        Context context = new Context();
        context.setState(fm);

        for (int i=0; i<10; i++) {
            context.play();
        }

    }
}

interface Station {
    void play();
}

class FM implements Station{

    @Override
    public void play() {
        System.out.println("Play FM");
    }
}

class Retro implements Station{
    @Override
    public void play() {
        System.out.println("Play Retro");
    }
}

class Shanson implements Station{
    @Override
    public void play() {
        System.out.println("Play Shanson");
    }
}

 class Context {
     private Station state;

     public void setState(Station state) {
         this.state = state;
     }

     public void play() {
         state.play();
         if (state instanceof  FM) state = new Retro();
         else if (state instanceof  Retro) state = new Shanson();
         else if (state instanceof  Shanson) state = new FM();
     }
 }