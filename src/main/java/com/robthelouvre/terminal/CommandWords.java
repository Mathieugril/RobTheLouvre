package com.robthelouvre.terminal;

import java.util.HashMap;
import java.util.Map;

public class CommandWords {
    private static Map<String, String> validCommands;

    public CommandWords() {
        validCommands = new HashMap<>();
        validCommands.put("go", "Move to another room");
        validCommands.put("quit", "End the game");
        validCommands.put("help", "Show help");
        validCommands.put("inspect", "Inspect the room");
        validCommands.put("eavesdrop", "Listen in on someone's conversation");
        validCommands.put("pickpocket", "Steal items from an unsuspecting victim");
        validCommands.put("tamper", "Turn the cameras on or off");
         validCommands.put("lie", "Tell a lie to fool someone");
        validCommands.put("take", "picks up an item");
        validCommands.put("drop", "drops item on floor");
    }

    public boolean isCommand(String commandWord) {
        return validCommands.containsKey(commandWord);
    }

    public static String showAll() {
        StringBuilder out = new StringBuilder();
        out.append("Valid commands are: ").append("\n");
        for (String command : validCommands.keySet()) {
            out.append(command).append(", ");
          //  return
        }
        return out.toString();
    }
}
