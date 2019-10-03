package Abilities;

import Utility.Utility;
import jdk.jshell.execution.Util;

import java.util.ArrayList;

public class DeathRay extends Ability {

    public DeathRay(){
        super(
                "Death Ray",
                3,
                true,
                Utility.deathRayPaths,
                true,
                680,
                176,
                92,
                92
        );
    }

    @Override
    public void activate() {
        System.out.println("Death Ray ACTIVATED!");
        super.setIsUsable(false);
    }

    @Override
    public void mouseEnteredAction() {

    }

    @Override
    public void mouseExitedAction() {

    }
}
