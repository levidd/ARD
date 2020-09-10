package com.mwl.util;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static com.mwl.util.TextParser.parser;
import static org.junit.Assert.assertEquals;

public class TextParserTest {

    @Test
    public void parserAcceptanceTestMoveNorth() {
        String input = "MovE nOrth";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String[] expected = {"move", "North"};
        String[] actual = parser();
        assertEquals(expected, actual);
    }

}