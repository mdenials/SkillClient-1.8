package com.skillclient.gui.click;

import org.lwjgl.input.Mouse;
import java.util.Collection;
import java.awt.Color;
import com.skillclient.utils.RenderGuiUtil;
import java.io.IOException;
import com.skillclient.gui.click.misc.KeyEvent;
import java.util.Iterator;
import com.skillclient.gui.click.components.FrameButtonModule;
import com.skillclient.main.Register;
import com.skillclient.gui.click.components.FrameTree;
import com.skillclient.misc.Module;
import java.util.HashMap;
import com.skillclient.gui.click.components.Frame;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreen;

public class SkillClickGui extends GuiScreen
{
    static ArrayList<Frame> frames;
    static ArrayList<Frame> framesToAdd;
    static ArrayList<Frame> framesToRemove;
    public static HashMap<Module, FrameTree> moduleFrames;
    public static Frame dragging;
    int oldMouseX;
    int oldMouseY;
    
    static {
        SkillClickGui.framesToAdd = new ArrayList<Frame>();
        SkillClickGui.framesToRemove = new ArrayList<Frame>();
        SkillClickGui.moduleFrames = new HashMap<Module, FrameTree>();
        SkillClickGui.dragging = null;
    }
    
    public SkillClickGui() {
        this.oldMouseY = 0;
    }
    
    public void initGui() {
        if (SkillClickGui.frames == null) {
            SkillClickGui.frames = new ArrayList<Frame>();
            SkillClickGui.moduleFrames.clear();
            int pos = 0;
            Register.Category[] normal;
            for (int length = (normal = Register.Category.normal).length, i = 0; i < length; ++i) {
                final Register.Category c = normal[i];
                final FrameTree tree = new FrameTree(c.getName(), 150, 10 + pos++ * 13);
                for (final Module m : c.getModules()) {
                    tree.addFrame((Frame)new FrameButtonModule(m, tree));
                }
                SkillClickGui.frames.add((Frame)tree);
            }
            SkillClickGui.frames.add((Frame)new SkillClickGui.SkillClickGui$1(this, "Active", 150, 10 + pos++ * 13));
            SkillClickGui.frames.add((Frame)new SkillClickGui.SkillClickGui$2(this, "Keycode", 150, 10 + pos++ * 13));
            SkillClickGui.frames.add((Frame)new SkillClickGui.SkillClickGui$3(this, "Search", 150, 10 + pos++ * 13));
            SkillClickGui.frames.add((Frame)new FrameSettings(150, 10 + pos++ * 13).init());
        }
    }
    
    public static void addFrame(final Frame frame) {
        SkillClickGui.framesToAdd.add(frame);
    }
    
    public static void removeFrame(final Frame frame) {
        SkillClickGui.framesToRemove.add(frame);
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        final KeyEvent event = new KeyEvent(typedChar, keyCode);
        for (final Frame frame : SkillClickGui.frames) {
            frame.keyTyped(event);
        }
        if (event.isCallSuper()) {
            super.keyTyped(typedChar, keyCode);
        }
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        SkillClickGui.dragging = null;
        for (final Frame frame : SkillClickGui.frames) {
            if (RenderGuiUtil.isHovered(mouseX, mouseY, frame)) {
                frame.click(mouseX, mouseY, mouseButton);
            }
            if (RenderGuiUtil.isDraggingHovered(mouseX, mouseY, frame)) {
                SkillClickGui.dragging = frame;
            }
        }
    }
    
    protected void mouseClickMove(final int mouseX, final int mouseY, final int clickedMouseButton, final long timeSinceLastClick) {
        for (final Frame frame : SkillClickGui.frames) {
            if (RenderGuiUtil.isHovered(mouseX, mouseY, frame)) {
                frame.click(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
            }
        }
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        if (SkillClickGui.mc.theWorld == null) {
            this.drawDefaultBackground();
        }
        final int i = 155;
        final int j = 50;
        final int c = new Color(j, j, j, i).getRGB();
        this.drawGradientRect(0, 0, this.width, this.height, c, c);
        if (!SkillClickGui.framesToAdd.isEmpty()) {
            SkillClickGui.frames.addAll(SkillClickGui.framesToAdd);
            SkillClickGui.framesToAdd.clear();
        }
        if (!SkillClickGui.framesToRemove.isEmpty()) {
            SkillClickGui.frames.removeAll(SkillClickGui.framesToRemove);
            SkillClickGui.framesToRemove.clear();
        }
        for (final Frame frame : SkillClickGui.frames) {
            frame.render(mouseX, mouseY, partialTicks);
            if (SkillClickGui.dragging == frame) {
                if (Mouse.isButtonDown(0)) {
                    frame.offSet(mouseX - this.oldMouseX, mouseY - this.oldMouseY);
                    if (RenderGuiUtil.isDraggingHovered(mouseX, mouseY, frame)) {
                        continue;
                    }
                    SkillClickGui.dragging = null;
                }
                else {
                    SkillClickGui.dragging = null;
                }
            }
        }
        this.oldMouseX = mouseX;
        this.oldMouseY = mouseY;
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
}

