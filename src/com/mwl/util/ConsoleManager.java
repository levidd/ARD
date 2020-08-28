package com.mwl.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsoleManager {
     private String input;
     private static final Scanner scanner = new Scanner(System.in);

     public ConsoleManager(){}

     public static void check(String userInput){
       /*check whether user input matches the target commands
       * if so, game continues
       * otherwise, a warning pops up and the question repeats
       * */
     }

     public static void gameIntro() {
          System.out.println("Welcome to ARD, the game where you get Another Random Destiny every time you play!");
     }

     public static void gameExplanation() {
          MenuTrieNode base = new MenuTrieNode("Help Menu", "You asked for help, what can I help you with?");
          base.addChild(new MenuTrieNode("Story Details", "This is a text based game, intro to what this help" +
                  " portion will be about", base)); // child 0
          base.addChild(new MenuTrieNode("Game controls", "To play the game, you type commands into the " +
                  "console. There are a plethora of commands to choose from, here are a few options. To call any of " +
                  "these, it is <command> <option>", base)); // child 1
          base.getChild(0).addChild(new MenuTrieNode("How do I win", "You can win by finding the boss and" +
                  " defeating him.", base.getChild(0))); // 0-0
          base.getChild(0).getChild(0).addChild(new MenuTrieNode("How to find the boss", "Boss Bezos spawns " +
                  "somewhere on the map, you need to search and find him!", base.getChild(0).getChild(0))); // child 0-0-0
          base.getChild(0).getChild(0).addChild(new MenuTrieNode("How to kill the boss", "You need special" +
                  " items to defeat Bezos. They are sword, shield, potion, ...", base.getChild(0).getChild(0))); // child 0-0-1

          boolean navigateMenu = true;
          MenuTrieNode curr = base;
          while (navigateMenu) {
               System.out.println("<" + curr.getTitle() + ">");
               System.out.println(curr.getDescription());
               MenuTrieNode finalCurr = curr;
               int choice = getInput(IntStream.range(0, curr.getChildren().size())
                       .mapToObj(e -> List.of("" + e, finalCurr.getChild(e).getTitle()))
                       .collect(Collectors.toList())); // -> {{"0", "Story details"}, {"1", "Game controls"}} etc.

               if (choice == -1) {
                    navigateMenu = false;
               } else if (choice == -2) {
                    curr = curr.getParent();
               } else {
                    curr = curr.getChild(choice);
               }
          }
     }

     private static int getInput(List<List<String>> options) {
          options.add(List.of("b", "back"));
          options.add(List.of("q", "quit"));
          boolean doLoop = true;
          int choice = -1;
          while (doLoop){
               options.forEach(System.out::println); // print out the options
               String input = scanner.nextLine().strip(); // get the choice from console

               // map each item in options to be boolean, true if input is inside sublist
               choice = options.stream().map(e -> e.contains(input)).collect(Collectors.toList()).indexOf(true);

               if (choice != -1) {
                    doLoop = false;
               } else {
                    System.out.println("I didn't understand that option, please try again.");
               }

          }

          return (choice == options.size() - 1 ? -1 : choice == options.size() - 2 ? -2 : choice); // -> -1 or -2 or some number in list
     }

     public static void main(String[] args) {
//          MenuTrie menu = new MenuTrie();
          gameExplanation();
     }
}
