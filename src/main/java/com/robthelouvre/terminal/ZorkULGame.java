/* This game is a classic text-based adventure set in a university environment.
   The player starts outside the main entrance and can navigate through different rooms like a 
   lecture theatre, campus pub, computing lab, and admin office using simple text commands (e.g., "go east", "go west").
    The game provides descriptions of each location and lists possible exits.

Key features include:
Room navigation: Moving among interconnected rooms with named exits.
Simple command parser: Recognizes a limited set of commands like "go", "help", and "quit".
Player character: Tracks current location and handles moving between rooms.
Text descriptions: Provides immersive text output describing the player's surroundings and available options.
Help system: Lists valid commands to guide the player.
Overall, it recreates the classic Zork interactive fiction experience with a university-themed setting, 
emphasizing exploration and simple command-driven gameplay
*/

package com.robthelouvre.terminal;

import java.io.*;
import java.util.Scanner;

public class ZorkULGame {
    private Parser parser;
    private User player;
    private static Room balcony, outside, lobby, regaliaGallery, mastersGallery,
            securityRoom, guardRoom, serviceTunnel, janitorCloset,
            deliveryDock, garden, secretPassage, vip, basementTunnel, van;
    private Cameras regCam, deliveryScanner;
    public static Guards patrick, jude;
    Scanner ise = new Scanner(System.in);


    public ZorkULGame() {
        start.create();
        parser = new Parser();
    }



    public void play() {
        printWelcome();

       boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);

