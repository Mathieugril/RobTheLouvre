package com.robthelouvre.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class s2controller {

    @FXML
    Label nameLabel;

    public void displayName(String username) {
        nameLabel.setText("Welcome " + username);
    }

    @FXML
    private Button lougoutButton;
    @FXML
    private AnchorPane anchor;

    Stage stage;

    public void logout(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout");
        alert.setContentText("Do you want to save before existing: ");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) anchor.getScene().getWindow();
            System.out.print("logged out");
            stage.close();
        }
    }
}
