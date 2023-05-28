package com.skillclient.events;

import com.skillclient.utils.Rotation;
import com.skillclient.events.api.events.EventStoppable;

public class EventRotation extends EventStoppable
{
    private float yaw;
    private float pitch;
    private final boolean isPacket;
    
    public EventRotation(final float pitch, final float yaw, final boolean packet) {
        this.pitch = pitch;
        this.yaw = yaw;
        this.isPacket = packet;
    }
    
    public float getPitch() {
        if (Float.isNaN(this.pitch)) {
            this.pitch = 0.0f;
            System.err.println("Pitch was NaN");
        }
        return clampValue(this.pitch, -90.0f, 90.0f);
    }
    
    public static float clampValue(final float value, final float floor, final float cap) {
        if (value < floor) {
            return floor;
        }
        if (value > cap) {
            return cap;
        }
        return value;
    }
    
    public float getYaw() {
        if (Float.isNaN(this.yaw)) {
            this.yaw = 0.0f;
            System.err.println("Yaw was NaN");
        }
        return this.yaw;
    }
    
    public void setRotation(final Rotation rot) {
        this.setPitch(rot.getPitch());
        this.setYaw(rot.getYaw());
    }
    
    public boolean isPacket() {
        return this.isPacket;
    }
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
    
    public String toString() {
        return "EventRotation(yaw=" + this.getYaw() + ", pitch=" + this.getPitch() + ", isPacket=" + this.isPacket() + ")";
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EventRotation)) {
            return false;
        }
        final EventRotation other = (EventRotation)o;
        return other.canEqual(this) && Float.compare(this.getYaw(), other.getYaw()) == 0 && Float.compare(this.getPitch(), other.getPitch()) == 0 && this.isPacket() == other.isPacket();
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof EventRotation;
    }
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.getYaw());
        result = result * 59 + Float.floatToIntBits(this.getPitch());
        result = result * 59 + (this.isPacket() ? 79 : 97);
        return result;
    }
}

