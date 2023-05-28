package com.skillclient.chat;

import java.util.List;
import com.skillclient.main.SkillClient;
import net.minecraft.client.Minecraft;

public abstract class Cmd
{
    private String command;
    private String description;
    protected static final Minecraft mc;
    protected static final SkillClient sc;
    
    static {
        mc = Minecraft.getMinecraft();
        sc = SkillClient.getClient();
    }
    
    public Cmd(final String command, final String description) {
        this.command = command;
        this.description = description;
    }
    
    public abstract void action(final String[] p0);
    
    public abstract List<String> tabComplete(final String[] p0);
    
    public String getCommand() {
        return this.command;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setCommand(final String command) {
        this.command = command;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Cmd)) {
            return false;
        }
        final Cmd other = (Cmd)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$command = this.getCommand();
        final Object other$command = other.getCommand();
        Label_0065: {
            if (this$command == null) {
                if (other$command == null) {
                    break Label_0065;
                }
            }
            else if (this$command.equals(other$command)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null) {
            if (other$description == null) {
                return true;
            }
        }
        else if (this$description.equals(other$description)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof Cmd;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $command = this.getCommand();
        result = result * 59 + (($command == null) ? 43 : $command.hashCode());
        final Object $description = this.getDescription();
        result = result * 59 + (($description == null) ? 43 : $description.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "Cmd(command=" + this.getCommand() + ", description=" + this.getDescription() + ")";
    }
}

