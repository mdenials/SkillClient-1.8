package com.skillclient.events.api;

import java.lang.reflect.Method;

private static final class MethodData
{
    private final Object source;
    private final Method target;
    private final byte priority;
    
    public MethodData(final Object source, final Method target, final byte priority) {
        this.source = source;
        this.target = target;
        this.priority = priority;
    }
    
    public Object getSource() {
        return this.source;
    }
    
    public Method getTarget() {
        return this.target;
    }
    
    public byte getPriority() {
        return this.priority;
    }
}


