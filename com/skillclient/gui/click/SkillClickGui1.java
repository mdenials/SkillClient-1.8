package com.skillclient.gui.click;

import java.util.Iterator;
import com.skillclient.gui.click.components.Frame;
import com.skillclient.gui.click.components.FrameButtonModule;
import com.skillclient.misc.Module;
import com.skillclient.gui.click.components.FrameTree;

class SkillClickGui$1 extends FrameTree {
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        this.getSubFrames().clear();
        for (final Module m : SkillClickGui$1.sc.moduleList) {
            if (m.isToggled() && !m.invisible) {
                this.addFrame((Frame)new FrameButtonModule(m, (FrameTree)this));
            }
        }
        super.render(mouseX, mouseY, partialTicks);
    }
}
