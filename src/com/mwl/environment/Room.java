package com.mwl.environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Room {
    private String description; // description of the room
    private List<Item> items; // list of items in room
    private final int id; // room id (for ensuring hashcode is different)

    /**
     * Constructor
     * @param description
     * @param id
     */
    public Room(String description, int id) {
        this.description = description;
        this.id = id;
        items = new ArrayList<>();
    }

    /**
     * Return room description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Return all items in room
     * @return items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Returns room id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Adds item to room's item list
     * @param item
     */
    public void addItem(Item item) {
        if (item != null) {
            items.add(item);
        }
    }

    /**
     * Adds a list of items to room's item list.
     * @param items
     */
    public void addAllItems(List<Item> items) {
        if (items != null) {
            this.items.addAll(items);
        }
    }

    /**
     * Grab an item from room. Removes item from room's item list, if present. Returns a boolean if successfully
     * removed the item.
     * @param item
     * @return
     */
    public boolean grabItem(Item item) {
        return items.remove(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return getId() == room.getId() &&
                Objects.equals(getDescription(), room.getDescription()) &&
                Objects.equals(getItems(), room.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getItems(), getId());
    }

}
