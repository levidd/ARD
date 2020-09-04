package com.mwl.combat;

import com.mwl.ard.Game;
import com.mwl.characters.Monster;
import com.mwl.characters.Normal;
import com.mwl.characters.Player;
import com.mwl.environment.Room;
import com.mwl.util.Codes;
import com.mwl.util.Colors;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.mwl.combat.WinOrLose.LevelUp;
import static com.mwl.util.ExitGame.exit;

public class combatEngine {


    /**
     * static method a player can call to attack monsters
     *
     * @param player      current player
     * @param currentRoom room where the player is currently.
     */
    public static void fightRoomMonster(Player player, Room currentRoom) {
        if (checkForMonsterInRoom(currentRoom)) {
            Monster monster = currentRoom.getMonsters().get(0);
            int lifeValue = monster.getLife();
            int damage = randomDamage();
            lifeValue -= damage;
            monster.setLife(lifeValue);
            if (!checkIfMonsterAlive(currentRoom)) {
                removeDefeatedMonsterFromRoom(currentRoom);
                LevelUp(player);
                System.out.println("You killed " + Codes.Monster.withColor(monster.getName()));
            } else {
                System.out.println(Codes.Player.withColor(player.getName()) + " has attacked "
                        + Codes.Monster.withColor(monster.getName()) + " and "
                        + Codes.Monster.withColor(monster.getName()) + " lost life value of: "
                        + Codes.Monster.getColor().negative(damage));
                System.out.println(Codes.Monster.withColor(monster.getName()) + " current life value is: "
                        + Codes.Life.withColor(lifeValue));
            }
        }
    }

    /**
     * static method that runs for the monster to fight back when a player attacks.
     *
     * @param monster the monster in the current room.
     * @param player  the player currently in the room with the monster.
     */
    public static void MonsterFightsPlayer(Monster monster, Player player) {
        int quantity = ThreadLocalRandom.current().nextInt(2, 3);

        while (quantity > 0) {
            quantity--;
            if (checkIfMonsterAlive(player.getCurrentRoom())) {
                int lifeValue = player.getLife();
                int damage = randomDamage();
                lifeValue -= damage;
                player.setLife(lifeValue);
                if (!checkIfPlayerAlive(player)) {
                    ifPlayerDeath(monster);
                } else {
                    System.out.println(Codes.Monster.withColor(monster.getName()) + " has attacked "
                            + Codes.Player.withColor(player.getName()) + " and "
                            + Codes.Player.withColor(player.getName()) + " lost life value of: "
                            + Codes.Player.getColor().negative(damage));
                    System.out.println(Codes.Player.withColor(player.getName()) + " current life value is: "
                            + Codes.Life.withColor(lifeValue));
                }
            }
        }
    }

    /**
     * Helper method for fightRoomMonster to check if a monster is in the room
     *
     * @param currentRoom Room that player is currently
     * @return return true if monster is in the current room, false otherwise
     */
    private static boolean checkForMonsterInRoom(Room currentRoom) {
        boolean monsterPresent = true;
        if (currentRoom.getMonsters().size() == 0) {
            System.out.println("Just kidding! No monster in this room");
            monsterPresent = false;
        }
        return monsterPresent;
    }

    /**
     * Removes a monster from current room.
     *
     * @param currentRoom Room where player is currently at.
     */
    private static void removeDefeatedMonsterFromRoom(Room currentRoom) {
        currentRoom.defeatMonster(currentRoom.getMonsters().get(0));
    }

    /**
     * return true if current room's monster's life is more than 0, false otherwise
     *
     * @param currentRoom Room where player is currently at
     * @return true or false depending if a monster is in the room
     */
    private static boolean checkIfMonsterAlive(Room currentRoom) {
        boolean alive = false;
        if (currentRoom.getMonsters().get(0).getLife() > 0) {
            alive = true;
        }
        return alive;
    }

    /**
     * returns true or false depending if current player's life is more than 0
     *
     * @param player current game player
     * @return true or false based on player's life value
     */
    private static boolean checkIfPlayerAlive(Player player) {
        boolean alive = false;
        if (player.getLife() > 0) {
            alive = true;
        }
        return alive;
    }

    /**
     * end the game when monster kills current player.\
     *
     * @param monster current room's monster
     */
    private static void ifPlayerDeath(Monster monster) {
        System.out.println("Sorry " + Codes.Monster.withColor(monster.getName()) + " killed "
                + Codes.Player.withColor("you."));
        exit("exit");
    }

    /**
     * generates random number to simulate damage
     *
     * @return random int
     */
    private static int randomDamage() {
        return ThreadLocalRandom.current().nextInt(0, 30);
    }
}