package com.mwl.combat;

import com.mwl.characters.Player;

import static com.mwl.characters.MonsterFactory.getMonsterStartingLife;
import static com.mwl.characters.MonsterFactory.setMonsterStartingLife;

public class WinOrLose {

    /**
     * Call this method when monster is defeated to level up player.
     *
     * @param player current game player
     */
    public static void LevelUp(Player player) {
        player.setLevel(player.getLevel() + 1);
        IncreasePlayerLife(player);
        //IncreaseMonstersLife(player);
        setMonsterStartingLife(getMonsterStartingLife() + 20);
    }

    private static void IncreasePlayerLife(Player player) {
        player.setLife(player.getLife() + 20);
    }

    /**
     * Increments existing monsters' life
     */
//    private static void IncreaseMonstersLife(Player player) {
//        Set<Room> rooms = Game.gameMap.roomList();
//        for (Room room : rooms) {
//            if (checkForMonsterInRoom(room)) {
//                room.getMonsters().get(0).setLife(room.getMonsters().get(0).getLife() + 20);
//            }
//        }
//    }
}
