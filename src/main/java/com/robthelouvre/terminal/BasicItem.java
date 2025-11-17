package com.robthelouvre.terminal;
import java.io.*;

public class BasicItem  implements Item, Serializable{
    private String description;
    private String name;
    private int id;
    private boolean isVisible;

    public BasicItem(String name, String description) {
        this.name = name;
        this.description = description;
        this.isVisible = true;
    }
    public BasicItem(String name, String description, int id) {
        this.name = name;
        this.description = description;
        this.isVisible = true;
        this.id = id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean getStatus() {
        return isVisible;
    }

    @Override
    public void setStatus(boolean visible) {
        this.isVisible = visible;
    }
}

