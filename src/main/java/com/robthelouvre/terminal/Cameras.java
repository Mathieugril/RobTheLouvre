package com.robthelouvre.terminal;

public class Cameras implements Item{
    private Room Room;
    private boolean isVisible;
    private int id;

    public Cameras(Room Room, boolean isVisible, int id) {
        this.Room = Room;
        this.isVisible = isVisible();
        this.id = id;
    }
    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void setDescription(String description) {

    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setID(int id) {

    }

    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public void setVisible(boolean visible) {

    }
}
