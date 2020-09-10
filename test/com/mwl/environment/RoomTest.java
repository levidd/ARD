package com.mwl.environment;

import com.mwl.characters.Monster;
import com.mwl.characters.MonsterFactory;
import com.mwl.characters.Normal;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoomTest {
    Room room1;
    Monster monster1;
    Item item1;

    @Before
    public void setUp() throws Exception {
        room1 = new Room("room desc", 1);
        monster1 = MonsterFactory.createMonster();
        item1 = Item.Food;
        room1.addMonster(monster1);
        room1.addItem(item1);
    }

    @Test
    public void defeatMonsterSucessfullyCompleteReturn() {
        Boolean actual = room1.defeatMonster(monster1);
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void defeatMonsterSucessfullyRemoveMonsterFromList() {
        room1.defeatMonster(monster1);
        int actual = room1.getMonsters().size();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void testHashCodeNoChangeWhenItemAndMosterRemove() {
        int actual = room1.hashCode();
        room1.grabItem(item1);
        room1.defeatMonster(monster1);
        int expected = room1.hashCode();
        assertEquals(expected, actual);
    }
}