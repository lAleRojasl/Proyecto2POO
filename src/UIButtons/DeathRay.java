package UIButtons;

import Utility.Utility;

public class DeathRay extends Ability {

    public DeathRay(){
        super(
                "Death Ray",
                3,
                true,
                Utility.deathRayPaths,
                true,
                680,
                340,
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
