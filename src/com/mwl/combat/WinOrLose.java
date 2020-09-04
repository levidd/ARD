package com.mwl.combat;

import com.mwl.characters.Monster;
import com.mwl.characters.Player;

import java.lang.management.PlatformLoggingMXBean;

public class WinOrLose {

    /**
     * Call this method when monster is defeated to level up player.
     * @param player current game player
     */
    public static void LevelUp(Player player){
        player.setLevel(player.getLevel()+1);
    }

//    public static void main(String[] args) {
//        int level = 150/100;
//        System.out.println(level);
//    }

}
