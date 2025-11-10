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
    Scanner ise = new Scanner(System.in);

    public ZorkULGame() {
        createRooms();
        parser = new Parser();
    }

    private void createRooms() {
        Room balcony, outside, lobby, regaliaGallery, mastersGallery, securityRoom, guardRoom, serviceTunnel, janitorCloset, deliveryDock, garden, secretPassage,
                vip, basementTunnel;

        Item KeyCard = new Item("Keycard", "Might come in handy.");
        Item Uniform = new Item("Guards Uniform", "This can be used to blend in.");
        Item Gum = new Item("Hollywood Chewing Gum", "Not much use other then fresh breath");
        Item Smokes = new Item("Marlboro Reds", "Be helping him taking these");

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
        balcony = new Room("stand on a narrow balcony above the riverside façade and gardens. The cherry picker sits below, beside a large tree; a glass window ahead leads into the museum's upper wing.");
        lobby = new Room("are in the grand pyramid lobby. Ticket desks, security scanners and the echo of footsteps fill the space.",  lobbyItems);
        regaliaGallery = new Room("see a glittering hall of crown jewels and diadems behind glass cases. Spotlights and no public make this the obvious prize zone.", regaliaGalleryItems);
        mastersGallery = new Room("are in long gallery of paintings: tourists pause before masterpieces while guards linger at doorways. The steady flow of foot-traffic provides cover.", masterGalleryItems);
        securityRoom = new Room("enter the control room. Monitors line the walls, each screen showing CCTV feeds of corridors, galleries, and exterior walls.", securityItems);
        guardRoom = new Room("enter a small break room with lockers, a coffee machine and a mini fridge. Footsteps echo through the thin walls.", guardRoomItems);
        serviceTunnel = new Room("are under dim lights that hang over pipes and cables. OLd crates line the walls, this tunnel connects hidden zones beneath the museum.", tunnelRoomItems);
        janitorCloset = new Room("see buckets, mops and cleaning supplies in tight quarters. An old door behind stacked carts is interesting.", janitorRoomItems);
        deliveryDock = new Room("see wide loading doors and a rubber-rimmed ramp open to the street. A delivery van often idles outside while staff have a smoke", dockItems);
        garden = new Room("are surrounded by hedges cut into formal shapes, a silent fountain and statues under lights.");
        secretPassage = new Room("enter a hidden passage, a narrow corridor with a low ceiling and no light.", passageItems);
        vip = new Room("are impressed by luxurious sofas and low tables, champagne flutes and private doors to the gallery. Guests in tuxedos are quietly monitored by nearby security.", vipItems);
        basementTunnel = new Room("are in a dim underground garage: engine rumble, an exit ramp to the street and the faint hum of air-conditioning.", basementItems);


        balcony.setExit("north", regaliaGallery, true);
        balcony.setExit("down", garden, true);
        balcony.setDetails("Scuffed paint marks the window frame; the latch looks old and might give with some force.");

        lobby.setExit("west", outside, true);
        lobby.setExit("east", mastersGallery, true);
        lobby.setExit("south", vip);
        lobby.setDetails("The sheer amount of people would make it easy to blend in. You see a guard not paying attention. His name badge displays 'Henry Dumoulin'");
       // lobbyItems.add(KeyCard);

        regaliaGallery.setExit("north",mastersGallery);
        regaliaGallery.setExit("south", balcony);
        regaliaGallery.setExit("east", securityRoom);
        regaliaGallery.setDetails("The is illuminated via the window and spotlights. Two guards are taking a break on a bench on the other side of the hall," +
                                  " with all the displays it would be easy to get close without being spotted.");


        mastersGallery.setExit("south",regaliaGallery);
        mastersGallery.setExit("west", lobby, true);
        mastersGallery.setDetails("The paintings would be a good score but way to many people here. The guards seem to be talking about something over the radio to each-other");


        securityRoom.setExit("west",regaliaGallery);
        securityRoom.setExit("east", guardRoom);
        securityRoom.setDetails("Screens show the camera feed of nearly the whole museum. Guards rotate in-and-out from their break room, one forgets to logout...");


        guardRoom.setExit("west", securityRoom);
        guardRoom.setExit("north", janitorCloset, true );
        guardRoom.setDetails("Half empty coffee cups are scattered on the counter, a card game is waiting to be finished. Lockers line the wall—one hangs ajar.");
        guardRoomItems.add(Uniform);

        janitorCloset.setExit("south", guardRoom);
        janitorCloset.setExit("north", serviceTunnel, true);
        janitorCloset.setDetails("The cramped closet smells of bleach. An old, taped-up door behind stacked carts is interesting.");
        janitorCloset.setExit("east", secretPassage);


        serviceTunnel.setExit("north", deliveryDock, true);
        serviceTunnel.setExit("south", janitorCloset, true);
        serviceTunnel.setDetails("Pipes hiss and ducts vibrate above. Not much to see here it seems.");

        deliveryDock.setExit("north", basementTunnel);
        deliveryDock.setExit("south", serviceTunnel, true);
        deliveryDock.setDetails("Although quite open there is an annoying amount of guards around. It would be foolish to try anything without a disguise.");

        basementTunnel.setExit("north", outside);
        basementTunnel.setExit("south", deliveryDock,true);
        basementTunnel.setDetails("Vans line the side wall. If done right they would be easy to hot wire in order to escape into the city.");

        garden.setExit("up", balcony, true);
        garden.setExit("south", outside);
        garden.setExit("east", secretPassage);
        garden.setDetails("A beaten path in the corner catches your eye. An old seemingly locked door hides at the end of the path.");

        secretPassage.setExit("north", janitorCloset);
        secretPassage.setExit("south", garden);
        secretPassage.setDetails("Old blueprints and staff scribbles mark this 'staff only' route.");

        vip.setExit("north", lobby, true);
        vip.setDetails("Obnoxious tones and low lighting make these people and easy target, but be careful of the guards.");

        player = new User("player", lobby);


        Guards henry = new Guards("Henry", lobby);
        henry.getInventory().add(KeyCard);
        henry.getInventory().add(Gum);
        henry.getInventory().add(Smokes);

        Character.addCharacter(player);
        Character.addCharacter(henry);


    }



    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
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
                Item pickupItem = Items.checkItemAvailable(command.getSecondWord(), player.getCurrentRoom().getItems());
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
                Item dropItem = Items.checkItemAvailable(command.getSecondWord(), player.getInventory());
                if (dropItem == null) {
                    System.out.println("I don't have that item!");
                } else {
                    player.dropItem(dropItem);
                }
                break;
            case "pickpocket":
                steal(command);
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
                    if (!take.equalsIgnoreCase(item.getName())) {
                        System.out.print(i.getName() + " does not have " + take);
                        break;
                    }
                    stolenItem = item;
                    i.getInventory().remove(stolenItem);
                    player.getInventory().add(stolenItem);

                    System.out.println("You stole the " + stolenItem.getName() + " from " + i.getName() + "!\n" + item.getDescription());
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
            System.out.println("There is no door!");
        } else {
            player.setCurrentRoom(nextRoom);
            System.out.println(player.getCurrentRoom().getLongDescription());
        }
    }

    private void details() {
        System.out.println(player.getCurrentRoom().inspect());
    }



    public static void main(String[] args) {
        ZorkULGame game = new ZorkULGame();
        game.play();
    }
}
