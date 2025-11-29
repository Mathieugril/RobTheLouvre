package com.robthelouvre.terminal;

public class Cameras implements Item{
    private Room Room;
    private boolean isOn;


    public Cameras() {
        this.Room = Room;
        this.isOn = true;

    }
    public Cameras(Room Room) {
        this.Room = Room;
        this.isOn = true;

    }
    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getName() {
        return "";
    }


    @Override
    public boolean getStatus() {
        return isOn;
    }

    @Override
    public void setStatus(boolean status) {
        this.isOn = status;
    }
}
