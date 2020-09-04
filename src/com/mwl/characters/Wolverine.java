package com.mwl.characters;

import com.mwl.environment.Item;
import com.mwl.environment.Room;

import java.util.Collection;
import java.util.List;

import static com.mwl.combat.combatEngine.MonsterFightsPlayer;
import static com.mwl.combat.combatEngine.fightRoomMonster;

public class Wolverine extends Player{
    Monster monster;

    public Wolverine(String name, int life, Room currentRoom, List<Item> itemsInventory){
        super(name, life, currentRoom, itemsInventory);
    }

    @Override
    public void attack() {
        fightRoomMonster(this,getCurrentRoom());
        if(getCurrentRoom().getMonsters().size() > 0){
            MonsterFightsPlayer(getCurrentRoom().getMonsters().get(0), this);
        }
    }

    @Override //health boost
    public void useSpecialPower() {
        int lifeValue = getLife();
        lifeValue += 50;
        setLife(lifeValue);
    }

}
