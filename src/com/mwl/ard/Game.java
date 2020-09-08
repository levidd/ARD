package com.mwl.ard;

import com.mwl.characters.Monster;
import com.mwl.characters.MonsterFactory;
import com.mwl.characters.Player;
import com.mwl.environment.Direction;
import com.mwl.environment.Item;
import com.mwl.environment.Room;
import com.mwl.environment.RoomMap;
import com.mwl.util.Codes;
import com.mwl.util.ConsoleManager;
import com.mwl.util.TextParser;

import java.util.Random;

import static com.mwl.util.ExitGame.exit;

public class Game {
    Player player;
    RoomMap gameMap;
    Random random = new Random();
    Monster boss;


    public Game() {
        // default constructor
        gameMap = new RoomMap();
    }

    boolean play() {
        // tell player what room they are in or if monster is in front of them
//        System.out.println(player.getCurrentRoom().getDescription());
        System.out.print("> ");

        // ask what player wants to do
        // Text parser
        String[] command = TextParser.parser();
        // do that thing
        switch (command[0]) {
            case "move" -> gameMap.moveCharacter(player, Direction.valueOf(command[1]));
            case "look" -> Look(player, command[1]);
            case "flight" -> Flight(player, command[1]);
            case "fight" -> Fight(player, command[1]);
            case "pickup" -> player.pickUpItem(Item.valueOf(command[1]));
            case "drop" -> player.dropItem(Item.valueOf(command[1]));
            case "help" -> ConsoleManager.gameExplanation();
            case "use" -> UsePower(player, command[1]);
        }

        return true;
    }

    public void newGame() {
        // new game logic
        ConsoleManager.gameIntro();
        player = ConsoleManager.choosePlayer(gameMap);

        boolean playGame = true;
        boolean bossIsHere = false;
        while (playGame) {
            // keep playing game until it passes back as false
            playGame = play();

            if (boss == null) {
                boss = MonsterFactory.createBossMonster(player);
                player.getCurrentRoom().addMonster(boss);
            }
            if (boss != null && boss.getLife() <= 0) {
                System.out.println(Codes.Player.withColor(player.getName()) + " killed "
                        + Codes.Monster.withColor(boss.getName()) + "! You win!!!!");
                exit("exit");
            }
        }

        // quit message
        System.out.println("Thanks for playing! Come play again");

    }

    void Flight(Player player, String option) {
        System.out.println("Flying " + option);
        // run method to do the action
    }

    void Fight(Player player, String option) {
        System.out.println("fighting " + option);
        player.attack();
    }

    void UsePower(Player player, String option) {
        System.out.println("use " + option);
        player.useSpecialPower();
    }

    void Look(Player player, String option) {
        switch (option) {
            case "Around" -> player.getCurrentRoom().overview();
            case "Me" -> player.printStats();
        }
    }

}

