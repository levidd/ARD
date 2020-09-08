package com.mwl.environment;

import com.mwl.characters.Player;
import com.mwl.util.Codes;
import com.mwl.util.ConsoleManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Chest {
    private List<Item> reward;
    private Puzzle puzzle;
    private boolean broken;

    public Chest(Puzzle puzzle) {
        this.puzzle = puzzle;
        reward = makeAward();
    }


    private List<Item> makeAward() {
        List<Item> result = new ArrayList<>();
        int scale = puzzle.getDifficultyInt();
        int items = Item.values().length;
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(scale, scale * 2); i++) {
            result.add(Item.values()[ThreadLocalRandom.current().nextInt(items)]);
        }
        return result;
    }

    public List<Item> askQuestion() {
        List<Item> result = new ArrayList<>();
        if (!broken) {
            System.out.println("You approach a chest and a question appears...");
            System.out.println(puzzle.getQuestion());
            List<String> answers = puzzle.getAllAnswers();
            int choice = getInput(IntStream.range(0, answers.size()).mapToObj(e -> List.of("" + e, answers.get(e)))
                    .collect(Collectors.toList()));

            if (answers.get(choice).equals(puzzle.getAnswer())) {
                System.out.println("The " + Codes.Chest.withColor("chest") + " unlocks with a loud click");
                result = reward;
            } else {
                System.out.println("The chest makes a grunt and refuses to open");
            }
        } else {
            System.out.println("The chest does nothing");
        }
        broken = true;
        return result;
    }

    private int getInput(List<List<String>> options) {
        boolean doLoop = true;
        int choice = -1;
        while (doLoop){
            options.forEach(System.out::println); // print out the options
            String input = ConsoleManager.scanner().nextLine().strip(); // get the choice from console

            // map each item in options to be boolean, true if input is inside sublist
            choice = options.stream().map(e -> e.contains(input)).collect(Collectors.toList()).indexOf(true);

            if (choice != -1) {
                doLoop = false;
            } else {
                System.out.println("The chest doesn't understand that option");
            }
        }

        return choice;
    }

    @Override
    public String toString() {
        return (broken) ? "Broken Chest" : "Chest";
    }
}
