package com.aws.util;

public final class AnsiEscapeCodes {
    private static final String CSI = "\u001b[";

    private static final String GRAPHIC_SUFFIX ="m";
    private static final int GRAPHIC_FG_DARK_BASE = 30;
    private static final int GRAPHIC_FG_LIGHT_BASE = 90;
    private static final int GRAPHIC_RESET = 0;

    private static final int COLOR_BLACK = 0;
    private static final int COLOR_RED = 1;
    private static final int COLOR_GREEN = 2;
    private static final int COLOR_YELLOW = 3;
    private static final int COLOR_BLUE = 4;
    private static final int COLOR_MAGENTA = 5;
    private static final int COLOR_CYAN = 6;
    private static final int COLOR_WHITE = 7;
    private static final int COLOR_DEFAULT = 9;

    public static final String RESET_FOREGROUND = AnsiSetColor(GRAPHIC_FG_DARK_BASE, COLOR_DEFAULT);
    public static final String SET_FOREGROUND_BLACK = AnsiSetColor(GRAPHIC_FG_DARK_BASE, COLOR_BLACK);
    public static final String SET_FOREGROUND_DARK_RED = AnsiSetColor(GRAPHIC_FG_DARK_BASE, COLOR_RED);
    public static final String SET_FOREGROUND_DARK_GREEN = AnsiSetColor(GRAPHIC_FG_DARK_BASE, COLOR_GREEN);
    public static final String SET_FOREGROUND_DARK_YELLOW = AnsiSetColor(GRAPHIC_FG_DARK_BASE, COLOR_YELLOW);
    public static final String SET_FOREGROUND_DARK_BLUE = AnsiSetColor(GRAPHIC_FG_DARK_BASE, COLOR_BLUE);
    public static final String SET_FOREGROUND_DARK_MAGENTA = AnsiSetColor(GRAPHIC_FG_DARK_BASE, COLOR_MAGENTA);
    public static final String SET_FOREGROUND_DARK_CYAN = AnsiSetColor(GRAPHIC_FG_DARK_BASE, COLOR_CYAN);
    public static final String SET_FOREGROUND_LIGHT_GRAY = AnsiSetColor(GRAPHIC_FG_DARK_BASE, COLOR_WHITE);
    public static final String SET_FOREGROUND_DARK_GRAY = AnsiSetColor(GRAPHIC_FG_LIGHT_BASE, COLOR_BLACK);
    public static final String SET_FOREGROUND_LIGHT_RED = AnsiSetColor(GRAPHIC_FG_LIGHT_BASE, COLOR_RED);
    public static final String SET_FOREGROUND_LIGHT_GREEN = AnsiSetColor(GRAPHIC_FG_LIGHT_BASE, COLOR_GREEN);
    public static final String SET_FOREGROUND_LIGHT_YELLOW = AnsiSetColor(GRAPHIC_FG_LIGHT_BASE, COLOR_YELLOW);
    public static final String SET_FOREGROUND_LIGHT_BLUE = AnsiSetColor(GRAPHIC_FG_LIGHT_BASE, COLOR_BLUE);
    public static final String SET_FOREGROUND_LIGHT_MAGENTA = AnsiSetColor(GRAPHIC_FG_LIGHT_BASE, COLOR_MAGENTA);
    public static final String SET_FOREGROUND_LIGHT_CYAN = AnsiSetColor(GRAPHIC_FG_LIGHT_BASE, COLOR_CYAN);
    public static final String SET_FOREGROUND_WHITE = AnsiSetColor(GRAPHIC_FG_LIGHT_BASE, COLOR_WHITE);

    private static String AnsiGraphicCommand(int commandCode){
        return CSI + commandCode + GRAPHIC_SUFFIX;
    }
    private static String AnsiSetColor(int base, int color){
        return AnsiGraphicCommand(base + color);
    }
}
