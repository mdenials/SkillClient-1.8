package com.skillclient.gui.click.components;

import com.skillclient.misc.ValueArray;
import com.skillclient.misc.Value;

class FrameButtonModule$7 extends FrameToggleButton {
    private final /* synthetic */ Value val$value;
    private final /* synthetic */ int val$i_;
    
    public void click(final int mouseX, final int mouseY, final int mouseButton) {
        this.val$value.setValue(((ValueArray)this.val$value).array()[this.val$i_]);
    }
    
    public boolean isChecked() {
        return this.val$value.getValue().equals(((ValueArray)this.val$value).array()[this.val$i_]);
    }
}
