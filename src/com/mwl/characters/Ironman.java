package com.mwl.characters;

import com.mwl.environment.Item;
import com.mwl.environment.Room;

import java.util.Collection;
import java.util.List;

public class Ironman extends Player{
    Monster monster;

    public Ironman(String name, int life, Room currentRoom, List<Item> itemsInventory){
        super(name, life, currentRoom, itemsInventory);
    }

    @Override
    public void attack() {
        int lifeValue = monster.getLife();
        lifeValue -= 30;
        monster.setLife(lifeValue);
        System.out.println("Iron Man has attacked monster and monster lost life value of: 30");
        System.out.println("Monster current life value is: " + lifeValue);
    }

    @Override //generate more items
    public void useSpecialPower() {
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
