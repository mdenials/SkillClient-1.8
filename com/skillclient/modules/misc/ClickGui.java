package com.skillclient.modules.misc;

import com.skillclient.gui.click.SkillClickGui;
import net.minecraft.client.gui.GuiScreen;
import com.skillclient.gui.list.SkillListGui;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.misc.Module;

public class ClickGui extends Module
{
    public ValueBoolean gui_type;
    
    public ClickGui() {
        super("ClickGui", Register.Category.MISC, "Toggle Modules,");
        this.gui_type = (ValueBoolean)new ClickGui.ClickGui$1(this, "new", (Module)this, true);
        this.invisible = true;
        this.modes = 1;
    }
    
    public void onEnable() {
        if (this.gui_type.getValue()) {
            ClickGui.mc.displayGuiScreen((GuiScreen)new SkillListGui());
        }
        else {
            ClickGui.mc.displayGuiScreen((GuiScreen)new SkillClickGui());
        }
    }
    
    public int getDefaultKeyCode() {
        return 54;
    }
}

