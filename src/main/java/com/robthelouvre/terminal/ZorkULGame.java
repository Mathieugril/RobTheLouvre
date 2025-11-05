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

public class ZorkULGame {
    private Parser parser;
    private Character player;

    public ZorkULGame() {
        createRooms();
        parser = new Parser();
    }

    private void createRooms() {
        Room balcony, outside, lobby, regaliaGallery, mastersGallery, securityRoom, guardRoom, serviceTunnel, janitorCloset, deliveryDock, garden, secretPassage,
                vip, basementTunnel;

        outside = new Room("are out of the Louvre");
        balcony = new Room("stand on a narrow balcony above the riverside façade and gardens. The cherry picker sits below, beside a large tree; a glass window ahead leads into the museum's upper wing.");
        lobby = new Room("are in the grand pyramid lobby as it pulses with visitors and guards. Ticket desks, security scanners and the echo of footsteps fill the space.");
        regaliaGallery = new Room("see a glittering hall of crown jewels and diadems behind glass cases. Spotlights and no public make this the obvious prize zone.");
        mastersGallery = new Room("are in long gallery of paintings: tourists pause before masterpieces while guards linger at doorways. The steady flow of foot-traffic provides cover.");
        securityRoom = new Room("enter the control room. Monitors line the walls, each screen showing CCTV feeds of corridors, galleries, and exterior walls.");
        guardRoom = new Room("enter a small break room with lockers, a coffee machine and spare uniforms. Footsteps echo through the thin walls.");
        serviceTunnel = new Room("are under dim lights that hang over pipes and cables. Paint-stenciled crates line the walls — this tunnel connects hidden zones beneath the museum.");
        janitorCloset = new Room("see buckets, mops and cleaning supplies in tight quarters. An old door behind stacked carts is interesting.");
        deliveryDock = new Room("see wide loading doors and a rubber-rimmed ramp open to the street. A delivery van often idles outside while staff have a smoke");
        garden = new Room("are surrounded by hedges cut into formal shapes, a silent fountain and statues under lights. A beaten path in the corner catches your eye.");
        secretPassage = new Room("enter a hidden passage, a narrow corridor with a low ceiling. Old blueprints and staff scribbles mark this 'staff only' route.");
        vip = new Room("are impressed by luxurious sofas and low tables, champagne flutes and private doors to the gallery. Guests in tuxedos are quietly monitored by nearby security.");
        basementTunnel = new Room("are in a dim underground garage: engine rumble, an exit ramp to the street and the faint hum of air-conditioning.");


        balcony.setExit("north", regaliaGallery);
        balcony.setExit("down", garden);
        balcony.setDetails("Scuffed paint marks the window frame; the latch looks old and might give with some force.");

        lobby.setExit("west", outside);
        lobby.setExit("east", mastersGallery);
        lobby.setExit("south", vip);
        lobby.setDetails("The sheer amount of people would make it easy to blend in");

        regaliaGallery.setExit("north",mastersGallery);
        regaliaGallery.setExit("south", balcony);
        regaliaGallery.setExit("east", securityRoom);
      //  regaliaGallery

        mastersGallery.setExit("south",regaliaGallery);
        mastersGallery.setExit("west", lobby);

        securityRoom.setExit("west",regaliaGallery);
        securityRoom.setExit("east", guardRoom);

        guardRoom.setExit("west", securityRoom);
        guardRoom.setExit("north", janitorCloset );

        janitorCloset.setExit("south", guardRoom);
        janitorCloset.setExit("north", serviceTunnel);
       // janitorCloset.setExit("east", secretPassage);

        serviceTunnel.setExit("north", deliveryDock);
        serviceTunnel.setExit("south", janitorCloset);

        deliveryDock.setExit("north", basementTunnel);
        deliveryDock.setExit("south", serviceTunnel);

        basementTunnel.setExit("north", outside);
        basementTunnel.setExit("south", deliveryDock);

        garden.setExit("up", balcony);
        garden.setExit("south", outside);
      //  garden.setExit("east", secretPassage);

        secretPassage.setExit("north", janitorCloset);
        secretPassage.setExit("south", garden);

        vip.setExit("north", lobby);





                player = new Character("player", balcony );
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
            case "inspect":
                details();
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

    private String details() {
        return player.getCurrentRoom().inspect();
    }


    public static void main(String[] args) {
        ZorkULGame game = new ZorkULGame();
        game.play();
    }
}
