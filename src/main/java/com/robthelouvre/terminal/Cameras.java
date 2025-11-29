package com.robthelouvre.terminal;

public class Cameras implements Item{
    private boolean isOn;


    public Cameras() {
        this.isOn = true;

    }

    @Override
    public String getDescription() {
        return "Security system to protect museums integrity";
    }

    @Override
    public String getName() {
        return "PSF: paris safety features";
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
