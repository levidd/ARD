package com.mwl.util;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;

import static com.mwl.util.ConsoleManager.recursiveHelper;
import static org.junit.Assert.*;

public class ConsoleManagerTest {

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    NodeList menuNodeList = null;

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.Test
    public void testGameIntro() {
    }

    @org.junit.Test
    public void testGameExplanation() {
    }

    @Test(expected = Exception.class)
    public void testRecursiveHelper() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("test/com/mwl/util/help_menu.xml");
        menuNodeList = doc.getElementsByTagName("menu");
        Node menuNode = menuNodeList.item(0);

        System.out.println(menuNodeList.item(0).getNodeName());

        MenuTrieNode menu = new MenuTrieNode("Select an option", "See the descriptions inside each option.", null);
        menu.setParent(menu);
        MenuTrieNode option1 = new MenuTrieNode("Option 1", "This is Option 1.", menu);
        MenuTrieNode option11 = new MenuTrieNode("Option 11", "This is Option 11.", option1);
        MenuTrieNode option111 = new MenuTrieNode("Option 111", "This is Option 111", option11);

        assertEquals(option1, ConsoleManager.recursiveHelper(menuNode));

        MenuTrieNode actual = new MenuTrieNode("yes", "no", null);
        assertEquals(actual, ConsoleManager.recursiveHelper(menuNode));

        System.out.println(ConsoleManager.recursiveHelper(menuNode));
    }

    @Test
    public void testRead_xml() throws ParserConfigurationException, IOException, SAXException {

     MenuTrieNode menu = new MenuTrieNode("Help Menu", "You asked for help, what item do you want to learn about?", null);
     menu.setParent(menu);

     MenuTrieNode story = new MenuTrieNode("Story Details", "This is a text based game, intro to what this help portion will be about.", menu);
     MenuTrieNode winning = new MenuTrieNode("How do I win", "You can win by finding the boss and defeating him.", story);
     MenuTrieNode findBoss = new MenuTrieNode("How to find the boss", "Boss Bezos spawns somewhere on the map, you need to search and find him!", winning);
     MenuTrieNode killBoss = new MenuTrieNode("How to kill the boss", "You need special items to defeat Bezos. They are sword, shield, potion, ...", winning);
     MenuTrieNode rooms = new MenuTrieNode("What are rooms", "Rooms are good.", story);
     MenuTrieNode survival = new MenuTrieNode("How do I survive", "You kill the monsters.", story);
     MenuTrieNode gamePlay = new MenuTrieNode("Game Controls", "To play the game, you type commands into the console. There are a plethora of commands to choose from, here are a few options. To call any of these, it is [command] [option]", menu);
     MenuTrieNode move = new MenuTrieNode("move", "to move around the game you say move [direction]. Directions are north, south, west, east.", gamePlay);
     MenuTrieNode pickup = new MenuTrieNode("pickup", "to pick up an item you say pickup [item]. If the item is in the room and can be picked up, it is added to your inventory.", gamePlay);
     MenuTrieNode drop = new MenuTrieNode("drop", "to drop an item from your inventory you say drop [item] and if the item can be dropped, it will be removed from your inventory and into the room", gamePlay);
     MenuTrieNode attack = new MenuTrieNode("attack", "to attack an enemy, say attack [monster]. MORE DETAILS LATER WHEN WE FIGURE OUT HOW IT WORKS", gamePlay);

     menu.addChild(story);
     menu.addChild(gamePlay);
        System.out.println(menu.getTitle());
        System.out.println(ConsoleManager.read_xml().getTitle());
        System.out.println(menu.getDescription());
        System.out.println(ConsoleManager.read_xml().getDescription());
        System.out.println(menu.hashCode());
        System.out.println(ConsoleManager.read_xml().hashCode());
        System.out.println(menu.getParent());
        System.out.println(ConsoleManager.read_xml().getParent());
        System.out.println(menu.getChildren());
        System.out.println(ConsoleManager.read_xml().getChildren());


        assertTrue(ConsoleManager.read_xml().equals(menu));


    }
}