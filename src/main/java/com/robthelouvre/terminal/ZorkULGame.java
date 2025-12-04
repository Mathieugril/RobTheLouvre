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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;



public class ZorkULGame{
    private Parser parser;
    private User player;
    private Room balcony, outside, lobby, regaliaGallery, mastersGallery,
            securityRoom, guardRoom, serviceTunnel, janitorCloset,
            deliveryDock, garden, secretPassage, vip, basementTunnel, van;
    private Cameras regaliaCamera, deliveryScanner;
    private Guards patrick, jerry, sean, david, jude;
    private Item Uniform, Flashlight, Crown, VanKeys;
    private boolean finished = false;
    private boolean isPassageKnown = false;

    public User getPlayer() {
        return player;
    }

    public Guards getPatrick() { return patrick; }
    public Guards getJude()    { return jude; }

    public boolean isFinished() {
        return finished;
    }

    public ZorkULGame() {
        create();
        parser = new Parser();
    }

    public String getWelcomeText() {
        StringBuilder out = new StringBuilder();
        out.append("Welcome to Rob the Louvre!\n");
        out.append("Type 'help' if you need help.\n\n");
        out.append(player.getCurrentRoom().getLongDescription()).append("\n");
        return out.toString();


    }

    public void create() {

        Uniform = new BasicItem("Uniform", Text.ItemDESC.UNIFORM);

        Flashlight = new BasicItem("Flashlight", Text.ItemDESC.FLASHLIGHT);
        Crown = new BasicItem("Crown", Text.ItemDESC.CROWN);

        VanKeys = new BasicItem("Van-Key", Text.ItemDESC.VANKEYS);

        Item Chalk = new BasicItem("Chalk",Text.ItemDESC.SNUS);
        Item money = new BasicItem("Fat-Wallet", Text.ItemDESC.MONEY);

        Item Headphones = new BasicItem("Headphones", Text.ItemDESC.HEADPHONES);
        Item Waffles = new BasicItem("Waffles", Text.ItemDESC.WAFFLES);

        Item Snus = new BasicItem("Snus", Text.ItemDESC.SNUS);
        Item Monster = new BasicItem("Monster", Text.ItemDESC.MONSTER);

        Item Bread = new BasicItem("Bread", Text.ItemDESC.BREAD);
        Item KeyCard = new BasicItem("Keycard", Text.ItemDESC.KEYCARD);


        regaliaCamera = new Cameras();
        deliveryScanner = new Cameras();

        List<Item> regaliaGalleryItems = new ArrayList<>();
        List<Item> securityItems = new ArrayList<>();
        List<Item> guardRoomItems = new ArrayList<>();
        List<Item> dockItems = new ArrayList<>();


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
        regaliaGalleryItems.add(Flashlight);
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
        basementTunnel.setExit("south", van, true);

        garden.setExit("up", balcony, true);
        garden.setExit("south", outside);
        garden.setExit("east", secretPassage);

        secretPassage.setExit("north", janitorCloset);
        secretPassage.setExit("back", garden, true);

        vip.setExit("north", lobby, true);;

        van.setExit("out", basementTunnel, true);
        van.setExit("north", basementTunnel, true);


        player = new User("Player", balcony);

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
        jude.getInventory().add(Chalk);
        patrick.getInventory().add(money);

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


        return out.toString();
    }

