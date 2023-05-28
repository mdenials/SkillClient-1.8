package com.skillclient.gui.list;

import org.lwjgl.input.Mouse;
import com.skillclient.gui.utils.SkillGuiCheckBox;
import com.skillclient.utils.ClientSettings;
import java.io.IOException;
import com.skillclient.misc.Value;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.Minecraft;
import java.util.Iterator;
import org.lwjgl.opengl.GL11;
import com.skillclient.main.SkillClient;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import com.skillclient.misc.Module;
import com.skillclient.main.Register;
import net.minecraft.client.gui.GuiTextField;
import com.skillclient.gui.utils.GuiButton;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;

public class SkillListGui extends GuiScreen
{
    int oldMouseX;
    int oldMouseY;
    protected List<GuiButton> categoryList;
    protected List<GuiButton> modulesList;
    float modulesOffset;
    float modulesMotion;
    protected static List<GuiButton> settingsList;
    float settingsOffset;
    float settingsMotion;
    GuiTextField search;
    Register.Category category;
    public Module module;
    ArrayList<Module> modules;
    public boolean needs_update;
    
    static {
        SkillListGui.settingsList = (List<GuiButton>)Lists.newArrayList();
    }
    
    public SkillListGui() {
        this.oldMouseY = 0;
        this.categoryList = (List<GuiButton>)Lists.newArrayList();
        this.modulesList = (List<GuiButton>)Lists.newArrayList();
        this.modulesOffset = 0.0f;
        this.modulesMotion = 0.0f;
        this.settingsOffset = 0.0f;
        this.settingsMotion = 0.0f;
        this.category = Register.Category.ALL;
        this.module = SkillClient.getClient().moduleList.get(0);
        this.modules = new ArrayList<Module>();
        this.needs_update = false;
    }
    
    public void initGui() {
        this.modulesOffset = 0.0f;
        this.categoryList.clear();
        for (int i = 0; i < Register.Category.values().length; ++i) {
            this.categoryList.add(new GuiButton(i, 10, 10 + i * 22, 70, 20, Register.Category.values()[i].getName()));
        }
        this.categoryList.get(0).enabled = false;
        (this.search = new GuiTextField(-1, this.fontRendererObj, 90, 10, this.width - 100, 20)).setMaxStringLength(256);
        this.search.setFocused(true);
        this.search.setCanLoseFocus(false);
        this.updateCategory();
        this.updateModule();
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        super.drawWorldBackground(255);
        this.search.drawTextBox();
        for (int i = 0; i < this.categoryList.size(); ++i) {
            this.categoryList.get(i).drawButton(SkillListGui.mc, mouseX, mouseY);
        }
        GL11.glPushMatrix();
        this.prepareScissorBox(0.0f, 40.0f, (float)this.width, (float)(this.height - 10));
        GL11.glEnable(3089);
        for (final GuiButton m : this.modulesList) {
            m.yPosition = 40 + m.id * 25 - (int)this.modulesOffset;
            m.drawButton(SkillListGui.mc, mouseX, mouseY);
        }
        for (final GuiButton m : SkillListGui.settingsList) {
            m.yPosition = 40 + m.id * 25 - (int)this.settingsOffset;
            if (m instanceof GuiTextField) {
                ((GuiTextField)m).drawTextBox();
            }
            else {
                m.drawButton(SkillListGui.mc, mouseX, mouseY);
            }
        }
        GL11.glDisable(3089);
        GL11.glPopMatrix();
    }
    
