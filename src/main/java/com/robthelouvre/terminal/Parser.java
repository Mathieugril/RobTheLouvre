package com.robthelouvre.terminal;

import java.util.Scanner;

public class Parser {
    private final CommandWords commands;
    private final Scanner reader;

    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    public Command parseCommand(String inputLine) {
        String word1 = null;
        String word2 = null;
        String word3 = null;

        if (inputLine != null) {
            Scanner tokenizer = new Scanner(inputLine);

            if (tokenizer.hasNext()) {
                word1 = tokenizer.next();
                if (tokenizer.hasNext()) {
                    word2 = tokenizer.next();   // or nextLine() if you want multi-word second part
                    if (tokenizer.hasNext()) {
                        word3 = tokenizer.next();
                    }
                }
            }
                    tokenizer.close();
                }


                if (commands.isCommand(word1)) {
                      return new Command(word1, word2, word3);
                 } else {
                     return new Command(null, word2, word3);
                }
            }

            public String showCommands () {
                return CommandWords.showAll();

            }
        }
