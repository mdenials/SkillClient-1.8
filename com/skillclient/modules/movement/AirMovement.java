package com.skillclient.modules.movement;

import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class AirMovement extends Module
{
    public AirMovement() {
        super("AirMovement", Register.Category.MOVEMENT, "When you sneak in air you instantly stop moving");
    }
}

