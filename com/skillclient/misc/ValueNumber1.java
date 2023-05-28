package com.skillclient.misc;

import com.skillclient.gui.utils.SkillGuiSlider;

class ValueNumber$1 extends SkillGuiSlider {
    public void mouseReleased(final int par1, final int par2) {
        super.mouseReleased(par1, par2);
        ((Value<Double, D>)ValueNumber.this).setValue(this.getValue());
        this.setValue((double)((Value<Double, D>)ValueNumber.this).getValue());
    }
}


