package tests.javafx.Fxexperience;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static java.lang.Thread.sleep;

/**
 * Created by Pihnastyi.O on 11/16/2017.
 */
public class FxexperienceVar02 extends Application {
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
        ProgressBar bar = new ProgressBar();
        Task task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                final int max = 10;
                for (int i=1; i<=max; i++) {
                    updateProgress(i, max);
                    sleep(100);
                }
                return null;
            }
        };
        bar.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
        return bar;
    }
}