package com.mwl.util.commands;

/**
 * Help Class implements Commands interface and validate user option
 */
public class Help implements Commands {
    /**
     * do_command method verifies if option is valid
     * @param option is the user input
     */
    @Override
    public void do_command(String option) {
        // Check if the argument word do not match
        if (!option.equals("Me")) {
            throw new IllegalArgumentException("Invalid command. Try again!");
        }
    }
}
