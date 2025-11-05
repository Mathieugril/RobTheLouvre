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

import java.util.Scanner;

public class ZorkULGame {
    private Parser parser;
    private Character player;

    public ZorkULGame() {
        createRooms();
        parser = new Parser();
    }

    private void createRooms() {
        Room outside, lobby, ballroom, bathroom, coatroom, kitchen, garden, office, stairwell, balcony, westhall, oak;

        // create rooms
        outside = new Room("outside the front of the townhall");
        lobby = new Room("in the lobby, guests mingle");
        ballroom = new Room("now in the ballroom is filled with guests");
        bathroom = new Room("in the bathroom");
        coatroom = new Room("in a cloakroom");
        kitchen = new Room("in the kitchen, busy with floor staff and chefs");
        westhall = new Room("in the west-hallway, two doors in front of you and stairs further down hall");
        garden = new Room("outside in the garden, fountain is the centrepiece");
        oak = new Room("the shadow of a tall and broad oak tree sits in silence");


        office = new Room("in the security office, cameras survey *nearly* every inch of the building");
        stairwell = new Room("in the stairwell, it's guarded at the bottom");
        balcony = new Room("overlooking the garden");
        //upstairswesthall = new Room("overlooking the ballroom and can");





        // initialise room exits
        outside.setExit("north", lobby);
        // everywhere else goes to cars

        lobby.setExit("north", ballroom);
        lobby.setExit("south", outside);
        // east brings you to paintings
        lobby.setExit("west", westhall);


        // north just makes you wander around room
        ballroom.setExit("south", lobby);
        ballroom.setExit("east", garden );
        ballroom.setExit("west", kitchen); //costume

        // only one option
        bathroom.setExit("east", westhall);

        // only one option
        coatroom.setExit("east", westhall);

        kitchen.setExit("south", westhall);
        kitchen.setExit("east", ballroom);

        westhall.setExit("north", kitchen); //costume + key
        westhall.setExit("east", lobby);
        // west should give option of bathroom, coatroom and stairwell

        garden.setExit("west", ballroom);
        garden.setExit("south", oak);

        oak.setExit("north", garden);
        oak.setExit("up", balcony);

        // second floor








        // create the player character and start outside
        player = new Character("player", outside);
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
        System.out.println("Welcome to the University adventure!");
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

    public static void main(String[] args) {
        ZorkULGame game = new ZorkULGame();
        game.play();
    }
}
