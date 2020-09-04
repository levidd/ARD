package com.mwl.characters;

import com.mwl.environment.Item;
import com.mwl.util.Codes;
import com.mwl.environment.Room;

import java.util.List;


public abstract class Player {
    private String name;
    private int life;
    private Room currentRoom;
    private List<Item> itemsInventory;
    private int level;

    public Player(){
    }

    public Player(String name, int life, Room currentRoom, List<Item> itemsInventory, int level){
        this.name = name;
        this.life = life;
        this.currentRoom = currentRoom;
        this.itemsInventory = itemsInventory;
        this.level = level;
    }


    /*If the item is in current room, add the item picked up by the user into the item inventory
    * and remove the item from the room item list
    * if input is not in Item Enum, throw Exception
    * */
    public void pickUpItem(Item item){
//        if(!Arrays.stream(Item.values()).anyMatch(item::equals)){
//            throw new IllegalArgumentException("Can't pick up! NO such item in this game!");
//        }
         if(currentRoom.getItems().contains(item)){
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
//        if(!Arrays.stream(Item.values()).anyMatch(item::equals)){
//            throw new IllegalArgumentException("Can't pick up! NO such item in this game!");
//        }
         if(itemsInventory.contains(item)){
            itemsInventory.remove(item);
            currentRoom.addItem(item);
        } else {
            System.out.println("Can't drop this item! It's not in player's item inventory!");
        }
    }

    public abstract void attack();
    public abstract void useSpecialPower();

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
        System.out.println(Codes.Player.getCode() + getName());
        System.out.println(Codes.Life.getCode() + getLife());
        System.out.println(Codes.Room.getCode()  + "Room " + getCurrentRoom().getId());
        System.out.println(Codes.Item.getCode() + getItemsInventory());
        System.out.println(Codes.Level.getCode() + " Level " + getLevel());
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
