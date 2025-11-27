package com.robthelouvre.terminal;

import java.util.ArrayList;
import java.util.Scanner;



public class Guards extends Character {

    public Guards(String name, Room startingRoom) {
        super(name, startingRoom);
        ArrayList<Item> inventory;
    }

    @Override
    public void setCurrentRoom(Room room) {
        super.setCurrentRoom(room);
    }

    public static String lie(Command command, Room target) {
        StringBuilder dialog = new StringBuilder();


        if (!command.hasSecondWord()) {
        for (String lie : Text.Convos.lieToGuards()) {
            dialog.append(lie).append("\n");
        }


            dialog.append("Who is the supervisor on today?");
            dialog.append("\n - Axel\n - Dorian\n - Jean\n - Tobias\n \n To lie, type: lie *name*");
        } else {
            String name = command.getSecondWord();

        if (name.equalsIgnoreCase("tobias")) {
            for (String right : Text.Convos.responseRight()) {
                dialog.append(right).append("\n");

            }

            ZorkULGame.jude.setCurrentRoom(target);
            ZorkULGame.patrick.setCurrentRoom(target);
        } else {
            dialog.append(" Player: ").append(name).append(" told me.\n");
            for (String wrong : Text.Convos.responseWrong()) {
                dialog.append(wrong).append("\n");
            }
        }

        }
        return dialog.toString();
        }

}




