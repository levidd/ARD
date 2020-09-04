package com.mwl.characters;

import com.mwl.environment.Item;
import com.mwl.environment.Room;

import java.util.Collection;
import java.util.List;

import static com.mwl.combat.combatEngine.MonsterFightsPlayer;
import static com.mwl.combat.combatEngine.fightRoomMonster;

public class Ironman extends Player{
    Monster monster;

    public Ironman(String name, int life, Room currentRoom, List<Item> itemsInventory){
        super(name, life, currentRoom, itemsInventory);
    }

    @Override
    public void attack() {
        fightRoomMonster(this,getCurrentRoom());
        if(getCurrentRoom().getMonsters().size() > 0){
            MonsterFightsPlayer(getCurrentRoom().getMonsters().get(0), this);
        }
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
