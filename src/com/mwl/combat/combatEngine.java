package com.mwl.combat;

import com.mwl.ard.Game;
import com.mwl.characters.Monster;
import com.mwl.characters.Normal;
import com.mwl.characters.Player;
import com.mwl.environment.Room;

import java.util.concurrent.ThreadLocalRandom;

import static com.mwl.util.ExitGame.exit;

public class combatEngine {

    /**
     * static method a player can call to attack monsters
     * @param player current player
     * @param currentRoom room where the player is currently.
     */
    public static void fightRoomMonster(Player player, Room currentRoom){
        if(checkForMonsterInRoom(currentRoom)){
            int lifeValue = currentRoom.getMonsters().get(0).getLife();
            int damage = randomDamage();
            lifeValue -= damage;
            currentRoom.getMonsters().get(0).setLife(lifeValue);
            if(!checkIfMonsterAlive(currentRoom)){
                removeDefeatedMonsterFromRoom(currentRoom);
                System.out.println("You killed the monster");
            }else {
                System.out.println(player.getName() + " has attacked monster and monster lost life value of: " + damage);
                System.out.println("Monster current life value is: " + lifeValue);
            }
        }
    }

    /**
     *  static method that runs for the monster to fight back when a player attacks.
     * @param monster the monster in the current room.
     * @param player the player currently in the room with the monster.
     */
    public static void MonsterFightsPlayer(Monster monster, Player player){
        int quantity = ThreadLocalRandom.current().nextInt(2, 3);

        while (quantity > 0){
            quantity--;
            if(checkIfMonsterAlive(player.getCurrentRoom())){
                int lifeValue = player.getLife();
                int damage = randomDamage();
                lifeValue -= damage;
                player.setLife(lifeValue);
                if(!checkIfPlayerAlive(player)){
                    ifPlayerDeath(monster);
                }else {
                    System.out.println(monster.getName() + " has attacked player and player lost life value of: " + damage);
                    System.out.println("Player current life value is: " + lifeValue);
                }
            }
        }
    }

    /**
     * Helper method for fightRoomMonster to check if a monster is in the room
     * @param currentRoom Room that player is currently
     * @return return true if monster is in the current room, false otherwise
     */
    private static boolean checkForMonsterInRoom(Room currentRoom){
        boolean monsterPresent = true;
        if(currentRoom.getMonsters().size() == 0){
            System.out.println("Just kidding! No monster in this room");
            monsterPresent = false;
        }
        return monsterPresent;
    }

    /**
     * Removes a monster from current room.
     * @param currentRoom Room where player is currently at.
     */
    private static void removeDefeatedMonsterFromRoom(Room currentRoom){
        currentRoom.defeatMonster(currentRoom.getMonsters().get(0));
    }

    /**
     * return true if current room's monster's life is more than 0, false otherwise
     * @param currentRoom Room where player is currently at
     * @return true or false depending if a monster is in the room
     */
    private static boolean checkIfMonsterAlive(Room currentRoom){
        boolean alive = false;
        if(currentRoom.getMonsters().get(0).getLife() > 0){
            alive = true;
        }
        return alive;
    }

    /**
     * returns true or false depending if current player's life is more than 0
     * @param player current game player
     * @return true or false based on player's life value
     */
    private static boolean checkIfPlayerAlive(Player player){
        boolean alive = false;
        if(player.getLife() > 0){
            alive = true;
        }
        return alive;
    }

    /**
     * end the game when monster kills current player.\
     * @param monster current room's monster
     */
    private static void ifPlayerDeath(Monster monster){
        System.out.println("Sorry "+ monster.getName()+" killed you.");
        exit("exit");
    }

    /**
     * generates random number to simulate damage
     * @return random int
     */
    private static int randomDamage(){
        return ThreadLocalRandom.current().nextInt(0, 30);
    }
}