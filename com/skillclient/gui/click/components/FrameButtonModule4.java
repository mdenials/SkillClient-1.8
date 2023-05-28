package com.skillclient.gui.click.components;

import com.skillclient.misc.Value;

class FrameButtonModule$4 extends FrameToggleButton {
    private final /* synthetic */ Value val$value;
    
    public void setChecked(final boolean isChecked) {
        super.setChecked(isChecked);
        this.val$value.setValue(isChecked);
    }
}
