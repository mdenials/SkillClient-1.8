package com.skillclient.utils;

public class TimerUtil
{
    private long lastMS;
    
    public TimerUtil() {
        this.setLastMS();
    }
    
    public int convertToMS(final int perSecond) {
        return 1000 / perSecond;
    }
    
    public boolean everyDelay(final long delay) {
        if (System.currentTimeMillis() - this.lastMS >= delay) {
            this.lastMS = Math.min(System.currentTimeMillis(), this.lastMS + delay);
            return true;
        }
        return false;
    }
    
    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }
    
    public long getLastMS() {
        return this.lastMS;
    }
    
    public boolean hasReached(final float f) {
        return this.getCurrentMS() - this.lastMS >= f;
    }
    
    public boolean isDelayComplete(final long delay) {
        return System.currentTimeMillis() - this.lastMS >= delay;
    }
    
    public int pastTime() {
        return (int)(System.currentTimeMillis() - this.lastMS);
    }
    
    public void reset() {
        this.lastMS = this.getCurrentMS();
    }
    
    public void setLastMS() {
        this.lastMS = System.currentTimeMillis();
    }
    
    public void setLastMS(final long currentMS) {
        this.lastMS = currentMS;
    }
}

