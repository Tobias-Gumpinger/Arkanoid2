package at.ac.fhcampuswien;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class App {
/**
    @Override
    public void start(Stage primaryStage) throws Exception{

        //primaryStage.setTitle("Hello World - Test deux!");
        //Pane root = new Pane();
        //Scene scene = new Scene(root, 300,275);
        //primaryStage.setScene(new Scene(new Pane(), 300, 275));
        //Pane root = new Pane();
        //Rectangle rectangle = new Rectangle(50,50);
        //root.getChildren().add(rectangle);
        //primaryStage.show();
    }
    **/
    public static void main(String[] args) {
        JFrame object = new JFrame();
        Arkanoid game = new Arkanoid();
        object.setBounds(10,10,711,611); //window size
        object.setTitle("Arkanoid"); //title of window
        object.setResizable(false);
        object.setVisible(true);
        object.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        object.add(game);
    }
}
