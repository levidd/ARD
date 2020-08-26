package com.mwl.characters;

import com.mwl.environment.Room;

public abstract class Monster {
    String name;
    int life;
    Room currentRoom;

    public Monster() {
        // default constructor
    }

    public abstract void attack();
    public abstract void move();
}
