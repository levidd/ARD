package com.mwl.environment;

import java.util.ArrayList;
import java.util.List;

public class Room {
    String description;
    List items;
    List doors;

    public Room() {
        // default constructor
        description = "This is the room description";
        items = new ArrayList();
        doors = new ArrayList();
    }
    

}
