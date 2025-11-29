package com.robthelouvre.terminal;

public class BasicItem  implements Item{
    private String description;
    private String name;
    private boolean isOn;

    public BasicItem(String name, String description) {
        this.name = name;
        this.description = description;
        this.isOn = true;
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
        return isOn;
    }

    @Override
    public void setStatus(boolean visible) {
        this.isOn = visible;
    }
}

