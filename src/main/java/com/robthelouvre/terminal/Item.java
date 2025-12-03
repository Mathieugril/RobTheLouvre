package com.robthelouvre.terminal;

import java.io.Serializable;


public interface Item extends Serializable{
    String getDescription();
    String getName();
    boolean getStatus();
    void setStatus(boolean visible);
}
