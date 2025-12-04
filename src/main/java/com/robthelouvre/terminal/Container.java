package com.robthelouvre.terminal;

import java.io.Serializable;

public class Container<T extends Item> implements Serializable {

    private final String name;     // "Glass case", "Locker"
    private final String description;
    private T storedItem;            // the item inside, e.g. Crown or Uniform
    private boolean locked;

    public Container(String name, String description, T storedItem, boolean locked) {
        this.name = name;
        this.description = description;
        this.storedItem = storedItem;
        this.locked = locked;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    public T peek() {
        return storedItem;
    }

    public T takeItem() {
        T result = storedItem;
        storedItem = null;
        return result;
    }
}
