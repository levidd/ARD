package com.mwl.util;

import com.mwl.characters.Player;
import com.mwl.characters.PlayerFactory;
import com.mwl.environment.RoomMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsoleManager {
     private String input;
     RoomMap gameMap;
     private static final Scanner scanner = new Scanner(System.in);
     private static MenuTrieNode menu = read_xml();

     public ConsoleManager(){}

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

     public static void gameExplanation() {

          boolean navigateMenu = true;
          MenuTrieNode curr = menu;
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

     public static Player choosePlayer(RoomMap map) {
         System.out.println("Name your player: ");
         String name = scanner.nextLine();

         System.out.println("[Player type A has special ability of health boost];\n " +
                 "[Player type B has special ability to randomly generate one item that's already in inventory].");
         System.out.println("Choose Player type A or Player type B: ");
         String playerChoice = scanner.nextLine();

         return  PlayerFactory.createPlayer(name, 100, map.getStart(), new ArrayList<>(), playerChoice);
     }

      static MenuTrieNode recursiveHelper(Node current) {
          List<MenuTrieNode> result = new ArrayList<>();
          NodeList children = current.getChildNodes();

          for (int i = 0; i < children.getLength(); i++) {
               if (children.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element temp = (Element) children.item(i);
                    if (temp.getParentNode().equals(current)) {
                         result.add(recursiveHelper(children.item(i)));
                    }
               }
          }

          Element temp = (Element) current;
          MenuTrieNode returning = new MenuTrieNode(temp.getAttribute("title"), temp.getAttribute("description"));
          result.forEach(menuTrieNode -> {
               returning.addChild(menuTrieNode);
               menuTrieNode.setParent(returning);
          });

          return returning;
     }

      static MenuTrieNode read_xml() {
          DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
          NodeList menuNodeList = null;

          try {
               DocumentBuilder builder = factory.newDocumentBuilder();
               Document doc = builder.parse("resources/menu/help_menu.xml");
               menuNodeList = doc.getElementsByTagName("menu");

               MenuTrieNode menu = recursiveHelper(menuNodeList.item(0));
               menu.setParent(menu);
               return menu;
          } catch (ParserConfigurationException | SAXException | IOException  e) {
               e.printStackTrace();
          }

          MenuTrieNode failure = new MenuTrieNode("Help Menu", "There was an error reading the help menu," +
                  " please restart the game to try again.");
          failure.setParent(failure);

          return failure;
     }


}
