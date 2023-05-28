package com.skillclient.gui.accountManager.mcleaks;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public enum ChatColor
{
    BLACK("BLACK", 0, '0', "black"), 
    DARK_BLUE("DARK_BLUE", 1, '1', "dark_blue"), 
    DARK_GREEN("DARK_GREEN", 2, '2', "dark_green"), 
    DARK_AQUA("DARK_AQUA", 3, '3', "dark_aqua"), 
    DARK_RED("DARK_RED", 4, '4', "dark_red"), 
    DARK_PURPLE("DARK_PURPLE", 5, '5', "dark_purple"), 
    GOLD("GOLD", 6, '6', "gold"), 
    GRAY("GRAY", 7, '7', "gray"), 
    DARK_GRAY("DARK_GRAY", 8, '8', "dark_gray"), 
    BLUE("BLUE", 9, '9', "blue"), 
    GREEN("GREEN", 10, 'a', "green"), 
    AQUA("AQUA", 11, 'b', "aqua"), 
    RED("RED", 12, 'c', "red"), 
    LIGHT_PURPLE("LIGHT_PURPLE", 13, 'd', "light_purple"), 
    YELLOW("YELLOW", 14, 'e', "yellow"), 
    WHITE("WHITE", 15, 'f', "white"), 
    MAGIC("MAGIC", 16, 'k', "obfuscated"), 
    BOLD("BOLD", 17, 'l', "bold"), 
    STRIKETHROUGH("STRIKETHROUGH", 18, 'm', "strikethrough"), 
    UNDERLINE("UNDERLINE", 19, 'n', "underline"), 
    ITALIC("ITALIC", 20, 'o', "italic"), 
    RESET("RESET", 21, 'r', "reset");
    
    public static final String ALL_CODES = "0123456789AaBbCcDdEeFfKkLlMmNnOoRr";
    public static final Pattern STRIP_COLOR_PATTERN;
    private static final Map<Character, ChatColor> BY_CHAR;
    private final char code;
    private final String toString;
    private final String name;
    
    static {
        STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + String.valueOf('§') + "[0-9A-FK-OR]");
        BY_CHAR = new HashMap<Character, ChatColor>();
        ChatColor[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final ChatColor colour = values[i];
            ChatColor.BY_CHAR.put(colour.code, colour);
        }
    }
    
    private ChatColor(final String name2, final int ordinal, final char code, final String name) {
        this.code = code;
        this.name = name;
        this.toString = new String(new char[] { '§', code });
    }
    
    @Override
    public String toString() {
        return this.toString;
    }
}