    public boolean processCommand(Command command, StringBuilder out) {
        String commandWord = command.getCommandWord();

        if (commandWord == null) {
            out.append("I don't understand your command...");
            return false;
        }

        switch (commandWord.toLowerCase()) {
            case "help":
                appendHelp(out);
                break;
            case "cheat":
                cheatGame();
                out.append("\nGame cheated! Head north to freedom.\n");
                break;
            case "go", "move":
                goRoom(command, out);
                break;
            case "inspect", "search", "check":
                out.append(player.getCurrentRoom().inspect()).append("\n");
                out.append(player.getCurrentRoom().searchRoom()).append("\n");
                break;
            case "take", "pick-up", "grab":
                Item takeItem = ItemsUtil.checkItemAvailable(command.getSecondWord(), player.getCurrentRoom().getItems());
                if (takeItem == null) {
                    out.append("I can't find that item!");
                } else {
                    out.append(player.pickUpItem(takeItem)).append("\n");
                    out.append(takeItem.getDescription()).append("\n");
                    out.append("\nAll Exits: ").append(player.getCurrentRoom().getExitString()).append("\n");
                }
                for (Item item : player.getInventory()) {
                    if (item.getName().equals("Crown")){
                       if (regaliaCamera.getStatus()){
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
            case "inventory":
                out.append("Inventory:");
                for (Item item : player.getInventory()) {
                    out.append(item.getName()).append(", ");
                }
                break;
            case "drop":
                Item dropItem = ItemsUtil.checkItemAvailable(command.getSecondWord(), player.getInventory());
                if (dropItem == null) {
                    out.append("I don't have that item!");
                } else {
                    player.dropItem(dropItem);
                }
                break;
            case "pickpocket", "steal":
                steal(command, out);
                out.append("\nAll Exits: ").append(player.getCurrentRoom().getExitString()).append("\n");
                break;
            case "eavesdrop", "listen":
                if(player.getCurrentRoom().equals(regaliaGallery)) {
                    setSecretPassage(true);
                    this.isPassageKnown = true;
                }
                break;
            case "tamper":
                if(player.getCurrentRoom().equals(securityRoom)) {
                    regaliaCamera.setStatus(false);
                    out.append("Cameras in gallery have been disabled!");
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
                       out.append(Guards.lie(this, command, guardRoom));
                   } else {
                       out.append("You have no disguise,guards caught you.").append("\n");
                       return true;
                   }
                } else {
                    out.append("This doesn't benefit you here.").append("\n");
                }
                break;
            case "save":
                saveGame("savegame.dat");
                out.append("Game saved.\n");
                break;
            case "load":
                loadGame("savegame.dat");
                out.append("Game loaded.\n");
                break;
            case "restart":
                restartGame();
                out.append("Game restart.\n \n");
                out.append(getWelcomeText());
                break;
            case "quit":
                if (command.hasSecondWord()) {
                    out.append("Quit what?").append("\n");
                    return false;
                } else {
                    return true;
                }
            default:
                out.append("I don't know what you mean...").append("\n");
                break;
        }
        return false;
    }

    private void appendHelp(StringBuilder out) {
        out.append("You are in the middle of a heist. You are alone. You wander around the museum.\n");
        out.append(parser.showCommands()).append("\n");
    }

    private void goRoom(Command command, StringBuilder out) {
        if (!command.hasSecondWord()) {
            out.append("Go where?\n");
            return;
        }

        String direction = command.getSecondWord().toLowerCase();
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            out.append("You can't go there yet.\n");
        } else {
            player.setCurrentRoom(nextRoom);
            out.append(player.getCurrentRoom().getLongDescription()).append("\n");
        }
    }

    public RoomType getCurrentRoomType() {
        return player.getCurrentRoom().getType();
    }

    public void setSecretPassage(boolean passage) {
        this.isPassageKnown = passage;
    }

    public boolean isSecretPassageKnown() {
        return isPassageKnown;
    }

    private void steal(Command command, StringBuilder out)  {
        if (!command.hasSecondWord()) {
            out.append("Steal from who?\n");
            return;
        }

        String personName = command.getSecondWord();
        Character target = null;



        for (Character c : player.getCurrentRoom().getCharacters()) {
            if (c == player) continue;
            if (personName.equalsIgnoreCase(c.getName())) {
                target = c;
                break;
            }
        }

        if (target == null || !player.getCurrentRoom().equals(target.getCurrentRoom())) {
            out.append("There is no one called ").append(personName).append(" here.\n");
            return;
        }

        if (target.getInventory().isEmpty()) {
            out.append(target.getName()).append(" has nothing to steal.\n");
            return;
        }

        if (!command.hasThirdWord()) {
            out.append("\n").append(target.getName()).append(" has:\n");
            for (Item item : target.getInventory()) {
                out.append(" - ").append(item.getName()).append("\n");
            }
            out.append("\nTo steal something, type: pickpocket ").append(target.getName()).append(" [item name] \n");
            return;
        }

        String itemName = command.getThirdWord();
        Item stolenItem = target.findItemByName(itemName);

        if (stolenItem != null) {

            target.dropItem(stolenItem);
            player.pickUpItem(stolenItem);

            out.append("You stole the ").append(stolenItem.getName()).append(" from ").append(target.getName()).append("!\n").append(stolenItem.getDescription()).append("\n");

        } else {
            out.append(target.getName()).append(" does not have ").append(itemName).append(".\n");
        }
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

    public void saveGame(String fileName) {
        SaveLoadData data = new SaveLoadData();

        data.currentRoomType = player.getCurrentRoom().getType();

        for (Item item : player.getInventory()) {
            data.playerItems.add(item.getName());
        }

        data.passageKnown = isPassageKnown;
        data.regCamOn = regaliaCamera.getStatus();
        data.deliveryScannerOn = deliveryScanner.getStatus();

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Room findRoomByType(RoomType type) {

        return switch (type) {
            case OUTSIDE        -> outside;
            case BALCONY        -> balcony;
            case LOBBY          -> lobby;
            case REGALIA        -> regaliaGallery;
            case MASTERS        -> mastersGallery;
            case CONTROL        -> securityRoom;
            case BREAK          -> guardRoom;
            case SERVICE_TUNNEL -> serviceTunnel;
            case JANITOR_CLOSET -> janitorCloset;
            case DELIVERY_DOCK  -> deliveryDock;
            case GARDEN         -> garden;
            case SECRET_PASSAGE -> secretPassage;
            case VIP            -> vip;
            case BASEMENT       -> basementTunnel;
            case VAN            -> van;

        };
    }

    public void loadGame(String fileName) {
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(fileName))) {

            SaveLoadData data = (SaveLoadData) in.readObject();

            create();

            Room restoredRoom = findRoomByType(data.currentRoomType);
            player.setCurrentRoom(restoredRoom);

            player.getInventory().clear();
            for (String itemName : data.playerItems) {

                Item restored = new BasicItem(itemName, "Restored item: " + itemName);
                player.getInventory().add(restored);
            }



            this.isPassageKnown = data.passageKnown;
            if (isPassageKnown) {
                garden.setExit("east", secretPassage, true);
            }

            regaliaCamera.setStatus(data.regCamOn);
            deliveryScanner.setStatus(data.deliveryScannerOn);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void restartGame() {
        Character.resetAll();
        create();
        finished = false;
        isPassageKnown = false;
        regaliaCamera.setStatus(true);
        deliveryScanner.setStatus(true);
    }

    public void cheatGame() {
        isPassageKnown = true;
        regaliaCamera.setStatus(false);
        deliveryScanner.setStatus(true);
        player.setCurrentRoom(serviceTunnel);
        player.getInventory().add(Flashlight);
        player.getInventory().add(Crown);
        player.getInventory().add(Uniform);
        player.getInventory().add(VanKeys);
        deliveryDock.setExit("inside", van, true);


    }


    public static void main(String[] args) {
        ZorkULGame game = new ZorkULGame();

    }
}
