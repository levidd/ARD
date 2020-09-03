package com.mwl.util.commands;

/**
 * Pickup Class implements the Commands interface and throws exception if user input is not valid
 */
public class Pickup implements Commands {
    @Override
    public void do_command(String option) {
        if (option == null)
            throw new IllegalArgumentException("pickup what?");
    }
}
