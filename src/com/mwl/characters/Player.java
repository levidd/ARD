package com.mwl.characters;

import java.util.Collection;
import com.mwl.environment.Room;
import com.mwl.environment.Item;


public class Player {
    private String name;
    private int life;
    private Room currentRoom;
    private Collection<Item> itemsInventory;

    public Player(){
    }

    public Player(String name, int life, Room currentRoom, Collection<Item> itemsInventory){
        this.name = name;
        this.life = life;
        this.currentRoom = currentRoom;
        this.itemsInventory = itemsInventory;
    }

    public void move(String direction){
        //change user's location based on the input direction user chooses
    }

    public void pickUpItem(String item){
        //add the item picked up by the user into the item inventory collection
    }

    public void dropItem(String item){
        //remove the item dropped by the user from the item inventory collection
    }

    public void attack(){
        //decrease the life value of the monsters
    }

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

    public Collection<Item> getItemsInventory() {
        return itemsInventory;
    }
    public void setItemsInventory(Collection<Item> itemsInventory) {
        this.itemsInventory = itemsInventory;
    }
}
