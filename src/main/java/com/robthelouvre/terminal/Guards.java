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

    public static String lie(Room target) {
        StringBuilder dialog = new StringBuilder();
        Scanner scan = new Scanner(System.in);
        for (String lie : Text.Convos.lieToGuards()) {
            dialog.append(lie).append("\n");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }


        dialog.append("Who is the supervisor on today?");
        dialog.append("\n - Axel\n - Dorian\n - Jean\n - Tobias\n ");
        String name = scan.nextLine();

        if (name.equalsIgnoreCase("tobias")) {
            for (String right : Text.Convos.responseRight()) {
                dialog.append(right).append("\n");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            ZorkULGame.jude.setCurrentRoom(target);
            ZorkULGame.patrick.setCurrentRoom(target);
        } else {
            dialog.append(" Player: ").append(name).append(" told me.\n");
            for (String wrong : Text.Convos.responseWrong()) {
                dialog.append(wrong).append("\n");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }return "";
    }
}




