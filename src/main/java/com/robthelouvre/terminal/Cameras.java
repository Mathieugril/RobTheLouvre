package com.robthelouvre.terminal;

public class Cameras implements Item{
    private Room Room;
    private boolean isOn;
   // private int id;

    public Cameras() {
        this.Room = Room;
        this.isOn = true;
        //   this.id = id;
    }
    public Cameras(Room Room) {
        this.Room = Room;
        this.isOn = true;
     //   this.id = id;
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
    public boolean getStatus() {
        return isOn;
    }

    @Override
    public void setStatus(boolean status) {
        this.isOn = status;
    }
}
