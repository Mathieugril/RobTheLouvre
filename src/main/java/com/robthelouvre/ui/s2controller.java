package com.robthelouvre.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class s2controller {

    @FXML
    Label nameLabel;

    public void displayName(String username) {
        nameLabel.setText("Welcome " + username);
    }
}
