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

import java.util.ArrayList;
import java.util.Scanner;

public class ZorkULGame {
    private Parser parser;
    private Character player;
    private Room balcony, outside, lobby, regaliaGallery, mastersGallery,
            securityRoom, guardRoom, serviceTunnel, janitorCloset,
            deliveryDock, garden, secretPassage, vip, basementTunnel;
    Scanner ise = new Scanner(System.in);

    public ZorkULGame() {
        createRooms();
        parser = new Parser();
    }

    public void createRooms() {
       // Room balcony, outside, lobby, regaliaGallery, mastersGallery, securityRoom, guardRoom, serviceTunnel, janitorCloset, deliveryDock, garden, secretPassage,
      //          vip, basementTunnel;

        Item KeyCard = new Item("Keycard", Text.ItemDESC.KEYCARD);
        Item Uniform = new Item("Guards Uniform", Text.ItemDESC.UNIFORM);
        Item Gum = new Item("Hollywood Chewing Gum", Text.ItemDESC.GUM);
        Item Smokes = new Item("Marlboro Reds", Text.ItemDESC.SMOKES);

        ArrayList<Item> lobbyItems = new ArrayList<Item>();
        ArrayList<Item> regaliaGalleryItems = new ArrayList<Item>();
        ArrayList<Item> masterGalleryItems = new ArrayList<Item>();
        ArrayList<Item> securityItems = new ArrayList<Item>();
        ArrayList<Item> guardRoomItems = new ArrayList<Item>();
        ArrayList<Item> tunnelRoomItems = new ArrayList<Item>();

        ArrayList<Item> janitorRoomItems = new ArrayList<Item>();
        ArrayList<Item> dockItems = new ArrayList<Item>();
        ArrayList<Item> passageItems = new ArrayList<Item>();
        ArrayList<Item> vipItems = new ArrayList<Item>();
        ArrayList<Item> basementItems = new ArrayList<Item>();




        outside = new Room("are out of the Louvre");
        balcony = new Room(Text.Descriptions.BALCONY);
        lobby = new Room(Text.Descriptions.LOBBY, lobbyItems);
        regaliaGallery = new Room(Text.Descriptions.REGALIA, regaliaGalleryItems, Text.Convos.regaliaConvo());
        mastersGallery = new Room(Text.Descriptions.MASTERS, masterGalleryItems);
        securityRoom = new Room(Text.Descriptions.SECURITY, securityItems);
        guardRoom = new Room(Text.Descriptions.GUARDS, guardRoomItems);
        serviceTunnel = new Room(Text.Descriptions.SERVICE, tunnelRoomItems);
        janitorCloset = new Room(Text.Descriptions.JANITOR, janitorRoomItems);
        deliveryDock = new Room(Text.Descriptions.DELIVERY, dockItems);
        garden = new Room(Text.Descriptions.GARDEN);
        secretPassage = new Room(Text.Descriptions.PASSAGE, passageItems);
        vip = new Room(Text.Descriptions.VIP, vipItems);
        basementTunnel = new Room(Text.Descriptions.BASEMENT, basementItems);


        balcony.setExit("north", regaliaGallery, true);
        balcony.setExit("down", garden, true);
        balcony.setDetails(Text.Details.BALCONY_DET);

        lobby.setExit("west", outside, true);
        lobby.setExit("east", mastersGallery, true);
        lobby.setExit("south", vip);
        lobby.setDetails(Text.Details.LOBBY_DET);
       // lobbyItems.add(KeyCard);

        regaliaGallery.setExit("north",mastersGallery);
        regaliaGallery.setExit("south", balcony, true);
        regaliaGallery.setExit("east", securityRoom);
        regaliaGallery.setDetails(Text.Details.REGALIA_DET);


        mastersGallery.setExit("south",regaliaGallery);
        mastersGallery.setExit("west", lobby, true);
        mastersGallery.setDetails(Text.Details.MASTERS_DET);


        securityRoom.setExit("west",regaliaGallery);
        securityRoom.setExit("east", guardRoom);
        securityRoom.setDetails(Text.Details.SECURITY_DET);


        guardRoom.setExit("west", securityRoom);
        guardRoom.setExit("north", janitorCloset, true );
        guardRoom.setDetails(Text.Details.GUARD_DET);
        guardRoomItems.add(Uniform);

        janitorCloset.setExit("south", guardRoom, true);
        janitorCloset.setExit("north", serviceTunnel, true);
        janitorCloset.setDetails(Text.Details.JANITOR_DET);
        janitorCloset.setExit("east", secretPassage);


        serviceTunnel.setExit("north", deliveryDock, true);
        serviceTunnel.setExit("south", janitorCloset, true);
        serviceTunnel.setDetails(Text.Details.SERVICE_DET);

        deliveryDock.setExit("north", basementTunnel);
        deliveryDock.setExit("south", serviceTunnel, true);
        deliveryDock.setDetails(Text.Details.DELIVERY_DET);

        basementTunnel.setExit("north", outside);
        basementTunnel.setExit("south", deliveryDock,true);
        basementTunnel.setDetails(Text.Details.BASEMENT_DET);

        garden.setExit("up", balcony, true);
        garden.setExit("south", outside);
        garden.setExit("east", secretPassage);
        garden.setDetails(Text.Details.GARDEN_DET);

        secretPassage.setExit("north", janitorCloset);
        secretPassage.setExit("south", garden);
        secretPassage.setDetails(Text.Details.PASSAGE_DET);

        vip.setExit("north", lobby, true);
        vip.setDetails(Text.Details.VIP_DET);


        player = new User("player", lobby);

        Guards henry = new Guards("Henry", lobby);
        henry.getInventory().add(Gum);
        henry.getInventory().add(KeyCard);
        henry.getInventory().add(Smokes);

        Guards staff1 = new Guards("Gerard", deliveryDock);
        Guards staff2 = new Guards("Jean", deliveryDock);
        Guards staff3 = new Guards("David", deliveryDock);
        Guards staff4 = new Guards("Patrice", regaliaGallery);
        Guards staff5 = new Guards("Jude", regaliaGallery);

        Character.addCharacter(player);
        Character.addCharacter(henry);
       // Character.addCharacter(staff1);
       // Character.addCharacter(staff2);
       // Character.addCharacter(staff3);
        Character.addCharacter(staff4);
        Character.addCharacter(staff5);


        if (player.hasItem("Keycard")) {
            lobby.setExit("south", vip, true);
            mastersGallery.setExit("south", regaliaGallery, true);
            regaliaGallery.setExit("north", mastersGallery , true);
            securityRoom.setExit("east", guardRoom  , true);
        //    deliveryDock.setExit("north", basementTunnel, true);
        }
        if (player.hasItem("Keycard") && (player.hasItem("Uniform"))) {
              regaliaGallery.setExit("east", securityRoom , true);
              securityRoom.setExit("west", regaliaGallery  , true);
              guardRoom.setExit("west", securityRoom, true);
        }

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
                //    deliveryDock.setExit("north", basementTunnel, true);
            }
            if (player.hasItem("Keycard") && (player.hasItem("Uniform"))) {
                regaliaGallery.setExit("east", securityRoom , true);
                securityRoom.setExit("west", regaliaGallery  , true);
                guardRoom.setExit("west", securityRoom, true);
                deliveryDock.setExit("north", basementTunnel, true);
            }

        }
        System.out.println("Thank you for playing. Goodbye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to Rob the Louvre!");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
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
                details();
                System.out.println(player.getCurrentRoom().searchRoom());
                break;
            case "pick":
                Item pickupItem = Item.checkItemAvailable(command.getSecondWord(), player.getCurrentRoom().getItems());
                if (pickupItem == null) {
                    System.out.println("I can't find that item!");
                } else {
                    System.out.println(player.pickUpItem(pickupItem));
                }
                break;
            case "inventory": // plan on displaying inventory whole time anyway
                System.out.println("Inventory:");
                for (Item item : player.getInventory()) {
                    System.out.println(item.getName());
                }
                break;
            case "drop":
                Item dropItem = Item.checkItemAvailable(command.getSecondWord(), player.getInventory());
                if (dropItem == null) {
                    System.out.println("I don't have that item!");
                } else {
                    player.dropItem(dropItem);
                }
                break;
            case "pickpocket":
                steal(command);
               // System.out.println(player.getCurrentRoom().searchRoom());
                System.out.println("\nAll Exits: " + player.getCurrentRoom().getExitString());

                break;
            case "eavesdrop":
                listen();
                System.out.println("\nAll Exits: " + player.getCurrentRoom().getExitString());
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
        System.out.println("You are lost. You are alone. You wander around the university.");
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


            if (player.hasItem("Keycard")) {
                player.getCurrentRoom().setExitOpen(direction, true);
                System.out.println("Keycard opens the door");
                nextRoom = player.getCurrentRoom().getExit(direction);
            }  if (nextRoom == null) {
                System.out.println("You cant go in there yet");
            } else {
            player.setCurrentRoom(nextRoom);
            System.out.println(player.getCurrentRoom().getLongDescription());
        }


}


    private void details() {
        System.out.println(player.getCurrentRoom().inspect());
    }


    private void listen() {

        if (player.getCurrentRoom().getLines() == null) {
            System.out.print("Nothing to hear here");
        }

        for (String lines : Text.Convos.regaliaConvo()) {
            System.out.println(lines);
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }



    public static void main(String[] args) {
        ZorkULGame game = new ZorkULGame();
        game.play();
    }
}
