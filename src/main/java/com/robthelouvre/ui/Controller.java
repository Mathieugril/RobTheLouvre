package com.robthelouvre.ui;

import com.robthelouvre.terminal.Command;
import com.robthelouvre.terminal.Room;
import com.robthelouvre.terminal.ZorkULGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.List;
import java.util.Locale;

public class Controller {


        @FXML
        private TextArea messageBox;

        @FXML
        private TextField inputField;

        @FXML
        private ImageView playerIcon;

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

        messageBox.appendText("> " + line + "\n");

        Command command = game.parseCommand(line);

        String response = game.processInput(line);
        messageBox.appendText(response);

        movePlayerIcon();

        if ("eavesdrop".equalsIgnoreCase(command.getCommandWord())) {
            List<String> convoLines = game.listen();
            playLines(convoLines);
        }

            inputField.clear();

        if (game.isFinished()) {
            inputField.setDisable(true);
            inputField.setPromptText("Game over");
        }
    }
    public void movePlayerIcon() {
        String room = game.getCurrentRoom();
        switch(room) {
            case "lobby":
                playerIcon.setLayoutX(200);
                playerIcon.setLayoutY(200);
                break;
            case "mastersGallery":
                playerIcon.setLayoutX(400);
                playerIcon.setLayoutY(300);
                break;
        }
    }
    private void playLines(List<String> lines) {
        if (lines == null || lines.isEmpty()) {
            return;
        }

        Duration perLine = Duration.seconds(2.5); // same as your Thread.sleep(2500)
        Timeline timeline = new Timeline();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            KeyFrame frame = new KeyFrame(
                    perLine.multiply(i + 1),
                    e -> messageBox.appendText(line + "\n")
            );
            timeline.getKeyFrames().add(frame);
        }

        timeline.play();
    }
    }



