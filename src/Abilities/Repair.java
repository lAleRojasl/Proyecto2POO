package Abilities;

import Utility.Utility;

public class Repair extends Ability {

    public Repair(){
        super(
                "Repair",
                1,
                true,
                Utility.repairPaths,
                true,
                679,
                497,
                92,
                92
        );
    }

    @Override
    public void activate() {
        System.out.println("Repair ACTIVATED!");
        super.setIsUsable(false);
    }
}
