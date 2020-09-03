package com.mwl.characters;

import com.mwl.environment.Item;
import com.mwl.environment.Room;

import java.util.Collection;
import java.util.List;

public class Wolverine extends Player{
    Monster monster;

    public Wolverine(String name, int life, Room currentRoom, List<Item> itemsInventory){
        super(name, life, currentRoom, itemsInventory);
    }

    @Override
    public void attack() {
       int lifeValue = monster.getLife();
       lifeValue -= 10;
       setLife(lifeValue);
    }

    @Override //health boost
    public void useSpecialPower() {
        int lifeValue = getLife();
        lifeValue += 50;
        setLife(lifeValue);
    }

}
