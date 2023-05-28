package com.skillclient.utils.exceptions;

import com.skillclient.misc.Module;

public class InvalidModuleException extends Exception
{
    public InvalidModuleException(final Class<? extends Module> module) {
        super("Invalid Module:" + module);
    }
}
