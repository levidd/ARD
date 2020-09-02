package com.mwl.characters;

import com.mwl.environment.Item;
import com.mwl.environment.Room;

import java.util.Collection;
import java.util.List;

public class PlayerA extends Player{

    public PlayerA(String name, int life, Room currentRoom, List<Item> itemsInventory){
        super(name, life, currentRoom, itemsInventory);
    }

    @Override
    public void attack() {

    }

    public void health_boost(){
       int lifeValue = getLife();
       lifeValue += 50;
       setLife(lifeValue);
    }


}
