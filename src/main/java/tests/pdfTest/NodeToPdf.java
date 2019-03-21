package tests.pdfTest;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;


public class NodeToPdf extends Application {

    @Override
    public void start(Stage primaryStage) {

//        CategoryAxis xAxis = new CategoryAxis();
//        xAxis.setLabel("x");
//        NumberAxis yAxis = new NumberAxis();
//        yAxis.setLabel("y");
//        BarChart bar = new BarChart(xAxis, yAxis);
//        bar.setMaxSize(300, 300);
//        bar.setTitle("Bar node" );
//        bar.setTranslateY(-100);

        Button btn = new Button();
        btn.setTranslateY(100);
        btn.setText("To Pdf'");



        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        final LineChart<Number,Number> lineChart =
            new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));


        lineChart.getData().add(series);






        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {



                WritableImage nodeshot = lineChart.snapshot(new SnapshotParameters(), null);
                File file = new File("chart.png");

                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(nodeshot, null), "png", file);
                } catch (IOException e) {

                }

//-------------------------------------------------------------------------------------


//------------------------------------------------------------------------------------


//  E:\A\_00001\amazonmigrationstudio\sct-main-common\src\main\java\com\amazon\sct\service\report\generator\subtype\pdf\appconv\PdfAppConvReportGenerator.java
                PDDocument doc    = new PDDocument();
                PDPage page = new PDPage();
                PDImageXObject pdimage;
                PDPageContentStream content;
                try {
                    pdimage = PDImageXObject.createFromFile("chart.png",doc);
                    content = new PDPageContentStream(doc, page);
                    content.drawImage(pdimage, 50, 50);
                    content.close();
                    doc.addPage(page);
                    doc.save("pdf_file.pdf");
                    doc.close();
                    file.delete();
                } catch (IOException ex) {
                    Logger.getLogger(NodeToPdf.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });


        StackPane root = new StackPane();


        root.getChildren().add(lineChart);
        root.getChildren().add(btn);




        Scene scene = new Scene(root, 600, 600);


        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}