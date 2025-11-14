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
        validCommands.put("eavesdrop", "Listen in on someone's conversation");
        validCommands.put("pickpocket", "Steal items from an unsuspecting victim");
        validCommands.put("wait", "Wait for the right time");
        validCommands.put("tamper", "Turn the cameras on or off");
        validCommands.put("lie", "Tell a lie to fool someone");
        validCommands.put("take", "picks up an item");
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
