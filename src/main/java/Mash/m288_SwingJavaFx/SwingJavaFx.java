package Mash.m288_SwingJavaFx;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Shadow;
import javafx.scene.web.HTMLEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractCollection;
import java.util.LinkedHashMap;
import java.util.Map;

public class SwingJavaFx extends JFrame {
    HTMLEditor edtr;
    public SwingJavaFx() {
        this.setSize(600, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        JButton b1 = new JButton("Read");
        b1.setSize(150, 22);
        b1.setLocation(10, 10);
        b1.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, edtr.getHtmlText());
            }
        });
        this.add(b1);
        final JFXPanel jfx = new JFXPanel();
        jfx.setSize(560, 300);
        jfx.setLocation(10, 40);

        Button b10 = new Button("Button2");

        this.add(jfx);
        Platform.runLater(new Runnable() {
            @Override public void run() {
                Group root = new Group();
                Scene scene = new Scene(root, 400, 300);
                jfx.setScene(scene);
                javafx.scene.shape.Rectangle rctngl = new javafx.scene.shape.Rectangle();
                rctngl.setTranslateX(20);
                rctngl.setTranslateY(30);
                rctngl.setWidth(500);
                rctngl.setHeight(250);
                rctngl.setEffect(new Shadow());
                root.getChildren().add(rctngl);
                edtr = new HTMLEditor();
                edtr.setHtmlText("Blablabla");
                edtr.setTranslateX(20);
                edtr.setTranslateY(30);
                edtr.setPrefWidth(500);
                edtr.setPrefHeight(250);
                root.getChildren().add(edtr);
            }
        });

        Platform.runLater(new Runnable() {
            @Override public void run() {
                Map<String, String> hashmap = new LinkedHashMap<String, String>();

                Group root = new Group();
                Scene scene = new Scene(root, 400, 300);
                jfx.setScene(scene);
                Button b = new Button("Button");
                root.getChildren().add(b);
            }
        });

    }
    public static void main(String[] args) {
        AbstractCollection a;
        new SwingJavaFx();
    }
}
