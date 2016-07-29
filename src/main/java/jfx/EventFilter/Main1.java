package jfx.EventFilter;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
//from   http://www.java2s.com/Tutorials/Java/JavaFX/1120__JavaFX_Event_Filter.htm
public class Main1 extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);

        TextField textBox = new TextField();
        textBox.setPromptText("Write here");

        // Register an event filter for a single node and a specific event type
        scene.addEventFilter(MouseEvent.MOUSE_MOVED,
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {
                        System.out.println("mouse clicked");
                    };
                });

        root.getChildren().add(textBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
