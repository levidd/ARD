package com.mwl.ard;

import com.mwl.characters.Monster;
import com.mwl.characters.Player;
import com.mwl.environment.RoomMap;
import com.mwl.util.ConsoleManager;

import java.util.ArrayList;
import java.util.List;

public class Game {
    Player player;
    RoomMap gameMap;
    List<Monster> monsters;

    public Game() {
        // default constructor
       // player = new Player();
        gameMap = new RoomMap();
        monsters = new ArrayList<Monster>();

    }

    void round() {
        // do something here
    }

    public void newGame() {
        // new game logic
        ConsoleManager.gameIntro();
        //ConsoleManager.gameExplanation();
    }
}
