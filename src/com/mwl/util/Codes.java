package com.mwl.util;

public enum Codes {

    Monster("\u2648 ", Colors.RED),
    Room("\u22a1 ", Colors.YELLOW),
    Player("\u261b ", Colors.BLUE),
    Life("\u2665 ", Colors.LIGHTRED),
    Item("\u2200 ", Colors.LIGHTMAGENTA),
    Left("\u201f", Colors.GREEN),
    Right("\u201d", Colors.GREEN);


    String code;
    Colors color;

    Codes(String unicode, Colors color) {
        this.code = unicode;
        this.color = color;
    }

    public String getCode() {
        return withColor(code);
    }

    public String withColor(String output) {
        return color.toColor(output);
    }

    public Colors getColor() {
        return color;
    }
}
