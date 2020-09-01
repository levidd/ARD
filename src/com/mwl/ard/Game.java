package com.mwl.ard;

import com.mwl.characters.Monster;
import com.mwl.characters.Player;
import com.mwl.characters.PlayerA;
import com.mwl.environment.Direction;
import com.mwl.environment.Item;
import com.mwl.environment.Room;
import com.mwl.environment.RoomMap;
import com.mwl.util.ConsoleManager;
import com.mwl.util.TextParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Game {
    Player player;
    RoomMap gameMap;


    public Game() {
        // default constructor
        gameMap = new RoomMap();
    }

    boolean play() {
        // tell player what room they are in or if monster is in front of them
        System.out.println(player.getCurrentRoom().getDescription());

        // ask what player wants to do
            // Text parser
        String[] command = TextParser.parser();
            // do that thing
        switch (command[0]) {
            case "move" -> gameMap.moveCharacter(player, Direction.valueOf(command[1]));
            case "look" -> player.getCurrentRoom().overview();
            case "flight" -> Flight(player, command[1]);
            case "fight" -> Fight(player, command[1]);
            case "pickup" -> player.pickUpItem(Item.valueOf(command[1]));
            case "drop" -> player.dropItem(Item.valueOf(command[1]));
            case "help" -> ConsoleManager.gameExplanation();
        }

        // if any monsters on map, have them move to new location if applicable

        return true;
    }

    public void newGame() {
        // new game logic
        ConsoleManager.gameIntro();
        player = ConsoleManager.choosePlayer(gameMap);

        boolean playGame = true;
        while (playGame) {
            // keep playing game until it passes back as false
            playGame = play();
        }

        // quit message
        System.out.println("Thanks for playing! Come play again");

    }

    void Flight(Player player, String option) {
            System.out.println("Flying " + option);
            // run method to do the action
    }

    void Fight (Player player, String option) {
            System.out.println("fighting " + option);
            // run method to do the action
    }

}
