package com.robthelouvre.terminal;

import java.util.ArrayList;
import java.util.Scanner;



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
    public void setCurrentRoom(Room room) {
        super.setCurrentRoom(room);
    }

    public static void lie(Room target) {
       Scanner scan = new Scanner(System.in);
       for (String lie : Text.Convos.lieToGuards()) {
           System.out.println(lie);
           try {
               Thread.sleep(500);
           } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
           }
       }
       System.out.print("Who is the supervisor on today?");
       System.out.print("\n - Axel\n - Dorian\n - Jean\n - Tobias\n ");
       String name = scan.nextLine();

       if(name.equalsIgnoreCase("tobias")) {
           for (String right : Text.Convos.responseRight()) {
               System.out.println(right);
               try {
                   Thread.sleep(500);
               } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
               }
           }

           ZorkULGame.jude.setCurrentRoom(target);
           ZorkULGame.patrick.setCurrentRoom(target);
       } else {
           System.out.print(" Player: " + name + " told me.\n");
           for (String wrong : Text.Convos.responseWrong()) {
               System.out.println(wrong);
               try {
                   Thread.sleep(500);
               } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
               }
           }
       }


   }
}
