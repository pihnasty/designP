/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package Mash.m063_TableView;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class JavaFXApplication extends Application {

    public static class Hotel {

        private StringProperty name;
        private StringProperty resort;
        private StringProperty category;
        private DoubleProperty rate;

        public Hotel(String name, String resort, String category, double rate) {
            this.name = new SimpleStringProperty(name);
            this.resort = new SimpleStringProperty(resort);
            this.category = new SimpleStringProperty(category);
            this.rate=new SimpleDoubleProperty(rate);
        }
    public StringProperty nameProperty() {return name;}
    public String getName(){
    return name.getValue();
    }
    public StringProperty resortProperty() {return resort;}
    public String getResort(){
    return resort.getValue();
    }
    public StringProperty categoryProperty() {return category;}
    public String getCategory(){
    return category.getValue();
    }
    public DoubleProperty rateProperty() {return rate;}
    public Double getRate(){
    return rate.getValue();
    }
    }

    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Тестирование GUI-компонентов");
        Group root = new Group();
        Scene scene = new Scene(root, 600, 300, Color.BEIGE);
        primaryStage.setScene(scene);
        primaryStage.show();

        Label label=new Label("ТОР20 Отелей Турции");
        label.setLayoutX(10);
        label.setLayoutY(10);
        label.setPrefSize(500, 30);
        label.setStyle("-fx-font: bold italic 16pt Georgia;-fx-text-fill:#000066;-fx-background-color:lightgrey;");
        label.setAlignment(Pos.CENTER);

        ObservableList<Hotel> hotels =
        FXCollections.observableArrayList(
                new Hotel("Amara Dolce Vita","Кемер","HV1",4.5),
                new Hotel("Club Boran Mare Beach","Кемер","HV1",4.7),
                new Hotel("Delphin Botanik World of Paradise","Алания","5*",4.4),
                new Hotel("Kamelya World Hotel Fulya","Сиде","5*",4.8),
                new Hotel("Delphin Deluxe Resort","Алания","5*",4.7));

        TableView<Hotel> table = new TableView<Hotel>();
        table.setLayoutX(10);
        table.setLayoutY(50);
     //   table.setTableMenuButtonVisible(true);
        table.setCursor(Cursor.TEXT);
        table.setTooltip(new Tooltip("Популярные отели Турции"));
        table.setStyle("-fx-font: 14pt Arial;");
        table.setPrefWidth(500);
        table.setPrefHeight(200);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn nameCol = new TableColumn("Отель");
        nameCol.setCellValueFactory(
    new PropertyValueFactory<Hotel,String>("name")
);
        nameCol.setPrefWidth(250);
        nameCol.setResizable(false);
        nameCol.setSortable(true);

        TableColumn resortCol = new TableColumn("Курорт");
        resortCol.setCellValueFactory(
    new PropertyValueFactory<Hotel,String>("resort")
);

        TableColumn categoryCol = new TableColumn("Категория");
        categoryCol.setCellValueFactory(
    new PropertyValueFactory<Hotel,String>("category")
);

        TableColumn rateCol = new TableColumn("Рейтинг");
        rateCol.setCellValueFactory(  new PropertyValueFactory<Hotel,String>("rate"));

        table.setItems(hotels);
        table.getColumns().addAll(nameCol, resortCol, categoryCol, rateCol);

        root.getChildren().add(label);
        root.getChildren().add(table);


    }
}
