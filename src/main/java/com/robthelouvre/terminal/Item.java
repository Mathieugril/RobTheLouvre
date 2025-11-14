package com.robthelouvre.terminal;

import java.io.Serializable;


public interface Item extends Serializable{

    String getDescription();
    void setDescription(String description);

    String getName();
    void setName(String name);

    int getId();
    void setID(int id);

    boolean isVisible();
    void setVisible(boolean visible);
}
