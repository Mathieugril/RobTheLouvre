package com.robthelouvre.terminal;

import java.util.List;

public class Util {

    public static Item checkItemAvailable(String itemName, List<Item> items) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }
}