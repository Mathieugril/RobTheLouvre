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
import java.util.List;
import java.util.Scanner;

public class ZorkULGame {
    private Parser parser;
    public static User player;
    private static Room balcony, outside, lobby, regaliaGallery, mastersGallery,
            securityRoom, guardRoom, serviceTunnel, janitorCloset,
            deliveryDock, garden, secretPassage, vip, basementTunnel, van;
    private Cameras regCam, deliveryScanner;
    public static Guards patrick, jerry, sean, david, jude;
    Scanner ise = new Scanner(System.in);
    private boolean finished = false;
    private boolean passage = false;// for GUI usage as well

    public boolean isFinished() {
        return finished;
    }

    public ZorkULGame() {
        create();
        parser = new Parser();
    }

    public void create() {

        Item Uniform = new BasicItem("Uniform", Text.ItemDESC.UNIFORM);

        Item FlashLight = new BasicItem("Flashlight", Text.ItemDESC.FLASHLIGHT);
        Item Crown = new BasicItem("Crown", Text.ItemDESC.CROWN);

        Item Headphones = new BasicItem("Headphones", Text.ItemDESC.HEADPHONES);
        Item Waffles = new BasicItem("Waffles", Text.ItemDESC.WAFFLES);

        Item Snus = new BasicItem("Snus", Text.ItemDESC.SNUS);
        Item Monster = new BasicItem("Monster", Text.ItemDESC.MONSTER);

        Item Bread = new BasicItem("Bread", Text.ItemDESC.BREAD);
        Item KeyCard = new BasicItem("Keycard", Text.ItemDESC.KEYCARD);

        Item VanKeys = new BasicItem("Van-Key", Text.ItemDESC.VANKEYS);

         regCam = new Cameras(regaliaGallery);
         deliveryScanner = new Cameras();

        List<Item> regaliaGalleryItems = new ArrayList<Item>();
        List<Item> securityItems = new ArrayList<Item>();
        List<Item> guardRoomItems = new ArrayList<Item>();
        List<Item> dockItems = new ArrayList<Item>();


        outside = new Room(RoomType.OUTSIDE);
        balcony = new Room(RoomType.BALCONY);
        lobby = new Room(RoomType.LOBBY);
        regaliaGallery = new Room(RoomType.REGALIA, regaliaGalleryItems, Text.Convos.regaliaConvo());
        mastersGallery = new Room(RoomType.MASTERS);
        securityRoom = new Room(RoomType.CONTROL, securityItems);
        guardRoom = new Room(RoomType.BREAK, guardRoomItems);
        serviceTunnel = new Room(RoomType.SERVICE_TUNNEL);
        janitorCloset = new Room(RoomType.JANITOR_CLOSET);
        deliveryDock = new Room(RoomType.DELIVERY_DOCK, dockItems, Text.Convos.deliveryConvo());
        garden = new Room(RoomType.GARDEN);
        secretPassage = new Room(RoomType.SECRET_PASSAGE);
        vip = new Room(RoomType.VIP);
        basementTunnel = new Room(RoomType.BASEMENT);
        van = new Room(RoomType.VAN);


        balcony.setExit("north", regaliaGallery, true);
        balcony.setExit("down", garden, true);

        lobby.setExit("west", outside);
        lobby.setExit("east", mastersGallery, true);
        lobby.setExit("south", vip);

        regaliaGallery.setExit("north", mastersGallery);
        regaliaGallery.setExit("south", balcony, true);
        regaliaGallery.setExit("east", securityRoom);
        regaliaGalleryItems.add(FlashLight);
        regaliaGalleryItems.add(Crown);

        mastersGallery.setExit("south", regaliaGallery);
        mastersGallery.setExit("west", lobby, true);

        securityRoom.setExit("west", regaliaGallery);
        securityRoom.setExit("east", guardRoom);
        securityItems.add(VanKeys);

        guardRoom.setExit("west", securityRoom);
        guardRoom.setExit("north", janitorCloset, true);
        guardRoomItems.add(Uniform);

        janitorCloset.setExit("south", guardRoom, true);
        janitorCloset.setExit("north", serviceTunnel, true);
        janitorCloset.setExit("east", secretPassage);

        serviceTunnel.setExit("north", deliveryDock, true);
        serviceTunnel.setExit("south", janitorCloset, true);

        deliveryDock.setExit("south", serviceTunnel, true);

        basementTunnel.setExit("north", outside, true);
        basementTunnel.setExit("south", deliveryDock, true);

        garden.setExit("up", balcony, true);
        garden.setExit("south", outside);
        garden.setExit("east", secretPassage);

        secretPassage.setExit("north", janitorCloset);
        secretPassage.setExit("back", garden, true);

        vip.setExit("north", lobby, true);;

        van.setExit("out", basementTunnel, true);
        van.setExit("north", basementTunnel, true);


        player = new User("Player", regaliaGallery);

        jerry = new Guards("Gerard", guardRoom);
        jerry.getInventory().add(Headphones);
        jerry.getInventory().add(Waffles);

        sean = new Guards("Jean", guardRoom);
        sean.getInventory().add(Snus);
        sean.getInventory().add(Monster);

        david = new Guards("David", guardRoom);
        david.getInventory().add(Bread);
        david.getInventory().add(KeyCard);

        Guards randomGuards = new Guards("Guards", securityRoom);

        patrick = new Guards("Patrice", regaliaGallery);
        jude = new Guards("Jude", regaliaGallery);

        Guards scott = new Guards("Scott", deliveryDock);
        Guards dylan = new Guards("Dylan", deliveryDock);

        Character.addCharacter(player);
        Character.addCharacter(jerry);
        Character.addCharacter(sean);
        Character.addCharacter(david);
        Character.addCharacter(patrick);
        Character.addCharacter(jude);
        Character.addCharacter(scott);
        Character.addCharacter(dylan);
        Character.addCharacter(randomGuards);
    }

