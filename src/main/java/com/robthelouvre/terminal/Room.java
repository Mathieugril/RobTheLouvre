package com.robthelouvre.terminal;

import java.util.HashMap;
import java.util.Map;

public class Room {
    private String description;
    private Map<String, Room> exits; // Map direction to neighboring Room
    private String details;

    public Room(String description) {
        this.description = description;
        this.details = description;
        exits = new HashMap<>();
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String inspect() {
        return details;
    }

    public String getDescription() {
        return description;
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }



    public String getExitString() {
        StringBuilder sb = new StringBuilder();
        for (String direction : exits.keySet()) {
            sb.append(direction).append(" ");
        }
        return sb.toString().trim();
    }

    public String getLongDescription() {
        return "You " + description + "\nExits: " + getExitString();
    }
}
