package com.mwl.characters;

import com.mwl.environment.Item;
import com.mwl.environment.Room;

import java.util.Collection;

public class PlayerA extends Player{

    public PlayerA(String name, int life, Room currentRoom, Collection<Item> itemsInventory){
        super(name, life, currentRoom, itemsInventory);
    }

    @Override
    public void attack() {

    }

    public void health_boost(){

    }


}
