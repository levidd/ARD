package com.mwl.util;

public enum Colors {

    RED("[31m", "[41m"),
    GREEN("[32m", "[42m"),
    YELLOW("[33m", "[43m"),
    BLUE("[34m", "[44m"),
    MAGENTA("[35m", "[45m"),
    CYAN("[36m", "[46m"),
    LIGHTGRAY("[37m", "[47m"),
    LIGHTRED("[91m", "[101m"),
    LIGHTGREEN("[92m", "[102m"),
    LIGHTYELLOW("[93m", "[103m"),
    LIGHTBLUE("[94m", "[104m"),
    LIGHTMAGENTA("[95m", "[105m"),
    LIGHTCYAN("[96m", "[106m");

    String foreground;
    String background;
    String end;
    String escapeCode = "\033";

    Colors(String foreground, String background) {
        this.foreground = escapeCode + foreground;
        this.background = escapeCode + background;
        this.end = escapeCode + "[0m";
    }

    public String toColor(String output) {
        return foreground + output + end;
    }

    public String highlight(String output) {
        return background + output + end;
    }

    public String negative(String output) {
        return escapeCode + "[7m" + toColor(output) + end;
    }

    public String negative(int output) {
        return negative("" + output);
    }
}
