package com.robthelouvre.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        AnchorPane root = fxmlLoader.load();


        stage.setTitle("Hello!");

        Scene scene = new Scene(root, 600, 460);
        scene.getStylesheets().add(HelloApplication.class.getResource("test.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }
}
