package com.robthelouvre.terminal;

import java.io.Serializable;
import java.util.*;

public class Room implements Serializable {
    private RoomType type;
    private String description;
    private Map<String, Room> exits;
    private List<Item> items;
    private Map<String, Boolean> exitStates = new HashMap<>();
    private boolean access = false;
    private List<String> lines;

    public Room(RoomType type) {
        this.type = type;
        this.description = type.getDescription();
        exits = new HashMap<>();
        this.items = new ArrayList<>();
    }


    public Room(RoomType type, List<Item> items) {
        this.type = type;
        this.description = type.getDescription();
        exits = new HashMap<>();
        this.items = items;
        this.access = true;
    }

    public Room(RoomType type, List<Item> items, List<String> lines) {
        this.type = type;
        this.description = type.getDescription();
        exits = new HashMap<>();
        this.items = items;
        this.access = true;
        this.lines = lines;
    }





    public String inspect() {

        StringBuilder list = new StringBuilder();


        for (Character c : getCharacters()) {
            list.append(" - ").append(c.getName()).append("\n");
        }
        return type.getDetails() + "\nWho is here: \n" + list;

    }


    public List<String> getLines() {
        return lines;
    }



    public void setExit(String direction,Room neighbor) {
        exits.put(direction, neighbor);
    }
    public void setExit(String direction,Room neighbor, boolean access) {
        exits.put(direction, neighbor);
        exitStates.put(direction, access);
    }

    public boolean isExitOpen(String direction) {
        return exitStates.getOrDefault(direction, false);
    }

    public Room getExit(String direction) {
            if (isExitOpen(direction)) {
                return exits.get(direction);
            }
            return null;
        }


    public Set<String> getOpenExitDirections() {
        Set<String> openDirs = new HashSet<>();

        for (String dir : exitStates.keySet()) {
            if (exitStates.get(dir)) {   // true means open
                openDirs.add(dir);
            }
        }
        return openDirs;
    }

    public String searchRoom() {
        StringBuilder itemsList = new StringBuilder();
        if (getItems().isEmpty()) {
            return "No items out in the open";
        }
        if (getItems().size() == 1) {
            return "Is that, a " + getItems().getFirst().getName() + "!";
        }
        itemsList.append("I see ");
        for(int item = 0; item < getItems().size() - 1; item++) {
            itemsList.append("a ");
            itemsList.append(getItems().get(item).getName());
            itemsList.append(", ");
        }
        itemsList.append("and a ");
        itemsList.append(getItems().getLast().getName());
        itemsList.append("!");


        return itemsList.toString();
    }

    public void removeItem(Item item) {
        items.remove(item);
    }
    public void addItem(Item item) {
        items.add(item);
    }
    public List<Item> getItems() {
        return items;
    }

    public String getExitString() {
        StringBuilder sb = new StringBuilder();
        for (String direction : exits.keySet()) {
            sb.append(direction).append(" ");
        }
        return sb.toString().trim();
    }

    public String getLongDescription() {
        StringBuilder sb = new StringBuilder();
        var openDirs = getOpenExitDirections();
        if (openDirs.isEmpty()) {
            sb.append("none");
        } else {
            sb.append(openDirs);
        }

        return "You " + description + "\nExits: " + getExitString()+ "\nOpen exits: "+ sb;

    }
    public RoomType getType() {
        return type;
    }


    public List<Character> getCharacters() {
        List<Character> here = new ArrayList<>();

        for (Character c : Character.getAllCharacters()) {
            if (c.getCurrentRoom() == this) {
                here.add(c);
            }
        }

        return here;
    }
}
