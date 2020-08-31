package com.mwl.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ConsoleManager {
     private String input;
     private static final Scanner scanner = new Scanner(System.in);
     //private static MenuTrieNode node = new MenuTrieNode();

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


//     public static void createNodes(List<String[]> list, int index) {
//          MenuTrieNode node = null;
//          if(list.get(index).length == 1) {
//            node = new MenuTrieNode(list.get(index)[list.get(index).length - 1], list.get(index + 1)[list.get(index).length]);
//          }
//          else if(list.get(index).length >1 ){
//            node.addChild();
//          }
//
//          for (int i = 0; i < list.size(); i = i + 2) {
//               node = new MenuTrieNode(list.get(i)[list.get(i).length-1], list.get(i + 1)[list.get(i).length], node);
//          }
//
//     }

     public static void gameExplanation(List<String[]> temp) {
          MenuTrieNode base = null;

          for(int i=0;i<temp.size();i=i+2){
               if(temp.get(i).length == 1){
                    //root node
                    base = new MenuTrieNode(temp.get(i)[0], temp.get(i+1)[1]);
               } else if(temp.get(i).length == 2){
                    //first level child node
                    base.addChild(new MenuTrieNode(temp.get(i)[1], temp.get(i+1)[2],base)); // child 0
               } else if (temp.get(i).length == 3){
                    //second level child node
                    base.getChild(0).addChild(new MenuTrieNode(temp.get(i)[2], temp.get(i+1)[3], base.getChild(0))); // 0-0
               } else if (temp.get(i).length == 4){
                    //third level child node
                    base.getChild(0).getChild(0).addChild(new MenuTrieNode(temp.get(i)[3], temp.get(i+1)[4], base.getChild(0).getChild(0))); // child 0-0-0
               }
          }
          /*
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
          */

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

     public static void main(String[] args) throws Exception {
          //MenuTrieNode menu = new MenuTrieNode();
          gameExplanation(read_text_file());
     }

     public static List<String[]> read_text_file() throws Exception {
          Path path = Paths.get("resources/menu/help_menu.txt");
          List<String> content = new ArrayList<>();
          List<String> titles = new ArrayList<>();
          List<String> descriptions = new ArrayList<>();
          Stream<String> result = null;
          try {
                result = Files.lines(path);
          } catch (IOException e){
               e.printStackTrace();
          }

          content = result.collect(Collectors.toList());
          List<String[]> temp = content.stream().map(e -> e.split("\t")).collect(Collectors.toList());
          //temp.stream().forEach(e -> System.out.println(Arrays.toString(e)));
         // temp.stream().forEach(e -> System.out.println(e.length));

        return temp;
     }


}
