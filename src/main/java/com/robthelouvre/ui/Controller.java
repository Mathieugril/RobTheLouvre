package com.robthelouvre.ui;

import com.robthelouvre.terminal.ZorkULGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

public class Controller {


        @FXML
        private TextArea messageBox;

        @FXML
        private TextField inputField;

        private ZorkULGame game;

        @FXML
        private void initialize() {
            game = new ZorkULGame();
            messageBox.appendText(game.getWelcomeText());
        }

        @FXML
        private void handleCommand() {
            String line = inputField.getText().trim();
            if (line.isEmpty()) return;

            // echo command
            messageBox.appendText("> " + line + "\n");

            // ask the game to process exactly one line
            String response = game.processInput(line);
            messageBox.appendText(response);

            inputField.clear();

            if (game.isFinished()) {
                inputField.setDisable(true);
                inputField.setPromptText("Game over");
            }
        }
    }



