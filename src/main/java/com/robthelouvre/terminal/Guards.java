package com.robthelouvre.terminal;

import java.util.ArrayList;

public class Guards extends Character {

    public Guards(String name, Room startingRoom) {
        super(name, startingRoom);
        ArrayList<Item> inventory;
    }
    public Guards(String name, Room startingRoom,  ArrayList<Item> inventory) {
        super(name, startingRoom);
     //   ArrayList<Item> inventory;
    }

    @Override
    public void behavior() {

    }
}
