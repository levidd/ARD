package com.mwl.util;

public enum Codes {

    Monster("\u2648"),
    Room("\u22a1"),
    Player("\u261b"),
    Life("\u2665"),
    Item("\u2200");

    String code;

    Codes(String unicode) {
        this.code = unicode;
    }

    public String getCode() {
        return code;
    }
}
