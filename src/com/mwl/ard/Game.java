package com.mwl.ard;

import com.mwl.characters.Monster;
import com.mwl.characters.Player;
import com.mwl.environment.Room;
import com.mwl.util.ConsoleManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    Player player;
    Map<Integer, Room> gameMap;
    List<Monster> monsters;

    public Game() {
        // default constructor
        player = new Player();
        gameMap = new HashMap<Integer, Room>();
        monsters = new ArrayList<Monster>();

        gameMap.put(0, new Room());
    }

    void round() {
        // do something here
    }

    public void newGame() {
        // new game logic
        ConsoleManager.gameIntro();
        ConsoleManager.gameExplanation();
    }
}
