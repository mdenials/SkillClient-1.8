package com.skillclient.main;

import com.skillclient.misc.Module;
import java.util.ArrayList;

public enum Category
{
    ALL("ALL", 0, "All"), 
    ACTIVE("ACTIVE", 1, "Active", true), 
    HAS_KEYCODE("HAS_KEYCODE", 2, "Has KeyCode", true), 
    NEW("NEW", 3, "SEXY&NEW"), 
    MOVEMENT("MOVEMENT", 4, "Movement"), 
    RENDER("RENDER", 5, "Render"), 
    COMBAT("COMBAT", 6, "Combat"), 
    PLAYER("PLAYER", 7, "Player"), 
    WORLD("WORLD", 8, "World"), 
    AUTO("AUTO", 9, "Auto"), 
    MISC("MISC", 10, "Misc"), 
    UTIL("UTIL", 11, "Util"), 
    EXPLOIT("EXPLOIT", 12, "Exploit");
    
    private String name;
    private final boolean special;
    private ArrayList<Module> modules;
    public static final Category[] normal;
    
    static {
        normal = new Category[] { Category.NEW, Category.MOVEMENT, Category.RENDER, Category.COMBAT, Category.PLAYER, Category.WORLD, Category.AUTO, Category.MISC, Category.UTIL };
    }
    
    private Category(final String name2, final int ordinal, final String name, final boolean special) {
        this.modules = new ArrayList<Module>();
        this.name = name;
        this.special = special;
    }
    
    private Category(final String s, final int n, final String name) {
        this(s, n, name, false);
    }
    
    public ArrayList<Module> getModules() {
        synchronized (this.modules) {
            // monitorexit(this.modules)
            return this.modules;
        }
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public void registerModule(final Module module) {
        synchronized (this.modules) {
            this.modules.add(module);
        }
        // monitorexit(this.modules)
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean isSpecial() {
        return this.special;
    }
}

