package com.skillclient.gui.click.components;

class FrameButtonModule$2 extends FrameToggleButton {
    public void click(final int mouseX, final int mouseY, final int mouseButton) {
        FrameButtonModule.access$0(FrameButtonModule.this).toggle();
        this.setChecked(FrameButtonModule.access$0(FrameButtonModule.this).isToggled());
    }
    
    public String getText() {
        return this.isChecked() ? "disable" : "enable";
    }
    
    public boolean isChecked() {
        return FrameButtonModule.access$0(FrameButtonModule.this).isToggled();
    }
}
