package com.mwl.util;

import com.mwl.util.commands.*;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class ConsoleManagerTest {

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    NodeList menuNodeList = null;

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @Test
    public void testRecursiveHelper() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("testResources/menu/test.xml");
        menuNodeList = doc.getElementsByTagName("menu");
        Node menuNode = menuNodeList.item(0);

        MenuTrieNode menu = new MenuTrieNode("Select an option", "See the descriptions inside each option.");
        MenuTrieNode option1 = new MenuTrieNode("Option 1", "This is Option 1.");
        MenuTrieNode option11 = new MenuTrieNode("Option 11", "This is Option 11.");
        MenuTrieNode option111 = new MenuTrieNode("Option 111", "This is Option 111.");
        MenuTrieNode option112 = new MenuTrieNode("Option 112", "This is Option 112.");
        option11.addChild(option111);
        option111.setParent(option11);
        option11.addChild(option112);
        option112.setParent(option11);
        option1.addChild(option11);
        option11.setParent(option1);
        MenuTrieNode option12 = new MenuTrieNode("Option 12", "This is Option 12.");
        MenuTrieNode option13 = new MenuTrieNode("Option 13", "This is Option 13.");
        option1.addChild(option12);
        option12.setParent(option1);
        option1.addChild(option13);
        option13.setParent(option1);
        MenuTrieNode option2 = new MenuTrieNode("Option 2", "This is Option 2.");
        MenuTrieNode option21 = new MenuTrieNode("Option 21", "This is Option 21.");
        MenuTrieNode option22 = new MenuTrieNode("Option 22", "This is Option 22.");
        MenuTrieNode option23 = new MenuTrieNode("Option 23", "This is Option 23.");
        MenuTrieNode option24 = new MenuTrieNode("Option 24", "This is Option 24.");
        option2.addChild(option21);
        option21.setParent(option2);
        option2.addChild(option22);
        option22.setParent(option2);
        option2.addChild(option23);
        option23.setParent(option2);
        option2.addChild(option24);
        option24.setParent(option2);
        menu.addChild(option1);
        option1.setParent(menu);
        menu.addChild(option2);
        option2.setParent(menu);

        //    assertTrue(menu.equals(ConsoleManager.recursiveHelper(menuNode)));

        assertEquals(menu, ConsoleManager.recursiveHelper(menuNode));
        System.out.println(menuNode.getFirstChild().getNextSibling().getNodeName());
    }

    @Test
    public void testRead_xml() throws ParserConfigurationException, IOException, SAXException {

        MenuTrieNode menu = new MenuTrieNode("Help Menu", "You asked for help, what item do you want to learn about?");
        MenuTrieNode story = new MenuTrieNode("Story Details", "This is a text based game, intro to what this help portion will be about.");
        MenuTrieNode winning = new MenuTrieNode("How do I win", "You can win by finding the boss and defeating him.");
        MenuTrieNode findBoss = new MenuTrieNode("How to find the boss", "Boss Bezos spawns somewhere on the map, you need to search and find him!");
        MenuTrieNode killBoss = new MenuTrieNode("How to kill the boss", "You need special items to defeat Bezos. They are sword, shield, potion, ...");
        winning.addChild(findBoss);
        findBoss.setParent(winning);
        winning.addChild(killBoss);
        killBoss.setParent(winning);
        story.addChild(winning);
        winning.setParent(story);

        MenuTrieNode rooms = new MenuTrieNode("What are rooms", "Rooms are the places where player might see items or/and monsters. Rooms are created instantly as player moves in any direction.");
        MenuTrieNode survival = new MenuTrieNode("How do I survive", "You kill the monsters and keep your life value above 0.");
        MenuTrieNode items = new MenuTrieNode("What are items", "Items are randomly generated things player can pickup or drop off in each room. There are certain upgraded items (magical stones) that you can only pickup after you have adventures more than 5 rooms.");
        story.addChild(rooms);
        rooms.setParent(story);
        story.addChild(items);
        items.setParent(story);
        story.addChild(survival);
        survival.setParent(story);



        MenuTrieNode gamePlay = new MenuTrieNode("Game Controls", "To play the game, you type commands into the console. There are a plethora of commands to choose from, here are a few options. To call any of these, it is [command] [option]");
        MenuTrieNode move = new MenuTrieNode("move", "to move around the game you say move [direction]. Directions are north, south, west, east.");
        MenuTrieNode pickup = new MenuTrieNode("pickup", "to pick up an item you say pickup [item]. If the item is in the room and can be picked up, it is added to your inventory and removed from the room.");
        MenuTrieNode drop = new MenuTrieNode("drop", "to drop an item from your inventory you say drop [item] and if the item can be dropped, it will be removed from your inventory and into the room");
        MenuTrieNode attack = new MenuTrieNode("fight", "say fight [monster] for each round of fighting. The order of player fighting monster and monster attacking player is randomized for each round.");
        gamePlay.addChild(move);
        move.setParent(gamePlay);
        gamePlay.addChild(pickup);
        pickup.setParent(gamePlay);
        gamePlay.addChild(drop);
        drop.setParent(gamePlay);
        gamePlay.addChild(attack);
        attack.setParent(gamePlay);
        menu.addChild(story);
        story.setParent(menu);
        menu.addChild(gamePlay);
        gamePlay.setParent(menu);

        menu.setParent(menu);

        MenuTrieNode fileMenu = ConsoleManager.read_xml("testResources/menu/help_menu.xml");
        fileMenu.setParent(fileMenu);

        assertEquals(menu.getTitle(), fileMenu.getTitle());
        assertEquals(menu.getDescription(), fileMenu.getDescription());
        assertEquals(menu.hashCode(), fileMenu.hashCode());

        assertEquals(menu, fileMenu);

    }

    @Test
    public void scanInputAcceptanceTestMoveNorthAllCases() {
        Map<String, Commands> commands = new HashMap<>();
        commands.put("pickup", new Pickup());
        commands.put("drop", new Drop());
        commands.put("move", new Move());
        commands.put("flight", new Flight());
        commands.put("look", new Look());
        commands.put("fight", new Fight());
        commands.put("help", new Help());
        List<String> lineList = new ArrayList<>();
        try {
            File myFile = new File("testResources/moving/inputMoveNorth.txt");
            Scanner scanner = new Scanner(myFile);
            while (scanner.hasNextLine()) {
                lineList.add(scanner.nextLine());
            }
        } catch (IOException ioe) {
            System.out.println("IOException");
        }

        for (int i = 0; i < lineList.size(); i++) {
            String input = lineList.get(i);
            System.out.println(input);
//            InputStream in = new ByteArrayInputStream(input.getBytes());
//            System.setIn(in);
            String[] expected = {"move", "North"};
            String[] actual = ConsoleManager.scanInput(commands, input);
            assertEquals(expected, actual);
        }
    }

    @Test
    public void scanInputAcceptanceTestLookAroundAllCases() {
        Map<String, Commands> commands = new HashMap<>();
        commands.put("pickup", new Pickup());
        commands.put("drop", new Drop());
        commands.put("move", new Move());
        commands.put("flight", new Flight());
        commands.put("look", new Look());
        commands.put("fight", new Fight());
        commands.put("help", new Help());
        List<String> lineList = new ArrayList<>();
        try {
            File myFile = new File("testResources/moving/inputLookAround.txt");
            Scanner scanner = new Scanner(myFile);
            while (scanner.hasNextLine()) {
                lineList.add(scanner.nextLine());
            }
        } catch (IOException ioe) {
            System.out.println("IOException");
        }

        for (int i = 0; i < lineList.size(); i++) {
            String input = lineList.get(i);
            System.out.println(input);
//            InputStream in = new ByteArrayInputStream(input.getBytes());
//            System.setIn(in);
            String[] expected = {"look", "Around"};
            String[] actual = ConsoleManager.scanInput(commands, input);
            assertEquals(expected, actual);
        }
    }


    @Test
    public void scanInputAcceptanceTestMoveWestWithSpaceBeforeAndAfter() {
        Map<String, Commands> commands = new HashMap<>();
        commands.put("pickup", new Pickup());
        commands.put("drop", new Drop());
        commands.put("move", new Move());
        commands.put("flight", new Flight());
        commands.put("look", new Look());
        commands.put("fight", new Fight());
        commands.put("help", new Help());
        List<String> lineList = new ArrayList<>();
        try {
            File myFile = new File("testResources/moving/inputMoveWest.txt");
            Scanner scanner = new Scanner(myFile);
            while (scanner.hasNextLine()) {
                lineList.add(scanner.nextLine());
            }
        } catch (IOException ioe) {
            System.out.println("IOException");
        }

        for (int i = 0; i < lineList.size(); i++) {
            String input = lineList.get(i);
            System.out.println(input);
//            InputStream in = new ByteArrayInputStream(input.getBytes());
//            System.setIn(in);
            String[] expected = {"move", "West"};
            String[] actual = ConsoleManager.scanInput(commands, input);
            assertEquals(expected, actual);
        }
    }
}