package com.robthelouvre.terminal;

import java.util.ArrayList;
import java.io.*;

public abstract class Character implements Serializable {
    private String name;
    private Room currentRoom;
    private ArrayList<Item> inventory;



    public Character(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
        this.inventory = new ArrayList<Item>();
    }

    public String getName() {
        return name;
    }


    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public void move(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            System.out.println("You moved to: " + currentRoom.getDescription());
        } else {
            System.out.println("You can't go that way!");
        }
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }
    public boolean hasItem(String itemName) {
        for (Item it : inventory) {
            if (it.getName().equalsIgnoreCase(itemName)) return true;
        }
        return false;
    }

    public String pickUpItem(Item item) {
        currentRoom.removeItem(item);
        inventory.add(item);
        return "Picked up " + item.getName() + "!";

    }
    public void dropItem(Item item) {
        currentRoom.addItem(item);
        inventory.remove(item);
        System.out.println("Dropped " + item.getName() + "!");
    }


    private static ArrayList<Character> allCharacters = new ArrayList<>();

    public static void addCharacter(Character c) {
        allCharacters.add(c);
    }

    public static ArrayList<Character> getAllCharacters() {
        return allCharacters;
    }
}
