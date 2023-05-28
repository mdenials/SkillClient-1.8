package com.skillclient.gui.click;

import com.skillclient.misc.Value;
import java.util.Iterator;
import com.skillclient.gui.click.components.FrameButtonModule;
import com.skillclient.misc.Module;
import com.skillclient.gui.click.components.Frame;
import com.skillclient.gui.click.components.FrameTextField;
import com.skillclient.gui.click.components.FrameTree;

class SkillClickGui$3 extends FrameTree {
    FrameTextField text = new FrameTextField("Search", "", (FrameTree)this);
    
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        this.getSubFrames().clear();
        this.addFrame((Frame)this.text);
        int i = 0;
        for (final Module m : SkillClickGui$3.sc.moduleList) {
            if (this.isShown(m)) {
                if (i++ > 10) {
                    continue;
                }
                this.addFrame((Frame)new FrameButtonModule(m, (FrameTree)this));
            }
        }
        super.render(mouseX, mouseY, partialTicks);
    }
    
    public boolean isShown(final Module module) {
        if (module.getDisplayName().toLowerCase().contains(this.text.getFieldText())) {
            return true;
        }
        if (module.name.toLowerCase().contains(this.text.getFieldText())) {
            return true;
        }
        for (final Value v : module.valueList) {
            if (v.getDisplayname().toLowerCase().contains(this.text.getFieldText())) {
                return true;
            }
        }
        return false;
    }
}
