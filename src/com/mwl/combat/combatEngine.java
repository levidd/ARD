package com.mwl.combat;

import com.mwl.ard.Game;
import com.mwl.characters.Monster;
import com.mwl.characters.Normal;
import com.mwl.characters.Player;
import com.mwl.environment.Room;

import java.util.concurrent.ThreadLocalRandom;

import static com.mwl.util.ExitGame.exit;

public class combatEngine {
    public static void fightOrFlight(Room currentRoom){
        if(currentRoom.getMonsters().size()>0){
            System.out.println("You want to fight?");
        }
    }

    public static void fightRoomMonster(Player player, Room currentRoom){
        if(checkForMonsterInRoom(currentRoom)){
            int lifeValue = currentRoom.getMonsters().get(0).getLife();
            lifeValue -= 20;
            currentRoom.getMonsters().get(0).setLife(lifeValue);
            if(!checkIfMonsterAlive(currentRoom)){
                removeDefeatedMonsterFromRoom(currentRoom);
                System.out.println("You killed the monster");
            }else {
                System.out.println(player.getName() + " has attacked monster and monster lost life value of: 20");
                System.out.println("Monster current life value is: " + lifeValue);
            }
        }
    }

    public static void MonsterFightsPlayer(Monster monster, Player player){
        int quantity = ThreadLocalRandom.current().nextInt(2, 3);

        while (quantity > 0){
            quantity--;
            if(checkIfMonsterAlive(player.getCurrentRoom())){
                int lifeValue = player.getLife();
                lifeValue -= 20;
                player.setLife(lifeValue);
                if(!checkIfPlayerAlive(player)){
                    ifPlayerDeath(monster);
                }else {
                    System.out.println(monster.getName() + " has attacked player and player lost life value of: 20");
                    System.out.println("Player current life value is: " + lifeValue);
                }
            }
        }
    }

    private static boolean checkForMonsterInRoom(Room currentRoom){
        boolean monsterPresent = true;
        if(currentRoom.getMonsters().size() == 0){
            System.out.println("Just kidding! No monster in this room");
            monsterPresent = false;
        }
        return monsterPresent;
    }

    private static void removeDefeatedMonsterFromRoom(Room currentRoom){
        currentRoom.defeatMonster(currentRoom.getMonsters().get(0));
    }

    private static boolean checkIfMonsterAlive(Room currentRoom){
        boolean alive = false;
        if(currentRoom.getMonsters().get(0).getLife() > 0){
            alive = true;
        }
        return alive;
    }

    private static boolean checkIfPlayerAlive(Player player){
        boolean alive = false;
        if(player.getLife() > 0){
            alive = true;
        }
        return alive;
    }

    private static void ifPlayerDeath(Monster monster){
        System.out.println("Sorry "+ monster.getName()+" killed you.");
        exit("exit");
    }
}