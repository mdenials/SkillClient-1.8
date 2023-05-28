package com.skillclient.chat;

public static class Nick
{
    public String oldname;
    public String newname;
    
    public Nick(final String oldname, final String newname) {
        this.oldname = oldname;
        this.newname = newname;
    }
    
    public static boolean remove(final String oldname) {
        for (int i = 0; i < CmdNickName.names.size(); ++i) {
            final Nick nick = CmdNickName.names.get(i);
            if (nick.oldname.equalsIgnoreCase(oldname)) {
                CmdNickName.names.remove(nick);
                return true;
            }
        }
        return false;
    }
}

