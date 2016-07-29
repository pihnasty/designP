package jfx.EventFilter;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/*  http://www.java2s.com/Tutorials/Java/JavaFX/1120__JavaFX_Event_Filter.htm */
public class Main2 extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);

        TextField textBox = new TextField();
        textBox.setPromptText("Write here");
        // Define an event filter
        EventHandler filter = new EventHandler<InputEvent>() {
            public void handle(InputEvent event) {
                System.out.println("Filtering out event " + event.getEventType());
                event.consume();
            }
        };
        // Register the same filter for two different nodes
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, filter);

        // Register the filter for another event type
        scene.addEventFilter(KeyEvent.KEY_PRESSED, filter);

        root.getChildren().add(textBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
