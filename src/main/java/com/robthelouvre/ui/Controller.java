package com.robthelouvre.ui;

import com.robthelouvre.terminal.Command;

import com.robthelouvre.terminal.RoomType;
import com.robthelouvre.terminal.ZorkULGame;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.List;

public class Controller {
  //  public String line;


        @FXML
        private TextArea messageBox;

        @FXML
        private TextField inputField;

        @FXML
        private ImageView playerIcon;

        @FXML
        private ImageView playerIcon1;

        private ZorkULGame game;

        @FXML
        private AnchorPane mapPaneSecret;
        @FXML
        private AnchorPane mapPaneNormal;
        private Image thiefImage;
        private Image uniformImage;

    public void setGame(ZorkULGame game) {
        this.game = game;
        messageBox.appendText(game.getWelcomeText());
        updatePlayerIcon();
        updateMap();
    }


@FXML
    private void initialize() {

        thiefImage = new Image(getClass().getResource("robber1.png").toExternalForm());
        uniformImage = new Image(getClass().getResource("cop1.png").toExternalForm());

         playerIcon.setImage(thiefImage);
         playerIcon1.setImage(thiefImage);
}


    private void updateMap() {
        boolean knowsPassage = game != null && game.isSecretPassageKnown();

        mapPaneSecret.setVisible(knowsPassage);
        mapPaneSecret.setManaged(knowsPassage);

        mapPaneNormal.setVisible(!knowsPassage);
        mapPaneNormal.setManaged(!knowsPassage);
    }


    private void updatePlayerIcon() {

        if (game.player.hasItem("Uniform")) {
            playerIcon.setImage(uniformImage);
            playerIcon1.setImage(uniformImage);
        } else {
            playerIcon.setImage(thiefImage);
            playerIcon1.setImage(thiefImage);
        }
    }



        @FXML
         private void handleCommand() {

            String line = inputField.getText().trim().toLowerCase();
            if (line.isEmpty()) return;

            messageBox.appendText("> " + line + "\n");

            Command command = game.parseCommand(line);

            String response = game.processInput(line);
            messageBox.appendText(response);

            updatePlayerIcon();
            movePlayerIcon();
            updateMap();

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
            RoomType type = game.getCurrentRoomType();
             playerIcon.setLayoutX(type.getIconX());
             playerIcon.setLayoutY(type.getIconY());

             playerIcon1.setLayoutX(type.getIconX());
             playerIcon1.setLayoutY(type.getIconY());

             System.out.println("movePlayerIcon() called");
         }


         private void playLines(List<String> lines) {
            if (lines == null || lines.isEmpty()) {
              return;
        }

        Duration perLine = Duration.seconds(2.0);
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



