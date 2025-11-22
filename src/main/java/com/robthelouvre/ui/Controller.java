package com.robthelouvre.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;

public class Controller {

    @FXML
    private Rectangle myRec;
    private double x;
    private double y;

    public void north(ActionEvent e ){
        System.out.println("north");
        myRec.setY(y = y - 5);
    }
    public void south(ActionEvent e ){
        System.out.println("south");
        myRec.setY(y = y + 5);
    }
    public void east(ActionEvent e ){
        System.out.println("east");
        myRec.setX(x = x + 5);
    }
    public void west(ActionEvent e ){
        System.out.println("west");
        myRec.setX(x = x - 5);
    }
}
