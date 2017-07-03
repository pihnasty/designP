import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 * Created by pom on 28.06.2017.
 */
public class WeldStart {
    public static void main(String[] args) {
        // Initialize Weld
        Weld theWeld = new Weld();
        WeldContainer theContainer = theWeld.initialize();


        // Execute the run method
        //theContainer.instance().select(WeldStart.class).get().run();
        theContainer.instance().select(WeldStart.class).get();

        // Shutting down Weld again
        theWeld.shutdown();
    }

    private void run() {
        System.out.println("Weld starting!!!!");
    }
}
