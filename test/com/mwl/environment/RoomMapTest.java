package com.mwl.environment;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import static com.mwl.environment.Direction.*;

import static org.junit.Assert.*;

public class RoomMapTest {
    RoomMap map;

    @Before
    public void setUp() throws Exception {
        map = new RoomMap();
    }

    @Test
    public void moveRoom_makesNewRoom_whenNewDirection() {
        assertEquals(1, map.size());
        map.moveRoom(map.getStart(), North);
        assertEquals(2, map.size());
    }

    @Test
    public void moveRoom_linksRoomsCorrectly() {
        Room temp = map.moveRoom(map.getStart(), North);
        temp = map.moveRoom(temp, South);

        assertEquals(2, map.size()); // no new rooms added
        assertEquals(map.getStart(), temp); // moved back to starting room

        map.moveRoom(temp, North); // moving back creates no new rooms
        assertEquals(2, map.size());
    }

    @Test
    public void moveRoom_addsRoomsMultiple() {
        assertEquals(1, map.size());
        Room temp = map.getStart();
        for (int i = 0; i < 10; i++) {
            temp = map.moveRoom(temp, East);
        }

        assertEquals(11, map.size());
        long only_east = map.getMap().values().stream()
                .filter(item -> item.containsKey(East) && item.containsKey(West))
                .map(Map::size)
                .count();
        // first and last room won't have both east and west. only rooms in between
        assertEquals(9, only_east);
    }

    @Test(expected = IllegalArgumentException.class)
    public void moveRoom_startAtNull_ThrowsIllegalArgumentException() {
        map.moveRoom(null, North);
    }

}