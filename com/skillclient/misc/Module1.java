package com.skillclient.misc;

class Module$1 extends ValueBoolean {
    public Boolean getValue() {
        return Module.this.isToggled();
    }
    
    public void setValue(final Boolean value) {
        try {
            Module.this.toggle();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getDisplayname() {
        return Module.this.getDisplayName();
    }
    
    public String saveValue() {
        return "";
    }
}


