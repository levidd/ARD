package com.mwl.ard;

import com.mwl.characters.Monster;
import com.mwl.characters.Player;
import com.mwl.characters.PlayerA;
import com.mwl.environment.Item;
import com.mwl.environment.RoomMap;
import com.mwl.util.ConsoleManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Game {
    Player player;
    RoomMap gameMap;
    List<Monster> monsters;

    public Game() {
        // default constructor
        gameMap = new RoomMap();
        player = new PlayerA("player1", 100, gameMap.getStart(), new ArrayList<>());
        monsters = new ArrayList<Monster>();

    }

    boolean play() {
        // tell player what room they are in or if monster is in front of them

        // ask what player wants to do
            // Text parser
            // do that thing

        // if any monsters on map, have them move to new location if applicable

        return true;
    }

    public void newGame() {
        // new game logic
        ConsoleManager.gameIntro();
        //ConsoleManager.gameExplanation();

        boolean playGame = true;
        while (playGame) {
            // keep playing game until it passes back as false
            playGame = play();
        }

        // quit message
        System.out.println("Thanks for playing! Come play again");

    }
}
