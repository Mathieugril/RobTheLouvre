package com.robthelouvre.terminal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.io.*;
import java.util.List;

public abstract class Character implements Serializable {
    private String name;
    private Room currentRoom;
    private ArrayList<Item> inventory;
    private final ObservableList<String> inventoryGUI = FXCollections.observableArrayList();

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

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public ObservableList<String> getInventoryGUI() {
        return inventoryGUI;
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
    public String dropItem(Item item) {
        currentRoom.addItem(item);
        inventory.remove(item);
        return"Dropped " + item.getName() + "!";
    }


    private static List<Character> allCharacters = new ArrayList<>();

    public static void addCharacter(Character c) {
        allCharacters.add(c);
    }

    public static List<Character> getAllCharacters() {
        return allCharacters;
    }
}