    private void prepareScissorBox(final float x, final float y, final float x2, final float y2) {
        final ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        final int factor = sr.getScaleFactor();
        GL11.glScissor((int)(x * factor), (int)((sr.getScaledHeight() - y2) * factor), (int)((x2 - x) * factor), (int)((y2 - y) * factor));
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        Keyboard.enableRepeatEvents(true);
        for (final GuiButton m : SkillListGui.settingsList) {
            if (m.id != -1 && this.module.valueList.get(m.id).keyTyped(m, typedChar, keyCode)) {
                if (this.category.isSpecial()) {
                    final float old = this.modulesOffset;
                    this.updateCategory();
                    this.modulesOffset = old;
                }
                return;
            }
        }
        super.keyTyped(typedChar, keyCode);
        this.search.textboxKeyTyped(typedChar, keyCode);
        this.updateCategory();
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        this.search.mouseClicked(mouseX, mouseY, mouseButton);
        for (int i = 0; i < this.categoryList.size(); ++i) {
            final GuiButton guibutton = this.categoryList.get(i);
            if (guibutton.mousePressed(SkillListGui.mc, mouseX, mouseY)) {
                guibutton.playPressSound(SkillListGui.mc.getSoundHandler());
                this.category = Register.Category.values()[i];
                this.updateCategory();
                for (final GuiButton t : this.categoryList) {
                    t.enabled = true;
                }
                guibutton.enabled = false;
            }
        }
        for (final GuiButton m : this.modulesList) {
            if (m.id == -1) {
                continue;
            }
            m.yPosition = 40 + m.id * 25 - (int)this.modulesOffset;
            if (!m.mousePressed(SkillListGui.mc, mouseX, mouseY)) {
                continue;
            }
            m.playPressSound(SkillListGui.mc.getSoundHandler());
            this.module = this.modules.get(m.id);
            this.updateModule();
        }
        for (final GuiButton m : SkillListGui.settingsList) {
            if (m.id == -1) {
                continue;
            }
            m.yPosition = 40 + m.id * 25 - (int)this.settingsOffset;
            if (!m.mousePressed(SkillListGui.mc, mouseX, mouseY)) {
                continue;
            }
            final Value v = this.module.valueList.get(m.id);
            v.action(m);
            m.displayString = v.getGui().displayString;
            if (!this.category.isSpecial()) {
                continue;
            }
            final float old = this.modulesOffset;
            this.updateCategory();
            this.modulesOffset = old;
        }
        ClientSettings.saveOptions();
    }
    
    protected void mouseReleased(final int mouseX, final int mouseY, final int state) {
        super.mouseReleased(mouseX, mouseY, state);
        for (final GuiButton m : SkillListGui.settingsList) {
            m.mouseReleased(mouseX, mouseY);
        }
        ClientSettings.saveOptions();
    }
    
    public void updateScreen() {
        if (this.needs_update) {
            this.updateCategory();
            this.updateModule();
            this.needs_update = false;
        }
        this.search.updateCursorCounter();
        for (final GuiButton m : SkillListGui.settingsList) {
            if (m instanceof GuiTextField) {
                ((GuiTextField)m).updateCursorCounter();
            }
        }
        this.modulesMotion /= 2.0f;
        this.modulesOffset += this.modulesMotion;
        this.modulesOffset = Math.min((float)(this.modulesList.size() * 25 - this.height + 50), this.modulesOffset);
        this.modulesOffset = Math.max(0.0f, this.modulesOffset);
        this.settingsMotion /= 2.0f;
        this.settingsOffset += this.settingsMotion;
        this.settingsOffset = Math.min((float)(SkillListGui.settingsList.size() * 25 - this.height + 50), this.settingsOffset);
        this.settingsOffset = Math.max(0.0f, this.settingsOffset);
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    public void updateCategory() {
        this.modulesList.clear();
        this.modules = new ArrayList<Module>();
        for (final Module m : this.category.getModules()) {
            if (this.isShown(m)) {
                this.modules.add(m);
            }
        }
        for (int i = 0; i < this.modules.size(); ++i) {
            this.modulesList.add(new GuiButton(i, 90, 40 + 25 * i, (this.width - 120) / 2, 20, this.modules.get(i).name));
        }
        this.modulesOffset = 0.0f;
    }
    
    public void updateModule() {
        SkillListGui.settingsList.clear();
        SkillListGui.settingsList.add(new GuiButton(-1, 370, 40, (this.width - 120) / 2, 20, this.module.name));
        for (int i = 0; i < this.module.valueList.size(); ++i) {
            final GuiButton gui = this.module.valueList.get(i).getGui();
            gui.id = i;
            gui.xPosition = 110 + (this.width - 120) / 2;
            gui.yPosition = 65 + i * 25;
            gui.width = (this.width - 120) / 2;
            if (!(gui instanceof SkillGuiCheckBox)) {
                gui.height = 20;
            }
            SkillListGui.settingsList.add(gui);
        }
        this.settingsOffset = 0.0f;
    }
    
    public boolean isShown(final Module module) {
        final String s = this.search.getText().toLowerCase();
        if (s.isEmpty()) {
            return true;
        }
        if (module.getDisplayName().toLowerCase().contains(s)) {
            return true;
        }
        if (module.name.toLowerCase().contains(s)) {
            return true;
        }
        for (final Value v : module.valueList) {
            if (v.getDisplayname().toLowerCase().contains(s)) {
                return true;
            }
        }
        return false;
    }
    
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        final int offset = -Mouse.getDWheel() / 8;
        if (Mouse.getEventX() / (float)SkillListGui.mc.displayWidth < 0.55f) {
            this.modulesMotion += offset;
        }
        else {
            this.settingsMotion += offset;
        }
    }
}

