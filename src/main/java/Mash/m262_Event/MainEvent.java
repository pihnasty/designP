package Mash.m262_Event;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventDispatchChain;
import javafx.event.EventDispatcher;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by Pihnastyi.O on 4/13/2016.
 */
public class MainEvent extends Application {
    public static void main(String[] args) {
        Application.launch(MainEvent.class, args);


    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Тестирование GUI-компонентов");
        Group root = new Group();
        Scene scene = new Scene(root, 600, 300, Color.BEIGE);
        Button btn = new Button("Button");

        final EventDispatchChain ec=btn.buildEventDispatchChain(new  com.sun.javafx.event.EventDispatchChainImpl().append(
                new EventDispatcher(){
            public Event dispatchEvent(Event event, EventDispatchChain edc) {
                if(event.getEventType().getName().equals("MOUSE_CLICKED")){
                    System.out.println("Hello World");
                    edc.dispatchEvent(event);
                }
                return event;
            }
        }));

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                ec.dispatchEvent(event);
            }});


        scene.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                ec.dispatchEvent(event);
            }});

        primaryStage.setScene(scene);
        root.getChildren().add(btn);
        primaryStage.show();


    }
}



