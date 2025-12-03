package com.robthelouvre.terminal;

import java.util.*;

public class CommandWords {
    private static Map<String, String> validCommands;

    public CommandWords() {
        validCommands = new HashMap<>();
        validCommands.put("go", "go");
        validCommands.put("move", "go");

        validCommands.put("inspect", "Inspect the room");
        validCommands.put("search",  "Inspect the room");
        validCommands.put("check",   "Inspect the room");

        validCommands.put("take",    "Picks up an item");
        validCommands.put("grab",    "Picks up an item");
        validCommands.put("pick-up", "Picks up an item");

        validCommands.put("eavesdrop", "Listen in on someone's conversation");
        validCommands.put("listen", "Listen in on someone's conversation");

        validCommands.put("pickpocket", "Steal items from an unsuspecting victim");
        validCommands.put("steal", "Steal items from an unsuspecting victim");

        validCommands.put("tamper", "Turn the cameras on or off");
        validCommands.put("lie", "Tell a lie to fool someone");
        validCommands.put("drop", "drops item on floor");
        validCommands.put("inventory", "Displays items");

        validCommands.put("quit", "End the game");
        validCommands.put("help", "Show help");
        validCommands.put("save", "saves the state of the game");
        validCommands.put("load", "loads the state of the game");
        validCommands.put("restart", "starts game fresh");
        validCommands.put("cheat", "cheats game");

    }

    public boolean isCommand(String commandWord) {
        return validCommands.containsKey(commandWord);
    }

    private static final Set<String> primaryCommands = Set.of(
            "go [direction] ", "\nquit", "help", "\ninspect [room]", "\neavesdrop - listen to guards talking",
            "\npickpocket [person]", "\ntamper - for security systems", "\nlie - to deceive guards", "\ntake [item]", "\ndrop [item]",
            "\ninventory", "save", "load", "restart", "cheat"
    );

    public static String showAll() {
        StringBuilder out = new StringBuilder();
        out.append("Valid commands are:\n");
        for (String command : primaryCommands) {
            out.append(command).append(", ");
        }
        return out.toString();
    }
}
