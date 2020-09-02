package com.mwl.characters;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.mwl.environment.Direction;
import com.mwl.environment.Room;
import com.mwl.environment.Item;


public abstract class Player {
    private String name;
    private int life;
    private Room currentRoom;
    private List<Item> itemsInventory;

    public Player(){
    }

    public Player(String name, int life, Room currentRoom, List<Item> itemsInventory){
        this.name = name;
        this.life = life;
        this.currentRoom = currentRoom;
        this.itemsInventory = itemsInventory;
    }


    /*If the item is in current room, add the item picked up by the user into the item inventory
    * and remove the item from the room item list
    * if input is not in Item Enum, throw Exception
    * */
    public void pickUpItem(Item item){
        if(!Arrays.stream(Item.values()).anyMatch(item::equals)){
            throw new IllegalArgumentException("Can't pick up! NO such item in this game!");
        }
        else if(currentRoom.getItems().contains(item)){
            itemsInventory.add(item);
            currentRoom.grabItem(item);
        } else {
            System.out.println("Can't pick up! This item is not in the current room!");
        }

    }

    /*If the item is in player inventory, remove the item dropped by the user from the item inventory
    * and add the item into the room item list
    * if input is not in Item Enum, throw Exception
    * */
    public void dropItem(Item item){
        if(!Arrays.stream(Item.values()).anyMatch(item::equals)){
            throw new IllegalArgumentException("Can't pick up! NO such item in this game!");
        }
        else if(itemsInventory.contains(item)){
            itemsInventory.remove(item);
            currentRoom.addItem(item);
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

    public List<Item> getItemsInventory() {
        return itemsInventory;
    }
    public void setItemsInventory(List<Item> itemsInventory) {
        this.itemsInventory = itemsInventory;
    }

    public void printStats() {
        System.out.println("\u261b " + getName());
        System.out.println("\u2665 " + getLife());
        System.out.println("\u22a1 " + getCurrentRoom().getDescription());
        System.out.println("\u2200 " + getItemsInventory());
    }
}
