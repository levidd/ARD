package com.mwl.util.commands;

import java.util.List;

/**
 * Look Class implements the Commands interface to validate user input
 */
public class Look implements Commands {
    @Override
    public void do_command(String option) throws IllegalArgumentException {
        List<String> valid = List.of("Around", "Me");
        if (option == null || !valid.contains(option))
            throw new IllegalArgumentException("Look where?");
    }
}