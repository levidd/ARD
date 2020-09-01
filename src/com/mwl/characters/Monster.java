package com.mwl.characters;

import com.mwl.environment.Room;

public abstract class Monster {
   private String name;
   private String description;
   private int life;
   private Room currentRoom;

    public Monster() {
        // default constructor
    }

    public abstract void attack();
    public abstract void move();

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", life=" + life +
                ", currentRoom=" + currentRoom +
                '}';
    }
}
