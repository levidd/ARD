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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HelpMenuParserNew {
     private String input;
     private static final Scanner scanner = new Scanner(System.in);

     public HelpMenuParserNew(){}

     public static void gameIntro() {
          System.out.println("Welcome to ARD, the game where you get Another Random Destiny every time you play!");
     }

     public static Node helpMenuParser() throws Exception {
          DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
          NodeList menuNodeList = null;
          try {
               DocumentBuilder builder = factory.newDocumentBuilder();
               Document doc = builder.parse("resources/menu/help_menu.xml");
               menuNodeList = doc.getElementsByTagName("menu");

//               Node n0 = menuNodeList.item(0);
//               Element e0 = (Element) n0;
//               System.out.println(e0.getAttribute("title"));
//               System.out.println(e0.getAttribute("description"));
//
//               Node n01 = n0.getFirstChild().getNextSibling();
//               Element e01 = (Element) n01;
//               System.out.println(e01.getAttribute("title"));
//               System.out.println(e01.getAttribute("description"));
//
//               Node n011 = n01.getFirstChild().getNextSibling();
//               Element e011 = (Element) n011;
//               System.out.println(e011.getAttribute("title"));
//               System.out.println(e011.getAttribute("description"));
//
//               Node n0111 = n011.getFirstChild().getNextSibling();
//               Element e0111 = (Element) n0111;
//               System.out.println(e0111.getAttribute("title"));
//               System.out.println(e0111.getAttribute("description"));
//
//               Node n0112 = n0111.getNextSibling().getNextSibling();
//               Element e0112 = (Element) n0112;
//               System.out.println(e0112.getAttribute("title"));
//               System.out.println(e0112.getAttribute("description"));
//
//               Node n012 = n011.getNextSibling().getNextSibling();
//               Element e012 = (Element) n012;
//               System.out.println(e012.getAttribute("title"));
//               System.out.println(e012.getAttribute("description"));
//
//               Node n013 = n012.getNextSibling().getNextSibling();
//               Element e013 = (Element) n013;
//               System.out.println(e013.getAttribute("title"));
//               System.out.println(e013.getAttribute("description"));
//
//               Node n02 = n01.getNextSibling().getNextSibling();
//               Element e02 = (Element) n02;
//               System.out.println(e02.getAttribute("title"));
//               System.out.println(e02.getAttribute("description"));
//
//               Node n021 = n02.getFirstChild().getNextSibling();
//               Element e021 = (Element) n021;
//               System.out.println(e021.getAttribute("title"));
//               System.out.println(e021.getAttribute("description"));
//
//               Node n022 = n021.getNextSibling().getNextSibling();
//               Element e022 = (Element) n022;
//               System.out.println(e022.getAttribute("title"));
//               System.out.println(e022.getAttribute("description"));
//
//               Node n023 = n022.getNextSibling().getNextSibling();
//               Element e023 = (Element) n023;
//               System.out.println(e023.getAttribute("title"));
//               System.out.println(e023.getAttribute("description"));
//
//               Node n024 = n023.getNextSibling().getNextSibling();
//               Element e024 = (Element) n024;
//               System.out.println(e024.getAttribute("title"));
//               System.out.println(e024.getAttribute("description"));

          } catch (ParserConfigurationException e) {
               e.printStackTrace();
          } catch (SAXException e) {
               e.printStackTrace();
          } catch (IOException e) {
               e.printStackTrace();
          }
          Node menuNode = menuNodeList.item(0);
          return menuNode;
     }

     public static void helpMenuParser2(Node node) {
          int counter = 0;

          if(node.hasChildNodes()){
               Node n = node.getFirstChild().getNextSibling();
               Element e = (Element) n;
               System.out.println(e.getAttribute("title"));
               System.out.println(e.getAttribute("description"));
//                    Node p = node.getParentNode();
//                    helpMenuParser2(p);
               helpMenuParser2(n);
                if(!n.hasChildNodes()){
                     Node p = node.getParentNode();
                     helpMenuParser2(p);
                }
               }
               else{
//                    Node n1 = node.getNextSibling().getNextSibling();
//                    Element e1 = (Element) n1;
//                    System.out.println(e1.getAttribute("title"));
//                    System.out.println(e1.getAttribute("description"));
               System.out.println("Done");
               }

     }

     public static int getInput(List<List<String>> options) {
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




     public static void gameExplanation(NodeList menuNodeList) {

          boolean navigateMenu = true;
          Node n0 = menuNodeList.item(0);





//          while (navigateMenu) {
//               n0.getFirstChild().getNextSibling()
//               System.out.println();
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



     public static void main(String[] args) throws Exception {
//          DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//          NodeList menuNodeList = null;
//          try {
//               DocumentBuilder builder = factory.newDocumentBuilder();
//               Document doc = builder.parse("resources/menu/help_menu.xml");
//               menuNodeList = doc.getElementsByTagName("menu");

//               Node n0 = menuNodeList.item(0);
//               Element e0 = (Element) n0;
//               System.out.println(e0.getAttribute("title"));
//               System.out.println(e0.getAttribute("description"));
//
//               Node n01 = n0.getFirstChild().getNextSibling();
//               Element e01 = (Element) n01;
//               System.out.println(e01.getAttribute("title"));
//               System.out.println(e01.getAttribute("description"));
//
//               Node n011 = n01.getFirstChild().getNextSibling();
//               Element e011 = (Element) n011;
//               System.out.println(e011.getAttribute("title"));
//               System.out.println(e011.getAttribute("description"));
//
//               Node n0111 = n011.getFirstChild().getNextSibling();
//               Element e0111 = (Element) n0111;
//               System.out.println(e0111.getAttribute("title"));
//               System.out.println(e0111.getAttribute("description"));
//
//               Node n0112 = n0111.getNextSibling().getNextSibling();
//               Element e0112 = (Element) n0112;
//               System.out.println(e0112.getAttribute("title"));
//               System.out.println(e0112.getAttribute("description"));
//
//               Node n012 = n011.getNextSibling().getNextSibling();
//               Element e012 = (Element) n012;
//               System.out.println(e012.getAttribute("title"));
//               System.out.println(e012.getAttribute("description"));
//
//               Node n013 = n012.getNextSibling().getNextSibling();
//               Element e013 = (Element) n013;
//               System.out.println(e013.getAttribute("title"));
//               System.out.println(e013.getAttribute("description"));
//
//               Node n02 = n01.getNextSibling().getNextSibling();
//               Element e02 = (Element) n02;
//               System.out.println(e02.getAttribute("title"));
//               System.out.println(e02.getAttribute("description"));
//
//               Node n021 = n02.getFirstChild().getNextSibling();
//               Element e021 = (Element) n021;
//               System.out.println(e021.getAttribute("title"));
//               System.out.println(e021.getAttribute("description"));
//
//               Node n022 = n021.getNextSibling().getNextSibling();
//               Element e022 = (Element) n022;
//               System.out.println(e022.getAttribute("title"));
//               System.out.println(e022.getAttribute("description"));
//
//               Node n023 = n022.getNextSibling().getNextSibling();
//               Element e023 = (Element) n023;
//               System.out.println(e023.getAttribute("title"));
//               System.out.println(e023.getAttribute("description"));
//
//               Node n024 = n023.getNextSibling().getNextSibling();
//               Element e024 = (Element) n024;
//               System.out.println(e024.getAttribute("title"));
//               System.out.println(e024.getAttribute("description"));

//          } catch (ParserConfigurationException e) {
//               e.printStackTrace();
//          } catch (SAXException e) {
//               e.printStackTrace();
//          } catch (IOException e) {
//               e.printStackTrace();
//          }







             //  System.out.println(menuElement.getElementsByTagName("title").item(0).getTextContent());
             //  System.out.println(menuElement.getElementsByTagName("description").item(0).getTextContent());

//               int len = menuNode.item(0).getChildNodes().getLength();
//               System.out.println(menuNode.item(0).getFirstChild().getNodeName()); //#text
//               System.out.println(((Element) menuNode.item(0)).getTagName()); //menu
//               System.out.println(menuNode.item(0).getNodeValue()); //null
//               System.out.println(menuElement.getTagName()); //menu
//               System.out.println(menuElement.getFirstChild().toString());
//               System.out.println(menuElement.getTextContent()); //whole text menu
//               System.out.println(menuElement.getTagName()); //menu
//               System.out.println(len); //9 or 5




//               for(int i=0;i<len;i++) {
//                    Node n = menuNode.item(0).getChildNodes().item(i);
//                    if (n.getNodeType() == Node.ELEMENT_NODE) {
//                         Element e = (Element) n;
//                       //  System.out.println(e.getTagName());
//                       //  System.out.println(n.getNodeName());
//
//                    } else if (n.getNodeType() == Node.TEXT_NODE) {
//
//                        // System.out.println(n.getNodeName());
//                    }
//               }
//
//               NodeList firstChildrenNodes = doc.getElementsByTagName("first_level_child");

//          for(int i=0;i<firstChildrenNodes.getLength();i++){
//                  Node n = firstChildrenNodes.item(i);
//                  if(n.getNodeType() == Node.ELEMENT_NODE){
//                       Element e = (Element) n;
//                       options.add(List.of(Integer.toString(i), e.getElementsByTagName("title").item(0).getTextContent()));
//                  }
//               }
              // options.forEach(System.out::println);
//               String input = scanner.next().strip();
//               if(options.get(0).contains(input)){
//                    Node n = firstChildrenNodes.item(0).getChildNodes().item(0);
//                    Element e = (Element) n;
//                    options.add(List.of("0", e.getElementsByTagName("title").item(0).getTextContent()));
//               } else if(options.get(1).contains(input)){
//                    System.out.println(firstChildrenNodes.item(1).getChildNodes());
//
//               }


//               while(true){
//                    int choice = getInput(IntStream.range(0, curr.getChildren().size())
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
//
//
//
//
//
//          } catch (ParserConfigurationException e) {
//               e.printStackTrace();
//          } catch (SAXException e) {
//               e.printStackTrace();
//          } catch (IOException e) {
//               e.printStackTrace();
//          }

  helpMenuParser2(helpMenuParser());

     }




}
