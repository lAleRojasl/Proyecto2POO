package UIButtons;

import Utility.Utility;

public class ProtonCannon extends Ability {

    public ProtonCannon(){
        super(
                "Photon Canon",
                1,
                true,
                Utility.photonCanonPaths,
                true,
                680,
                176,
                92,
                92
        );
    }

    @Override
    public void activate() {
        System.out.println("Proton Cannon ACTIVATED!");
        super.setIsUsable(false);
    }
}
