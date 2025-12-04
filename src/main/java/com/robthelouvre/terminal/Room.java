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
        StringBuilder characterList = new StringBuilder();
        for (Character c : getCharacters()) {
            characterList.append(" - ").append(c.getName()).append("\n");
        }

        StringBuilder itemsList = new StringBuilder();


        if (getItems().isEmpty()) {
            itemsList.append("No items out in the open");
        } else if (getItems().size() == 1) {
            itemsList.append("\nIs that, a ").append(getItems().getFirst().getName()).append("!");
        } else {
            itemsList.append("I see ");
            for (int i = 0; i < getItems().size() - 1; i++) {
                itemsList.append("a ").append(getItems().get(i).getName()).append(", ");
            }
            itemsList.append("and a ").append(getItems().getLast().getName()).append("!");
        }

        if (!containers.isEmpty()) {
            itemsList.append("\nYou notice:\n");
            for (Container<?> c : containers) {
                itemsList.append(" - ").append(c.getName());
                itemsList.append("\n");
            }
        }

        return type.getDetails() + "\nWho is here: \n" + characterList + itemsList;
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

    private final List<Container<? extends Item>> containers = new ArrayList<>();

    public void addContainer(Container<? extends Item> c) {
        containers.add(c);
    }

    public List<Container<? extends Item>> getContainers() {
        return containers;
    }
}
