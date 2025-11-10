package com.robthelouvre.terminal;

public class Door {
    private Room target;
    private boolean access;


public Door(Room target, boolean access) {
    this.target = target;
    this.access = access;
}

public Room getTarget() {return target;}
public boolean isAccess() { return access; }
public void setOpen(boolean v) { access = v; }

}