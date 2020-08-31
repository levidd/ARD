package com.mwl.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ConsoleManagerNew {
     private String input;
     private static final Scanner scanner = new Scanner(System.in);
     //private static MenuTrieNode node = new MenuTrieNode();

     public ConsoleManagerNew(){}

     public static void check(String userInput){
       /*check whether user input matches the target commands
       * if so, game continues
       * otherwise, a warning pops up and the question repeats
       * */
     }

     public static void gameIntro() {
          System.out.println("Welcome to ARD, the game where you get Another Random Destiny every time you play!");
     }

     private static int getInput(Map<List<String>, String> options) {
          options.put(List.of("b"), "back");
          options.put(List.of("q"), "quit");
          List<List<String>> choiceList = new ArrayList<>();
          for(List<String> list: options.keySet()){
               choiceList = List.of(list);
          }
          boolean doLoop = true;
          int choice = -1;
          while (doLoop){
               choiceList.forEach(System.out::println); // print out the options
               String input = scanner.nextLine().strip(); // get the choice from console

               // map each item in options to be boolean, true if input is inside sublist
               choice = choiceList.stream().map(e -> e.contains(input)).collect(Collectors.toList()).indexOf(true);

               if (choice != -1) {
                    doLoop = false;
               } else {
                    System.out.println("I didn't understand that option, please try again.");
               }
          }
          return (choice == choiceList.size() - 1 ? -1 : choice == choiceList.size() - 2 ? -2 : choice); // -> -1 or -2 or some number in list
     }




     public static void gameExplanation(List<String[]> temp) {
          System.out.println(HelpMenuParser("Menu").keySet());
          System.out.println(HelpMenuParser("Menu").values());

//          MenuTrieNode base = null;
//
//          for(int i=0;i<temp.size();i=i+2){
//               if(temp.get(i).length == 1){
//                    //root node
//                    base = new MenuTrieNode(temp.get(i)[0], temp.get(i+1)[1]);
//               } else if(temp.get(i).length == 2){
//                    //first level child node
//                    base.addChild(new MenuTrieNode(temp.get(i)[1], temp.get(i+1)[2],base)); // child 0
//               } else if (temp.get(i).length == 3){
//                    //second level child node
//                    base.getChild(0).addChild(new MenuTrieNode(temp.get(i)[2], temp.get(i+1)[3], base.getChild(0))); // 0-0
//               } else if (temp.get(i).length == 4){
//                    //third level child node
//                    base.getChild(0).getChild(0).addChild(new MenuTrieNode(temp.get(i)[3], temp.get(i+1)[4], base.getChild(0).getChild(0))); // child 0-0-0
//               }
//          }
//
//          boolean navigateMenu = true;
//          MenuTrieNode curr = base;
//          while (navigateMenu) {
//               System.out.println("<" + curr.getTitle() + ">");
//               System.out.println(curr.getDescription());
//               MenuTrieNode finalCurr = curr;
//               int choice = getInput(IntStream.range(0, curr.getChildren().size())
//                       .mapToObj(e -> List.of("" + e, finalCurr.getChild(e).getTitle()))
//                       .collect(Collectors.toList())); // -> {{"0", "Story details"}, {"1", "Game controls"}} etc.
//
//               if (choice == -1) {
//                    navigateMenu = false;
//               } else if (choice == -2) {
//                    curr = curr.getParent();
//               } else {
//                    curr = curr.getChild(choice);
//               }
//          }
     }

     public static Map<List<String>, String> HelpMenuParser(String tagName){
          DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
          Map<List<String>, String> options = new HashMap<>();
          try {
               DocumentBuilder builder = factory.newDocumentBuilder();
               Document doc = builder.parse("resources/menu/help_menu.xml");
               NodeList titleList = doc.getElementsByTagName(tagName);
               for(int i=0;i<titleList.getLength();i++){
                    Node t = titleList.item(i);
                    if(t.getNodeType() == Node.ELEMENT_NODE) {
                         Element e = (Element) t;
                         options.put(List.of(Integer.toString(i), e.getElementsByTagName("title").item(0).getTextContent()),
                                 e.getElementsByTagName("description").item(0).getTextContent());
                    }
               }
          } catch (ParserConfigurationException e) {
               e.printStackTrace();
          } catch (SAXException e) {
               e.printStackTrace();
          } catch (IOException e) {
               e.printStackTrace();
          }
          //System.out.println(options);
          return options;
     }

     public static void main(String[] args) throws Exception {
          //MenuTrieNode menu = new MenuTrieNode();
          DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
          try {
               DocumentBuilder builder = factory.newDocumentBuilder();
               Document doc = builder.parse("resources/menu/help_menu.xml");
               NodeList titleList = doc.getElementsByTagName("third_level_child");
               for(int i=0;i<titleList.getLength();i++){
                    Node t = titleList.item(i);
                    if(t.getNodeType() == Node.ELEMENT_NODE) {
                         Element e = (Element) t;
                   //      System.out.println(e.getElementsByTagName("title").item(0).getTextContent());
                   //      System.out.println(e.getElementsByTagName("description").item(0).getTextContent());
                    }
               }
          } catch (ParserConfigurationException e) {
               e.printStackTrace();
          } catch (SAXException e) {
               e.printStackTrace();
          } catch (IOException e) {
               e.printStackTrace();
          }


          System.out.println(HelpMenuParser("Menu").keySet().toString());
          System.out.println(HelpMenuParser("Menu").values().toString());
          System.out.println(getInput(HelpMenuParser("first_level_child")));
     }




}
