package com.mwl.characters;

import java.util.Collection;

import com.mwl.environment.Direction;
import com.mwl.environment.Room;
import com.mwl.environment.Item;


public abstract class Player {
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


    //add the item picked up by the user into the item inventory collection
    public void pickUpItem(Item item){
            itemsInventory.add(item);
    }

    //remove the item dropped by the user from the item inventory collection
    public void dropItem(Item item){
        if(itemsInventory.contains(item)){
            itemsInventory.remove(item);
        } else {
            System.out.println("Can't drop this item! It's not in player's item inventory!");
        }
    }

    public abstract void attack();

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

    public void printStats() {
        System.out.println("\u261b " + getName());
        System.out.println("\u2665 " + getLife());
        System.out.println("\u22a1 " + getCurrentRoom().getDescription());
        System.out.println("\u2200 " + getItemsInventory());
    }
}
