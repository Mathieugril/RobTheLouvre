package com.robthelouvre.ui;
import com.robthelouvre.terminal.ZorkULGame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        ZorkULGame game = new ZorkULGame();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("map.fxml"));
        Parent root = fxmlLoader.load();

        Controller controller = fxmlLoader.getController();

        controller.setGame(game);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(HelloApplication.class.getResource("mapview.css").toExternalForm());
        stage.setScene(scene);
        stage.setMaximized(true);
       // stage.setResizable(false);
       //sta
        stage.show();
    }
}
