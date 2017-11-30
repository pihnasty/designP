package tests.javafx.Fxexperience;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by Pihnastyi.O on 11/16/2017.
 */
public class FxexperienceVar01  extends Application {
    private static final double EPSILON = 0.0000005;
    @Override
    public void start(Stage stage) throws Exception {

        final ProgressBar bar = getProgressBar();

        // layout the app
        final StackPane layout = new StackPane(bar);
        layout.setPadding(new Insets(10));
        stage.setScene(new Scene(layout));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static ProgressBar getProgressBar() {
        final ProgressBar bar = new ProgressBar();
        new Thread(new Runnable() {
            @Override public void run() {
                for (int i=1; i<=1000000; i++) {
                    final int counter = i;
                    Platform.runLater(new Runnable() {
                        @Override public void run() {
                            bar.setProgress(counter/1000000.0);
                        }
                    });
                }
            }
        }).start();
        return bar;
    }
}