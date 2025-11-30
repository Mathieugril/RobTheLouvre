package com.robthelouvre.terminal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class SaveLoadData implements Serializable {
    private static final long serialVersionUID = 1L;

    RoomType currentRoomType;
    List<String> playerItems = new ArrayList<>();
    boolean passageKnown;
    boolean regCamOn;
    boolean deliveryScannerOn;
}