package com.skillclient.misc;

import com.skillclient.gui.utils.GuiButton;

public abstract class Value<V, D extends GuiButton> implements SCMC
{
    protected Module modul;
    protected String name;
    protected int case_;
    protected final Value.Type type;
    protected V value;
    
    public Value(final String name, final Module modul, final Value.Type type) {
        this.modul = modul;
        this.name = name;
        this.case_ = modul.getnewCase();
        this.type = type;
        this.modul.valueList.add(this);
    }
    
    public boolean isActive() {
        return true;
    }
    
    public boolean reloadScreen() {
        return true;
    }
    
    public abstract void action(final D p0);
    
    public abstract D getGui();
    
    @Override
    public String toString() {
        return "[" + this.type.toString() + "] " + this.name;
    }
    
    public String getDisplayname() {
        return String.valueOf(this.name) + ":" + this.value;
    }
    
    public boolean keyTyped(final D m, final char typedChar, final int keyCode) {
        return false;
    }
    
    public abstract String saveValue();
    
    public abstract void loadValue(final String p0);
    
    public Module getModul() {
        return this.modul;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getCase_() {
        return this.case_;
    }
    
    public Value.Type getType() {
        return this.type;
    }
    
    public V getValue() {
        return this.value;
    }
    
    public void setModul(final Module modul) {
        this.modul = modul;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setCase_(final int case_) {
        this.case_ = case_;
    }
    
    public void setValue(final V value) {
        this.value = value;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Value)) {
            return false;
        }
        final Value<?, ?> other = (Value<?, ?>)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$modul = this.getModul();
        final Object other$modul = other.getModul();
        Label_0065: {
            if (this$modul == null) {
                if (other$modul == null) {
                    break Label_0065;
                }
            }
            else if (this$modul.equals(other$modul)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        Label_0102: {
            if (this$name == null) {
                if (other$name == null) {
                    break Label_0102;
                }
            }
            else if (this$name.equals(other$name)) {
                break Label_0102;
            }
            return false;
        }
        if (this.getCase_() != other.getCase_()) {
            return false;
        }
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        Label_0152: {
            if (this$type == null) {
                if (other$type == null) {
                    break Label_0152;
                }
            }
            else if (this$type.equals(other$type)) {
                break Label_0152;
            }
            return false;
        }
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        if (this$value == null) {
            if (other$value == null) {
                return true;
            }
        }
        else if (this$value.equals(other$value)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof Value;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $modul = this.getModul();
        result = result * 59 + (($modul == null) ? 43 : $modul.hashCode());
        final Object $name = this.getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        result = result * 59 + this.getCase_();
        final Object $type = this.getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        final Object $value = this.getValue();
        result = result * 59 + (($value == null) ? 43 : $value.hashCode());
        return result;
    }
}

