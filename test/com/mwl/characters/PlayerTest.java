package com.mwl.characters;

import com.mwl.environment.Item;
import com.mwl.environment.Room;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class PlayerTest {

    List<Item> playerInventory = new ArrayList<>();
    List<Item> roomInventory = new ArrayList<>();

    Room room1 = new Room("room", 1);
    Player player = new Wolverine("Wolverine", 100, room1, playerInventory, 1);



    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPickUpItem(){
        List<Item> list = new ArrayList<>();
        room1.addItem(Item.valueOf("Food"));
        player.pickUpItem(Item.valueOf("Food"));

        assertEquals(player.getItemsInventory(), list.add(Item.valueOf("Food")));
    }

}
