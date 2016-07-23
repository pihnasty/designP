/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package Mash.task_05_Concurrency_in_JavaFX_01_02;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class JavaFXApplication extends Application {

    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Тестирование GUI-компонентов");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 300, Color.LIGHTGREEN);
        primaryStage.setScene(scene);
        primaryStage.show();

        final ProgressBar pb = new ProgressBar();
        pb.setLayoutX(20);
        pb.setLayoutY(50);
        pb.setCursor(Cursor.TEXT);
        DropShadow effect = new DropShadow();
        effect.setOffsetX(8);
        effect.setOffsetY(8);
        pb.setEffect(effect);
        pb.setTooltip(new Tooltip("Индикатор выполнения задачи"));
        pb.setPrefSize(200, 30);
        pb.setProgress(0.0);

        Button btnS = new Button("Start");
        btnS.setLayoutX(20);
        btnS.setLayoutY(100);
        btnS.setStyle("-fx-font: 16pt Arial;");
        final int iterations[] = {0};
        btnS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

// Запуск и прерывание потока----------------Меняется код только в этом блоке------------------//
                final Thread [] r = {new Thread() };

                Task<Integer> task = new Task<Integer>() {
                    @Override protected Integer call() throws Exception {

                        for (iterations[0] = 0; iterations[0] < 100; iterations[0]++) {
                            if (isCancelled()) {
                                System.out.println("getMessage()= " + getMessage());
                                updateMessage("Cancelled");

                                break;
                            }
                            updateMessage("Iteration " + iterations[0]);

                            //Block the thread for a short time, but be sure
                            //to check the InterruptedException for cancellation
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException interrupted) {
                                if (isCancelled()) {
                                    updateMessage("Cancelled");
                                    break;
                                }
                            }
                        //     System.out.println("Iteration " + iterations[0]);
                            if (iterations[0] == 51) r_interrupt();

                        }
                        return iterations[0];
                    }
                    public void r_interrupt() {
                        if (iterations[0] == 51) cancel();
                        System.out.println("Iteration " + iterations[0] + "Прерывание Потока");
                    }
                };



                r[0] = new Thread(task);
                r[0].start();
//-End-----------------------------------------------------------------------------------------//



            }
        });
        Button btnR = new Button("Reset");
        btnR.setLayoutX(100);
        btnR.setLayoutY(100);
        btnR.setStyle("-fx-font: 16pt Arial;");
        btnR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pb.setProgress(0.0);
            }
        });

        root.getChildren().add(pb);
        root.getChildren().add(btnS);
        root.getChildren().add(btnR);
    }
}
