package com.mwl.characters;

import com.mwl.environment.Item;
import com.mwl.environment.Room;
import com.mwl.environment.RoomMap;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class PlayerTest {
    Room room1 = new RoomMap().getStart();
    List<Item> playerInventory = new ArrayList<>();
    List<Item> roomInventory = new ArrayList<>();
    Player player = new Wolverine("Wolverine", 100, room1, playerInventory, 1);


    @Before
    public void setUp() throws Exception {



    }

    @Test
    public void testPickUpItem(){
        roomInventory.add(Item.valueOf("food"));
        player.pickUpItem(Item.valueOf("food"));

    }

}
