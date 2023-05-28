package com.skillclient.gui.click.components;

import java.util.Iterator;
import com.skillclient.misc.ValueArray;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Value;
import com.skillclient.gui.click.SkillClickGui;
import com.skillclient.utils.RenderGuiUtil;
import com.skillclient.utils.RenderUtils;
import java.awt.Color;
import com.skillclient.misc.Module;

public class FrameButtonModule extends Frame
{
    private Module modul;
    
    public FrameButtonModule(final Module modul, final FrameTree parent) {
        super(modul.name, parent);
        this.modul = modul;
    }
    
    @Override
    public int getOwnWidth() {
        return super.getOwnWidth() + 10;
    }
    
    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawSquare(this.getPosX(), this.getPosX() + this.getWidth(), this.getPosY(), this.getPosY() + this.getHeight());
        this.drawSquare(this.getPosX() + 2, this.getPosX() + 9, this.getPosY() + 2, this.getPosY() + 9);
        if (this.modul.isToggled()) {
            RenderUtils.drawLine(this.getPosX() + 5, this.getPosY() + 10, this.getPosX() + 10, this.getPosY() + 1, Color.green);
            RenderUtils.drawLine(this.getPosX() + 2, this.getPosY() + 5, this.getPosX() + 5, this.getPosY() + 10, Color.green);
        }
        FrameButtonModule.mc.fontRendererObj.drawString(this.getText(), this.getPosX() + 12, this.getPosY() + 2, RenderGuiUtil.isHovered(mouseX, mouseY, this) ? Color.green.getRGB() : -1);
    }
    
    @Override
    public void click(final int mouseX, final int mouseY, final int mouseButton) {
        if (mouseButton == 0) {
            this.modul.toggle();
        }
        else {
            FrameTree frame = SkillClickGui.moduleFrames.get(this.modul);
            if (frame == null) {
                frame = (FrameTree)new FrameButtonModule.FrameButtonModule$1(this, this.modul.name);
                frame.addFrame((Frame)new FrameButtonModule.FrameButtonModule$2(this, "", this.modul.isToggled(), frame));
                frame.addFrame((Frame)new FrameButtonModule.FrameButtonModule$3(this, "bind", this.modul.getKeyCode(), frame));
                for (final Value value : this.modul.valueList) {
                    switch (value.getType()) {
                        default: {
                            continue;
                        }
                        case BOOLEAN: {
                            frame.addFrame((Frame)new FrameButtonModule.FrameButtonModule$4(this, value.getName(), (boolean)value.getValue(), frame, value));
                            continue;
                        }
                        case NUMBER: {
                            frame.addFrame((Frame)new FrameButtonModule.FrameButtonModule$5(this, value.getName(), frame, (double)value.getValue(), ((ValueNumber)value).getMin(), ((ValueNumber)value).getMax(), ((ValueNumber)value).getRound(), ((ValueNumber)value).getPostFix(), value));
                            continue;
                        }
                        case TEXT: {
                            frame.addFrame((Frame)new FrameButtonModule.FrameButtonModule$6(this, value.getName(), (String)value.getValue(), frame, value));
                            continue;
                        }
                        case ARRAY: {
                            final FrameTree tree = new FrameTree(value.getName());
                            for (int i = 0; i < ((ValueArray)value).array().length; ++i) {
                                final int i_ = i;
                                tree.addFrame((Frame)new FrameButtonModule.FrameButtonModule$7(this, ((ValueArray)value).array()[i].getDisplayname(), false, tree, value, i_));
                            }
                            frame.addFrame((Frame)tree);
                            continue;
                        }
                    }
                }
            }
            frame.setPosX(this.getPosX() + this.getWidth() + 4);
            frame.setPosY(this.getPosY());
            if (!SkillClickGui.moduleFrames.containsKey(this.modul)) {
                SkillClickGui.moduleFrames.put(this.modul, frame);
                SkillClickGui.addFrame((Frame)frame);
            }
        }
    }
    
    public Module getModul() {
        return this.modul;
    }
    
    public void setModul(final Module modul) {
        this.modul = modul;
    }
    
    @Override
    public String toString() {
        return "FrameButtonModule(modul=" + this.getModul() + ")";
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FrameButtonModule)) {
            return false;
        }
        final FrameButtonModule other = (FrameButtonModule)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$modul = this.getModul();
        final Object other$modul = other.getModul();
        if (this$modul == null) {
            if (other$modul == null) {
                return true;
            }
        }
        else if (this$modul.equals(other$modul)) {
            return true;
        }
        return false;
    }
    
    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof FrameButtonModule;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $modul = this.getModul();
        result = result * 59 + (($modul == null) ? 43 : $modul.hashCode());
        return result;
    }
}

