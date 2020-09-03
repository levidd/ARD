package com.mwl.characters;

import com.mwl.environment.Room;

public class Normal extends Monster{
    Player player;

    // Constructor
    public Normal(String name, int life, String description){
        setName(name);
        setDescription(description);
        setLife(life);
     //   setCurrentRoom(currentRoom);
    }

    // Abstract methods with local logic
    @Override
    public void attack() {
        int lifeValue = player.getLife();
        lifeValue -= 20;
        player.setLife(lifeValue);
        System.out.println("Monster has attacked player and player lost life value of: 20");
        System.out.println("Player current life value is: " + lifeValue);
    }

    @Override
    public void move() {
        // Add move code
    }

}
