package com.robthelouvre.terminal;

import java.util.HashMap;
import java.util.Map;

public class CommandWords {
    private Map<String, String> validCommands;

    public CommandWords() {
        validCommands = new HashMap<>();
        validCommands.put("go", "Move to another room");
        validCommands.put("quit", "End the game");
        validCommands.put("help", "Show help");
        validCommands.put("inspect", "Inspect the room");
        validCommands.put("eat", "Eat something");
       // validCommands.put("climb", "Using a ladder");
       // validCommands.put("eavesdrop", "Listen in on someone's conversation");
       // validCommands.put("pickpocket", "Steal items from an unsuspecting victim");
        //validCommands.put("open", "Open an object that interests you");
       // validCommands.put("0", "2");
        validCommands.put("pick", "picks up an item");
        validCommands.put("drop", "drops item on floor");
    }

    public boolean isCommand(String commandWord) {
        return validCommands.containsKey(commandWord);
    }

    public void showAll() {
        System.out.print("Valid commands are: ");
        for (String command : validCommands.keySet()) {
            System.out.print(command + " ");
        }
        System.out.println();
    }
}
