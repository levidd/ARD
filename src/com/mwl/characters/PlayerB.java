package com.mwl.characters;

import com.mwl.environment.Item;
import com.mwl.environment.Room;

import java.util.Collection;
import java.util.List;

public class PlayerB extends Player{
    public PlayerB(String name, int life, Room currentRoom, List<Item> itemsInventory){
        super(name, life, currentRoom, itemsInventory);
    }

    @Override
    public void attack() {

    }

    public void more_power(){
        List<Item> inventory = getItemsInventory();
        int len = inventory.size();
       if(len >= 1) {
           int random = (int) (Math.random()*len + 1);
           inventory.add(inventory.get(random));
       } else{
           System.out.println("Can't use more_power with empty items inventory!");
       }
    }

}
