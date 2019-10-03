package Abilities;

import Utility.Utility;

public class Shield extends Ability {

    public Shield(){
        super(
                "Shield",
                1,
                true,
                Utility.shieldPaths,
                true,
                678,
                661,
                92,
                92
        );
    }

    @Override
    public void activate() {
        System.out.println("Shield ACTIVATED!");
        super.setIsUsable(false);
    }
}
