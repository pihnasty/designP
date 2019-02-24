import com.sun.javafx.print.PrinterJobImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Destination;

public class PrinterNodetExample2 extends Application {


    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        StackPane root = new StackPane();
        ScrollPane scrollPane =new ScrollPane();
        VBox vBox = new VBox();

        Scene scene = new Scene(root, 400, 300);
        btn.setText("Say 'Hello World'");




        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("To Printer!");
                PrinterJob job = PrinterJob.createPrinterJob();
                if(job != null){

                    try {
                        java.lang.reflect.Field field = job.getClass().getDeclaredField("jobImpl");
                        field.setAccessible(true);
                        PrinterJobImpl jobImpl = (PrinterJobImpl) field.get(job);
                        field.setAccessible(false);

                        field = jobImpl.getClass().getDeclaredField("printReqAttrSet");
                        field.setAccessible(true);
                        PrintRequestAttributeSet printReqAttrSet = (PrintRequestAttributeSet) field.get(jobImpl);
                        field.setAccessible(false);

                        String.format("%020d", 93);
                        //        "D:\\A\\M\\MyProjects\\p8\\one_parameter_model\\pdf"
                        //                                                   D:\A\M\MyProjects\p8\one_parameter_model\pdf\pdf001.pdf

                        printReqAttrSet.add(new Destination(new java.net.URI("file:/D://A//M//MyProjects//p8//one_parameter_model//pdf//opm_page103.pdf")));
                    } catch (Exception e) {
                        System.err.println(e);
                    }




               //     job.getJobSettings();
                //    job.showPageSetupDialog(primaryStage);

                 //   job.showPrintDialog(primaryStage);
              //      job.getJobSettings().setPageRanges(new PageRange(1, 5));
                    job.printPage(vBox);
                    job.endJob();
                }
            }
        });


//-----------------------------------------------------

        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
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


        final LineChart<Number,Number> lineChart2 =
                new LineChart<Number,Number>(xAxis,yAxis);
        XYChart.Series series2 = new XYChart.Series(series.getData());
        lineChart2.getData().add(series2);

        final LineChart<Number,Number> lineChart3 =
                new LineChart<Number,Number>(xAxis,yAxis);
        XYChart.Series series3 = new XYChart.Series(series.getData());
        lineChart3.getData().add(series3);

        final LineChart<Number,Number> lineChart4 =
                new LineChart<Number,Number>(xAxis,yAxis);
        XYChart.Series series4 = new XYChart.Series(series.getData());
        lineChart4.getData().add(series4);


        BorderPane borderPane = new BorderPane();



        vBox.getChildren().add(lineChart);
        vBox.getChildren().add(lineChart2);
        vBox.getChildren().add(lineChart3);
        vBox.getChildren().add(lineChart4);


        // Always show vertical scroll bar
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        // Horizontal scroll bar is only displayed when needed
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        scrollPane.setContent(vBox);
        borderPane.setCenter(scrollPane);

        root.getChildren().addAll(borderPane,btn);




//-----------------------------------------------------





//----------------------------------------------------

        primaryStage.setTitle("Printer");
        primaryStage.setScene(scene);
        primaryStage.show();



    }


    public static void main(String[] args) {
        launch(args);
    }}

