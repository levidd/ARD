package com.mwl.environment;

import java.util.HashMap;
import java.util.Map;

public class RoomMap {
    private final Map<Room, Map<Direction, Room>> map; // underlying data structure
    private final Room start; // starting room

    /**
     * Default Constructor
     */
    public RoomMap() {
        map = new HashMap<>();
        start = makeNewRoom();
        map.put(start, new HashMap<>());
    }

    /**
     * Gets the map's starting room.
     * @return
     */
    public Room getStart() {
        return start;
    }

    /**
     * Get the size of the map, i.e. the number of rooms on the map at the given moment
     * @return
     */
    public int size() {
        return map.size();
    }

    /**
     * Returns the underlying data structure for Testing purposes
     * @return
     */
    Map<Room, Map<Direction, Room>> getMap() {
        return map;
    }

    /**
     * Logic for moving around on the game map. Moves in the passed direction from given room, and returns the new room
     * location. If the room moving to has not yet been visited, creates one and updates the map accordingly. New rooms
     * are linked in the opposite direction. If the given direction is not applicable, or if room passed is null, throws
     * an IllegalArgumentException.
     * @param currRoom room moving from
     * @param direction which direction to go through
     * @return room that was moved to
     * @throws IllegalArgumentException thrown if given direction is not applicable, or if currRoom is null
     */
    public Room moveRoom(Room currRoom, Direction direction) throws IllegalArgumentException {
        if (currRoom != null) {
            Map<Direction, Room> options = map.get(currRoom); // get submap for ease

            if (!options.containsKey(direction)) { // room hasn't been created yet, make one now
                Room temp = makeNewRoom();
                options.put(direction, temp);
                map.put(temp, new HashMap<>());
                map.get(temp).put(flipDirection(direction),currRoom);
            }

            return options.get(direction);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns the opposing direction string
     * @param direction starting direction
     * @return opposite direction
     */
    private Direction flipDirection(Direction direction) {
        return switch (direction) {
            case North -> Direction.South;
            case West -> Direction.East;
            case East -> Direction.West;
            default -> Direction.North; // for north and whatever else
        };

    }

    /**
     * Makes a new room with ID the current size of room.
     * @return
     */
    private Room makeNewRoom() {
        return new Room("Room #", map.size());
    }
}
