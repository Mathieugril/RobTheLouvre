package com.robthelouvre.terminal;

import java.util.ArrayList;

public class Public extends Character{

    public Public(String name, Room startingRoom) {
        super(name, startingRoom);
        ArrayList<Item> inventory;
    }

    @Override
    public void behavior() {
       System.out.print("The general public are admiring the art pieces");
    }
}
