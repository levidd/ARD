package com.mwl.util;

// importing enum classes
import com.mwl.environment.Direction;
import com.mwl.environment.Item;

// importing java classes
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.mwl.util.ExitGame.exit;

// TextParser blueprint begins
public class TextParser{

    // interface blueprint to be implemented by parser classes
    private interface Command {
        public void do_command(String option);
    }

    //  Move parser implementing Command interface
    public static class Move implements Command {
        // must implement methods
        @Override
        public void do_command(String direction) {
            // Check if the argument direction do not match a direction in the Direction enum
            if (!Arrays.stream(Direction.values()).anyMatch((directions) -> directions.name().equals(direction))){
                // throw exception if not match found. Catch by parser() method.
                throw new IllegalArgumentException("Move where?");
            }else{
                // this runs if match is found in the Direction enum
                System.out.println("Moving " + direction);
                // here we can run method to do action or make this method return a value to pass to another method
            }
        }
    }

    public static class Flight implements Command {
        @Override
        public void do_command(String option) {
            if (option == null)
                throw new IllegalArgumentException("Flight where?");
            System.out.println("Flying " + option);
            // run method to do the action
        }
    }

    public static class Look implements Command {
        @Override
        public void do_command(String option) {
            if (option == null)
                throw new IllegalArgumentException("Look where?");
            System.out.println("Looking " + option);
            // run method to do the action
        }
    }

    public static class Fight implements Command {
        @Override
        public void do_command(String option) {
            if (option == null)
                throw new IllegalArgumentException("Fight who?");
            System.out.println("fighting " + option);
            // run method to do the action
        }
    }

    public static class Pickup implements Command {
        @Override
        public void do_command(String option) {
            if (option == null)
                throw new IllegalArgumentException("pickup what?");
            System.out.println("Picking up " + option);
            // run method to do the action
        }
    }

    public static class Drop implements Command {
        @Override
        public void do_command(String option) {
            // Save word to use multiple times.
            // Check if the argument word do not match the items in the enum
            if (!Arrays.stream(Item.values()).anyMatch((item) -> item.name().equals(option))){
                throw new IllegalArgumentException("Drop what?");
            }else{
                System.out.println("Dropping " + option);
                // run method to do the action or make this method return a value to pass to another method
            }
        }
    }


    public static void parser(){
        System.out.println("Type something");
        Map<String, Command> commands = new HashMap<>();
        commands.put("pickup", new Pickup());
        commands.put("drop", new Drop());
        commands.put("move", new Move());
        commands.put("flight", new Flight());
        commands.put("look", new Look());
        commands.put("fight",  new Fight());

        // Parse text
        Scanner tryScanner = new Scanner(System.in);
        String playerInput;
        boolean valid = false;
        do {
            playerInput = tryScanner.nextLine();
            // check if input text is "exit." We need to do this on every input scanner.
            exit(playerInput);
            String[] words = playerInput.toLowerCase().split("\\W+");
            if(words.length != 2){
                System.out.println("Invalid command. Try again!");
                continue;
            }else {
                if(commands.containsKey(words[0])){
                    try {
                        String capitalize = words[1].substring(0, 1).toUpperCase() + words[1].substring(1);
                        commands.get(words[0]).do_command(capitalize);
                        valid = true;
                    }catch (IllegalArgumentException e){
                        System.out.println("Not a valid action. Try again!");
                    }
                }else {
                    System.out.println("Not a valid action. Try again!");
                }            }
        }while (!valid);
    }

    public static void main(String[] args) {
        parser();
    }
}