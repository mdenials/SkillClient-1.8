package com.skillclient.misc;

import org.lwjgl.input.Keyboard;
import com.skillclient.gui.GuiOverlay;
import java.awt.Color;
import com.skillclient.utils.ClientSettings;
import com.skillclient.utils.TimerUtil;
import net.minecraft.client.settings.KeyBinding;
import com.skillclient.main.Register;
import java.util.ArrayList;
import java.util.Random;
import com.skillclient.main.SkillClient;
import net.minecraft.client.Minecraft;

public class Module
{
    protected static final Minecraft mc;
    protected static final SkillClient sc;
    protected static Random random;
    public boolean currentlysettingKeyCode;
    private boolean toggled;
    private int mode;
    private int case_;
    public ArrayList<Value> valueList;
    public boolean invisible;
    public Register.Category category;
    public String name;
    private KeyBinding keybinding;
    public String description;
    public int modes;
    public TimerUtil toggled_since;
    
    static {
        mc = Minecraft.getMinecraft();
        sc = SkillClient.getClient();
        Module.random = new Random();
    }
    
    public Module(final String name, final Register.Category category, final String description) {
        this.currentlysettingKeyCode = false;
        this.mode = 0;
        this.case_ = 0;
        this.valueList = new ArrayList<Value>();
        this.modes = 2;
        this.toggled_since = new TimerUtil();
        this.name = name;
        this.category = category;
        this.description = description;
        this.keybinding = new KeyBinding(name, this.getDefaultKeyCode(), "Client");
        new Module.Module$1(this, "toggle", this, false);
        new Module.Module$2(this, "KeyBind", this, this.getKeyCode());
    }
    
    public int getDefaultKeyCode() {
        return 0;
    }
    
    public int getBind() {
        return 0;
    }
    
    public String getDisplayName() {
        return this.name;
    }
    
    public final KeyBinding getKeyBinding() {
        return this.keybinding;
    }
    
    public final int getKeyCode() {
        return this.keybinding.getKeyCode();
    }
    
    public int getnewCase() {
        return this.case_++;
    }
    
    public boolean isActive() {
        return this.isToggled();
    }
    
    public final boolean keyTyped(final char typedChar, final int keyCode) {
        if (this.currentlysettingKeyCode) {
            if (keyCode == 1) {
                this.setKeyCode(0);
            }
            else {
                this.setKeyCode(keyCode);
            }
            ClientSettings.saveOptions();
            this.currentlysettingKeyCode = false;
            Module.mc.displayGuiScreen(Module.mc.currentScreen);
            return false;
        }
        return true;
    }
    
    public void onDisable() {
        this.setToggled(false);
        this.setMode(0);
        if (!this.invisible) {
            GuiOverlay.notify("Disabled " + this.name, Color.CYAN);
        }
    }
    
    public void onEnable() {
        this.setToggled(true);
        if (!this.invisible) {
            GuiOverlay.notify("Enabled " + this.name, Color.CYAN);
        }
    }
    
    public final void setKeyCode(final int KeyCode) {
        this.keybinding.setKeyCode(KeyCode);
        if (!this.invisible) {
            GuiOverlay.notify("Binded " + this.name + " to " + Keyboard.getKeyName(KeyCode), Color.GRAY);
        }
    }
    
    public final void toggle() {
        this.toggled_since.setLastMS();
        if (this.modes != 0) {
            if (this.modes == 1) {
                this.onEnable();
                this.onDisable();
            }
            else {
                this.setMode(this.getMode() + 1);
                if (this.getMode() >= this.modes) {
                    this.setMode(0);
                }
                if (this.getMode() == 0) {
                    this.onDisable();
                }
                else {
                    this.onEnable();
                }
            }
        }
        ClientSettings.saveOptions();
    }
    
    public void setToggled(final boolean toggled) {
        this.toggled = toggled;
    }
    
    public boolean isToggled() {
        return this.toggled;
    }
    
    public int getMode() {
        return this.mode;
    }
    
    public void setMode(final int mode) {
        this.mode = mode;
    }
    
    public void setCase_(final int case_) {
        this.case_ = case_;
    }
}

