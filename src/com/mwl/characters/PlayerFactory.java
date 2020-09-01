package com.mwl.characters;

import com.mwl.environment.Item;
import com.mwl.environment.Room;

import java.util.List;

public class PlayerFactory {

    private PlayerFactory() {

    }

    public static Player createPlayer(String name, int life, Room currentRoom, List<Item> itemInventory, String playerOption) {
        Player player = null;
        switch (playerOption.toUpperCase().strip()) {
            case "A":
                player = new PlayerA(name, life, currentRoom, itemInventory);
            case "B":
                player = new PlayerB(name, life, currentRoom, itemInventory);
        }
        return player;
    }
}
