package com.mwl.ard;

import com.mwl.characters.Monster;
import com.mwl.characters.Player;
import com.mwl.environment.Room;
import com.mwl.environment.RoomMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    Player player;
    RoomMap gameMap;
    List<Monster> monsters;

    public Game() {
        // default constructor
        player = new Player();
        gameMap = new RoomMap();
        monsters = new ArrayList<Monster>();

        player.setCurrentRoom(gameMap.getStart());

    }

    void round() {
        // do something here
    }

    public void newGame() {
        // new game logic
    }
}