            if (player.hasItem("Keycard")) {
                lobby.setExit("south", vip, true);
                mastersGallery.setExit("south", regaliaGallery, true);
                regaliaGallery.setExit("north", mastersGallery , true);
                securityRoom.setExit("east", guardRoom  , true);
            }
            if (player.hasItem("Keycard") && (player.hasItem("Uniform"))) {
                regaliaGallery.setExit("east", securityRoom , true);
                securityRoom.setExit("west", regaliaGallery  , true);
                guardRoom.setExit("west", securityRoom, true);
                deliveryDock.setExit("north", basementTunnel);
            }
            if (player.hasItem("Flashlight")) {
                secretPassage.setExit("north", janitorCloset, true);
                secretPassage.setDetails(Text.Details.PASSAGE_DET2);
            }
            if ((player.hasItem("Crown")) && (player.getCurrentRoom().equals(outside))){
                System.out.println("\n \nYou have escaped with the Crown of Empress Eugénie, Congrats!!");
                finished = true;
            }

        }
        System.out.println("Thank you for playing. Goodbye.");
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("player.ser"))) {
            out.writeObject(player);
            System.out.println("Object has been serialized to player.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to Rob the Louvre!");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("player.ser"))) {
            User deserializedPerson = (User) in.readObject();
            System.out.println("Object has been deserialized:");
            for (Item i : deserializedPerson.getInventory()) {
                System.out.println(i.getName());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private boolean processCommand(Command command) {
        String commandWord = command.getCommandWord();

        if (commandWord == null) {
            System.out.println("I don't understand your command...");
            return false;
        }

        switch (commandWord) {
            case "help":
                printHelp();
                break;
            case "go":
                goRoom(command);
                break;
            case "inspect":
                System.out.println(player.getCurrentRoom().inspect());
                System.out.println(player.getCurrentRoom().searchRoom());
                break;
            case "take":
                Item takeItem = Util.checkItemAvailable(command.getSecondWord(), player.getCurrentRoom().getItems());
                if (takeItem == null) {
                    System.out.println("I can't find that item!");
                } else {
                    System.out.println(player.pickUpItem(takeItem));
                    System.out.println(takeItem.getDescription());
                    System.out.println("\nAll Exits: " + player.getCurrentRoom().getExitString());
                }
                for (Item item : player.getInventory()) {
                    if (item.getName().equals("Crown")){
                       if (regCam.getStatus()){
                            System.out.print("Cameras caught you, Game over!!\n");
                            return true;
                        }
                       if (jude.getCurrentRoom().equals(regaliaGallery)) {
                           System.out.print("Guards caught you, Game over!!\n");
                           return true;
                       }
                       balcony.setDetails(Text.Details.BALCONY_DET2);
                       balcony.setDescription(Text.Descriptions.BALCONY2);
                    }
                }
                break;
            case "inventory": // plan on displaying inventory whole time anyway
                System.out.println("Inventory:");
                for (Item item : player.getInventory()) {
                    System.out.println(item.getName());
                }
                break;
            case "drop":
                Item dropItem = Util.checkItemAvailable(command.getSecondWord(), player.getInventory());
                if (dropItem == null) {
                    System.out.println("I don't have that item!");
                } else {
                    player.dropItem(dropItem);
                }
                break;
            case "pickpocket":
                steal(command);
                System.out.println("\nAll Exits: " + player.getCurrentRoom().getExitString());
                break;
            case "eavesdrop":
                listen();
                System.out.println("\nAll Exits: " + player.getCurrentRoom().getExitString());
                break;
            case "tamper":
                if(player.getCurrentRoom().equals(securityRoom)) {
                    regCam.setStatus(false);
                    System.out.println("Cameras in gallery have been disabled!");
                } else if (player.getCurrentRoom().equals(deliveryDock)) {
                    deliveryScanner.setStatus(false);
                    System.out.println("Scanner has been disabled!");
                } else {
                    System.out.println("Nothing to mess with here.");
                }

                break;
            case "lie":
                if (player.getCurrentRoom().equals(regaliaGallery)) {
                   if (player.hasItem("Uniform")) {
                       Guards.lie(guardRoom);
                   } else {
                       System.out.println("You have no disguise,guards caught you.");
                       return true;
                   }
                } else {
                    System.out.println("This doesn't benefit you here.");
                }
                break;
            case "quit":
                if (command.hasSecondWord()) {
                    System.out.println("Quit what?");
                    return false;
                } else {
                    return true; // signal to quit
                }
            default:
                System.out.println("I don't know what you mean...");
                break;
        }
        return false;
    }

    private void printHelp() {
        System.out.println("You are in the middle of a heist. You are alone. You wander around the university.");
        System.out.print("Your command words are: ");
        parser.showCommands();
    }

    private void steal(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Steal what?");
            return;
        }

        String choice = command.getSecondWord();

        for (Character i : Character.getAllCharacters()) {
            if (choice.equalsIgnoreCase(i.getName())) {

                if (i.getInventory().isEmpty()) {
                    System.out.println(i.getName() + " has nothing to steal.");
                    return;
                }

                System.out.println(i.getName() + " has:");
                for (Item item : i.getInventory()) {
                    System.out.println(" - " + item.getName());
                }

                System.out.print("What would you like to take? ");
                String take = ise.nextLine();

                Item stolenItem = null;
                for (Item item : i.getInventory()) {
                    if (take.equalsIgnoreCase(item.getName())) {
                        stolenItem = item;
                        break;
                    }
                }
                if (stolenItem != null) {
                    i.getInventory().remove(stolenItem);
                    player.getInventory().add(stolenItem);
                    System.out.println("You stole the " + stolenItem.getName() + " from " + i.getName() + "!\n" + stolenItem.getDescription());
                    return;
                } else {
                    System.out.print(i.getName() + " does not have " + take);
                    return;
                }
            }
            }
        System.out.println("There is no one named " + choice + " here to steal from.");

        }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        Room nextRoom = player.getCurrentRoom().getExit(direction);

           if (nextRoom == null) {
               System.out.println("You can't go there yet.");
           } else {
            player.setCurrentRoom(nextRoom);
            System.out.println(player.getCurrentRoom().getLongDescription());
        }
}

    private void listen() {

        if (player.getCurrentRoom().getLines() == null) {
            System.out.print("Nothing to hear here");
        }
        if (player.getCurrentRoom().equals(regaliaGallery)) {
            garden.setExit("east", secretPassage, true);
            for (String lines : Text.Convos.regaliaConvo()) {
                System.out.println(lines);
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (player.getCurrentRoom().equals(deliveryDock)) {
            for (String lines : Text.Convos.deliveryConvo()) {
                System.out.println(lines);
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (player.hasItem("Van-Key")) {
                deliveryDock.setExit("inside", van, true);
            }
        }

    }

    public static void main(String[] args) {
        ZorkULGame game = new ZorkULGame();
        game.play();
    }
}
