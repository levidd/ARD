package com.mwl.characters;

import com.mwl.environment.Item;
import com.mwl.environment.Room;

import java.util.Collection;

public class PlayerB extends Player{
    public PlayerB(String name, int life, Room currentRoom, Collection<Item> itemsInventory){
        super(name, life, currentRoom, itemsInventory);
    }

    @Override
    public void attack() {

    }

    public void be_invisible(){

    }

}
