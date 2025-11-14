package com.robthelouvre.terminal;

import java.io.Serializable;
import java.util.ArrayList;

public class User extends Character implements Serializable {

    public User(String name, Room startingRoom) {
        super(name, startingRoom);
        ArrayList<Item> inventory;
    }

}
