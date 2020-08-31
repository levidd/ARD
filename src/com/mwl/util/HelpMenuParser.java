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
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HelpMenuParser {
    private String input;
    private static final Scanner scanner = new Scanner(System.in);

    public HelpMenuParser(){}

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


    public static void main(String[] args) throws Exception {
          Scanner scanner = new Scanner(System.in);

          DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
          NodeList menuNodeList = null;

          try {
               DocumentBuilder builder = factory.newDocumentBuilder();
               Document doc = builder.parse("resources/menu/temp.xml");
               menuNodeList = doc.getElementsByTagName("node");

               MenuTrieNode menu = recursiveHelper(menuNodeList.item(0));
               menu.setParent(menu);



//               var x = doc.getChildNodes();
//               for(int i = 0; i < x.getLength(); i++) {
//                   System.out.println(x.item(i));
//               }

//              InputStream inputStream = HelpMenuParser.class.getClassLoader().getResourceAsStream("resources/menu/help_menu.xml");
//              DocumentBuilder builder1 = factory.newDocumentBuilder();
//              Document document = builder.parse(inputStream);
//
//              NodeList menuNodeList1 = document.getElementsByTagName("menu");
//              System.out.println(menuNodeList1.item(0).getNodeName());

               Node n0 = menuNodeList.item(0);

               NodeList temp = n0.getChildNodes();

               Element e0 = (Element) n0;
               System.out.println(e0.getAttribute("title"));
               System.out.println(e0.getAttribute("description"));

            //  System.out.println(n0.getChildNodes().getLength()); //5


               for(int i=1;i<n0.getChildNodes().getLength();i=i+2) {
                   Node n = n0.getChildNodes().item(i);
                   if (n.getNodeType() == Node.ELEMENT_NODE) {
                       Element e = (Element) n;
                       System.out.println(i + ": " + e.getAttribute("title"));
                   }
               }

               Node n01 = n0.getFirstChild().getNextSibling();
               Element e01 = (Element) n01;
//               System.out.println(e01.getAttribute("title"));
//               System.out.println(e01.getAttribute("description"));

               Node n02 = n01.getNextSibling().getNextSibling();
               Element e02 = (Element) n02;
//               System.out.println(e02.getAttribute("title"));
//               System.out.println(e02.getAttribute("description"));


               String input = scanner.nextLine().strip();
               if(e01.getAttribute("title").contains(input)){
                  Node n011 = n01.getFirstChild().getNextSibling();
                  Element e011 = (Element) n011;
                  System.out.println(e011.getAttribute("title"));

                  Node n012 = n011.getNextSibling().getNextSibling();
                  Element e012 = (Element) n012;
                  System.out.println(e012.getAttribute("title"));

                  Node n013 = n012.getNextSibling().getNextSibling();
                  Element e013 = (Element) n013;
                  System.out.println(e013.getAttribute("title"));

               } else if ((e02.getAttribute("title")).contains(input)){
                  Node n021 = n02.getFirstChild().getNextSibling();
                  Element e021 = (Element) n021;
                  System.out.println(e021.getAttribute("title"));

                  Node n022 = n021.getNextSibling().getNextSibling();
                  Element e022 = (Element) n022;
                  System.out.println(e022.getAttribute("title"));

                  Node n023 = n022.getNextSibling().getNextSibling();
                  Element e023 = (Element) n023;
                  System.out.println(e023.getAttribute("title"));

                  Node n024 = n023.getNextSibling().getNextSibling();
                  Element e024 = (Element) n024;
                  System.out.println(e024.getAttribute("title"));

               }
//

//              static Map<String,List<String>> parse(String xml) throws Exception
//              {
//                  Map<String,List<String>> map = new HashMap<String,List<String>>();
//
//                  SAXReader reader = new SAXReader();
//                  Document doc = reader.read(new StringReader(xml));
//
//                  for (Iterator i = doc.getRootElement().elements().iterator(); i.hasNext();)
//                  {
//                      Element element = (Element)i.next();
//
//                      //Maybe handle elements with only whitespace text content
//
//                      List<String> list = map.get(element.getName());
//                      if (list == null)
//                      {
//                          list = new ArrayList<String>();
//                          map.put(element.getName(), list);
//                      }
//                      list.add(element.getText());
//                  }
//
//                  return map;
//              }

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

//               System.out.println(e012.getAttribute("description"));
//

//               System.out.println(e013.getAttribute("description"));
//

//

//               System.out.println(e021.getAttribute("description"));
//

//               System.out.println(e022.getAttribute("description"));
//

//               System.out.println(e023.getAttribute("description"));
//

//               System.out.println(e024.getAttribute("description"));

          } catch (ParserConfigurationException e) {
               e.printStackTrace();
          } catch (SAXException e) {
               e.printStackTrace();
          } catch (IOException e) {
               e.printStackTrace();
          }

    }

    private static class SAXReader {
    }
}

