package com.mwl.characters;

import com.mwl.environment.Item;
import com.mwl.environment.Room;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.mwl.combat.combatEngine.MonsterFightsPlayer;
import static com.mwl.combat.combatEngine.fightRoomMonster;

public class Ironman extends Player{
    Monster monster;

    public Ironman(String name, int life, Room currentRoom, List<Item> itemsInventory, int level){
        super(name, life, currentRoom, itemsInventory, level);
    }

    @Override
    public void attack() {
        int rand = ThreadLocalRandom.current().nextInt(2);
        switch(rand) {
            case 0:
                fightRoomMonster(this);
                if (getCurrentRoom().getMonsters().size() > 0) {
                    MonsterFightsPlayer(getCurrentRoom().getMonsters().get(0), this);
                }
                break;
            case 1:
                if (getCurrentRoom().getMonsters().size() > 0) {
                    MonsterFightsPlayer(getCurrentRoom().getMonsters().get(0), this);
                }
                fightRoomMonster(this);
                break;
        }
    }

    @Override //generate more items
    public void useSpecialPower() {
      if(this.getItemsInventory().contains("Power_stone")) {
          List<Item> inventory = getItemsInventory();
          int len = inventory.size();
          if (len >= 1) {
              int random = (int) (Math.random() * len + 1);
              inventory.add(inventory.get(random));
              inventory.remove(Item.valueOf("Power_stone"));
              System.out.println(this.getName() + " has power stone in inventory and just used special power to generate one more item!");
          } else {
              System.out.println(this.getName() + " can't use special power with empty items inventory!");
          }
      }
    }
}
