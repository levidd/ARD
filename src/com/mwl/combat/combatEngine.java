package com.mwl.combat;

import com.mwl.ard.Game;
import com.mwl.characters.Monster;
import com.mwl.characters.Normal;
import com.mwl.characters.Player;
import com.mwl.environment.Room;
import com.mwl.util.Codes;
import com.mwl.util.Colors;

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
            Monster monster = currentRoom.getMonsters().get(0);
            int lifeValue = monster.getLife();
            lifeValue -= 20;
            monster.setLife(lifeValue);
            if(!checkIfMonsterAlive(currentRoom)){
                removeDefeatedMonsterFromRoom(currentRoom);
                System.out.println("You killed " + Codes.Monster.withColor(monster.getName()));
            }else {
                System.out.println(Codes.Player.withColor(player.getName()) + " has attacked monster and monster lost life value of: " + Codes.Monster.getColor().negative("" + 20));
                System.out.println(Codes.Monster.withColor(monster.getName()) + " current life value is: "
                        + Codes.Life.withColor("" + lifeValue));
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
                    System.out.println(Codes.Monster.withColor(monster.getName()) + " has attacked "
                            + Codes.Player.withColor(player.getName()) + " and " + Codes.Player.withColor(player.getName())
                            + " lost life value of: " + Codes.Player.getColor().negative("" + 20));
                    System.out.println(Codes.Player.withColor(player.getName()) + " current life value is: "
                            + Codes.Life.withColor("" + lifeValue));
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