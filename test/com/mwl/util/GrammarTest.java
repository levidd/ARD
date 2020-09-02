package com.mwl.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GrammarTest {
    Grammar grammar;

    @Before
    public void setUp() {
        grammar = new Grammar();
    }

    @Test
    public void generate_Sentence() {
        System.out.println(grammar.generate_Sentence());
    }
}