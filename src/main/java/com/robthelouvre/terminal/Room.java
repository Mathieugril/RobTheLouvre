package com.robthelouvre.terminal;

import java.util.*;

public class Room {
    private String description;
    private Map<String, Room> exits; // Map direction to neighboring Room
    private String details;
    private ArrayList<Item> items;
    private Map<String, Boolean> exitStates = new HashMap<>();
    private boolean access = false;

    public Room(String description) {
        this.description = description;
        this.details = description;
        exits = new HashMap<>();
        this.items = new ArrayList<Item>();

    }

    public Room(String description, ArrayList<Item> items) {
        this.description = description;
        this.details = description;
        exits = new HashMap<>();
        this.items = items;
        this.access = true;
    }




    public void setDetails(String details) {
        this.details = details;
    }
    public String inspect() {

        StringBuilder list = new StringBuilder();
        boolean found = false;

        for(Character i : Character.getAllCharacters()) {
            if(i.getCurrentRoom().equals(this)) {
                list.append(" - ").append(i.getName()).append("\n");
                found = true;
            }
        }
        return details + "\nWho is here: \n" + list;
      //  return details;

    }

    public String getDescription() {
        return description;
    }


    public boolean isOpen() { return access; }
    public void setOpen(boolean open) { this.access = open; }


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

    public void setExitOpen(String direction, boolean open) {
        if (exits.containsKey(direction)) {
            exitStates.put(direction, open);
        }
    }

        public Room getExit(String direction) {
            if (isExitOpen(direction)) {
                return exits.get(direction);
            }
            return null;
        }

    public void toggleExit(String direction) {
        setExitOpen(direction, !isExitOpen(direction));
    }
    public Set<String> getAllExitDirections() {
        return exits.keySet();
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
            return "Is that... a " + getItems().getFirst().getName() + "!";
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
    public ArrayList<Item> getItems() {
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
            sb.append(String.join(", ", openDirs));
        }

        return "You " + description + "\nExits: " + getExitString()+ "\nOpen exits: "+ sb.toString();
    }





}