    public Command parseCommand(String line) {
        return parser.parseCommand(line); // the new Parser method we wrote earlier
    }


    public String processInput(String input) {
        StringBuilder out = new StringBuilder();

        Command command = parser.parseCommand(input);
        finished = processCommand(command, out);

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
                RoomType.SECRET_PASSAGE.setDetails(Text.Details.PASSAGE_DET2);
            }
            if ((player.hasItem("Crown")) && (player.getCurrentRoom().equals(outside))){
                out.append("\n\nYou have escaped with the Crown of Empress Eugénie, Congrats!!\n");
                finished = true;
            }

       // out.append("Thank you for playing. Goodbye.\n");
//
        return out.toString();
    }


        public String getWelcomeText() {
            StringBuilder out = new StringBuilder();
            out.append("Welcome to Rob the Louvre!\n");
            out.append("Type 'help' if you need help.\n\n");
            out.append(player.getCurrentRoom().getLongDescription()).append("\n");
            return out.toString();

//        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("player.ser"))) {
//            User deserializedPerson = (User) in.readObject();
//            System.out.println("Object has been deserialized:");
//       //     for (Item i : deserializedPerson.getInventory()) {
//         //       System.out.println(i.getName());
//           // }
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }

    }

    public RoomType getCurrentRoomType() {
        return player.getCurrentRoom().getType();
    }

    public boolean processCommand(Command command, StringBuilder out) {
        String commandWord = command.getCommandWord();

        if (commandWord == null) {
            out.append("I don't understand your command...");
            return false;
        }

        switch (commandWord) {
            case "help":
                appendHelp(out);
                break;
            case "go":
                goRoom(command, out);
                break;
            case "inspect":
                out.append(player.getCurrentRoom().inspect()).append("\n");
                out.append(player.getCurrentRoom().searchRoom()).append("\n");
                break;
            case "take":
                Item takeItem = Util.checkItemAvailable(command.getSecondWord(), player.getCurrentRoom().getItems());
                if (takeItem == null) {
                    out.append("I can't find that item!");
                } else {
                    out.append(player.pickUpItem(takeItem)).append("\n");
                    out.append(takeItem.getDescription()).append("\n");
                    out.append("\nAll Exits: " + player.getCurrentRoom().getExitString()).append("\n");
                }
                for (Item item : player.getInventory()) {
                    if (item.getName().equals("Crown")){
                       if (regCam.getStatus()){
                           out.append("Cameras caught you, Game over!!\n");
                            return true;
                        }
                       if (jude.getCurrentRoom().equals(regaliaGallery)) {
                           out.append("Guards caught you, Game over!!\n");
                           return true;
                       }
                       RoomType.BALCONY.setDetails(Text.Details.BALCONY_DET2);
                       RoomType.BALCONY.setDescription(Text.Descriptions.BALCONY2);
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
                    out.append("I don't have that item!");
                } else {
                    player.dropItem(dropItem);
                }
                break;
            case "pickpocket":
                steal(command, out);
                out.append("\nAll Exits: ").append(player.getCurrentRoom().getExitString()).append("\n");
                break;
            case "eavesdrop":
                if(player.getCurrentRoom().equals(regaliaGallery)) {
                    setSecretPassage(true);
                    this.passage = true;
                }
              //  List<String> lines = listen();
            //    playLines(lines);
               // out.append("\nAll Exits: ").append(player.getCurrentRoom().getExitString()).append("\n");
                break;
            case "tamper":
                if(player.getCurrentRoom().equals(securityRoom)) {
                    regCam.setStatus(false);
                    System.out.println("Cameras in gallery have been disabled!");
                } else if (player.getCurrentRoom().equals(deliveryDock)) {
                    deliveryScanner.setStatus(false);
                    out.append("Scanner has been disabled!").append("\n");
                } else {
                    out.append("Nothing to mess with here.").append("\n");
                }

                break;
            case "lie":
                if (player.getCurrentRoom().equals(regaliaGallery)) {
                   if (player.hasItem("Uniform")) {
                       Guards.lie(guardRoom);
                   } else {
                       out.append("You have no disguise,guards caught you.").append("\n");
                       return true;
                   }
                } else {
                    out.append("This doesn't benefit you here.").append("\n");
                }
                break;
            case "quit":
                if (command.hasSecondWord()) {
                    out.append("Quit what?").append("\n");
                    return false;
                } else {
                    return true; // signal to quit
                }
            default:
                out.append("I don't know what you mean...").append("\n");
                break;
        }
        return false;
    }

    private void appendHelp(StringBuilder out) {
        out.append("You are in the middle of a heist. You are alone. You wander around the museum.\n");
        out.append("Your command words are: ");
        out.append(parser.showCommands()).append("\n"); // you may need a method that returns a String
    }

    private void goRoom(Command command, StringBuilder out) {
        if (!command.hasSecondWord()) {
            out.append("Go where?\n");
            return;
        }

        String direction = command.getSecondWord();
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            out.append("You can't go there yet.\n");
        } else {
            player.setCurrentRoom(nextRoom);
            out.append(player.getCurrentRoom().getLongDescription()).append("\n");
        }
    }

    public void setSecretPassage(boolean passage) {
        this.passage = passage;
    }

    public boolean isSecretPassageKnown() {
        return passage;
    }

    private void steal(Command command, StringBuilder out)  {
        if (!command.hasSecondWord()) {
            out.append("Steal what?").append("\n");
            return;
        }

        String choice = command.getSecondWord();

        for (Character i : Character.getAllCharacters()) {
            if (choice.equalsIgnoreCase(i.getName())) {

                if (i.getInventory().isEmpty()) {
                    out.append(i.getName()).append(" has nothing to steal.").append("\n");
                    return;
                }

                out.append(i.getName()).append(" has:").append("\n");
                for (Item item : i.getInventory()) {
                    out.append(" - ").append(item.getName()).append("\n");
                }

                out.append("What would you like to take? ").append("\n");
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
                    out.append("You stole the ").append(stolenItem.getName()).append(" from ").append(i.getName()).append("!\n").append(stolenItem.getDescription()).append("\n");
                    return;
                } else {
                    out.append(i.getName()).append(" does not have ").append(take).append("\n");
                    return;
                }
            }
        }
        out.append("There is no one named ").append(choice).append(" here to steal from.").append("\n");

    }
    public List<String> listen() {
        List<String> out = new ArrayList<>();

        if (player.getCurrentRoom().getLines() == null) {
            out.add("Nothing to hear here \n");
        }
        if (player.getCurrentRoom().equals(regaliaGallery)) {
            garden.setExit("east", secretPassage, true);
            for (String lines : Text.Convos.regaliaConvo()) {
                out.add(lines+ "\n");
            }
        } else if (player.getCurrentRoom().equals(deliveryDock)) {
            for (String lines : Text.Convos.deliveryConvo()) {
                out.add(lines + "\n");
            }
            if (player.hasItem("Van-Key")) {
                deliveryDock.setExit("inside", van, true);
            }
        }
        return out;

    }

    public static void main(String[] args) {
        ZorkULGame game = new ZorkULGame();
       // game.play();
    }
}
