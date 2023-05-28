package com.skillclient.misc;

class Module$2 extends ValueKey {
    public void setValue(final Integer value) {
        Module.this.setKeyCode(value);
        super.setValue((Object)Module.this.getKeyCode());
    }
    
    public Integer getValue() {
        return Module.this.getKeyCode();
    }
    
    public String saveValue() {
        return "";
    }
}
