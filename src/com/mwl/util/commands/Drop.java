package com.mwl.util.commands;

import com.mwl.environment.Item;

import java.util.Arrays;

/**
 * Drop class implements Commands interface and checks if command option is valid
 */
public class Drop implements Commands {
    @Override
    public void do_command(String option) {
        // Save word to use multiple times.
        // Check if the argument word do not match the items in the enum
        if (!Arrays.stream(Item.values()).anyMatch((item) -> item.name().equals(option))) {
            throw new IllegalArgumentException("Drop what?");
        }
    }
}
