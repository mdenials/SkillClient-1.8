package com.skillclient.modules.misc;

import java.util.HashMap;

public static class ProtectionsMap extends HashMap<String, String>
{
    int pos;
    
    public ProtectionsMap() {
        this.pos = 1;
    }
    
    public String getNickName(final String s) {
        if (!this.containsKey(s)) {
            this.put(s, this.getNext());
        }
        return ((HashMap<K, String>)this).get(s);
    }
    
    public String getNext() {
        final char[] chars = " ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; this.pos + 2 - i > Math.pow(chars.length, i); ++i) {
            sb.append(chars[(int)(this.pos / Math.pow(chars.length, i) % chars.length)]);
        }
        ++this.pos;
        return sb.toString();
    }
    
    public void reset() {
        this.clear();
        this.put(NameProtect.access$0().thePlayer.getName(), "You");
        this.pos = 1;
    }
}
