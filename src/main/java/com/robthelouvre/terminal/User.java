package com.robthelouvre.terminal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;

public class User extends Character implements Serializable {

    private final ObservableList<String> inventory = FXCollections.observableArrayList();

    public User(String name, Room startingRoom) {
        super(name, startingRoom);
        ArrayList<Item> inventory;
    }

    public ObservableList<String> getInventoryGUI() {
        return inventory;
    }

}
