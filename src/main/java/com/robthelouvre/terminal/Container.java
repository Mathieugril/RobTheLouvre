package com.robthelouvre.terminal;

import java.io.Serializable;

public class Container<T extends Item> implements Serializable {

    private final String name;
    private T storedItem;
    private boolean locked;

    public Container(String name, T storedItem, boolean locked) {
        this.name = name;
        this.storedItem = storedItem;
        this.locked = locked;
    }

    public String getName() {
        return name;
    }

    public boolean isLocked() {
        return locked;
    }

    public void unlock() {
        this.locked = false;
    }

    public boolean isEmpty() {
        return storedItem == null;
    }

    public T takeItem() {
        T result = storedItem;
        storedItem = null;
        return result;
    }
}
