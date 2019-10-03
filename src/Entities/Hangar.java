package Entities;

import Utility.Utility;

import javax.swing.*;

public class Hangar {

    private static Ship[] p1HangarBay = new Ship[9];
    private static Ship[] p2HangarBay = new Ship[9];
    private int shipToDeploy = 0;
    private boolean allShipsDeployed = false;

    public Hangar(JPanel jPanel){
        //Build Wardogs
        p1HangarBay[0] = buildWardog(true, true, 350, 50);
        p2HangarBay[0] = buildWardog(true, true, 1050, 50);

        p1HangarBay[1] = buildWardog(true, false, 350, 50);
        p2HangarBay[1] = buildWardog(true, false, 350, 50);

        //Build VoidRays
        p1HangarBay[2] = buildVoidRay(true, false, 310, 45);
        p1HangarBay[3] = buildVoidRay(true, false, 310, 45);
        p1HangarBay[4] = buildVoidRay(true, false, 310, 45);

        p2HangarBay[2] = buildVoidRay(true, false, 310, 45);
        p2HangarBay[3] = buildVoidRay(true, false, 310, 45);
        p2HangarBay[4] = buildVoidRay(true, false, 310, 45);

        //Build Stalkers
        p1HangarBay[5] = buildStalker(true, false, 280, 45);
        p1HangarBay[6] = buildStalker(true, false, 280, 45);
        p1HangarBay[7] = buildStalker(true, false, 280, 45);

        p2HangarBay[5] = buildStalker(true, false, 280, 45);
        p2HangarBay[6] = buildStalker(true, false, 280, 45);
        p2HangarBay[7] = buildStalker(true, false, 280, 45);

        //Build Carrier
        p1HangarBay[8] = buildCarrier(true, false, 275, 15);

        p2HangarBay[8] = buildCarrier(true, false, 275, 15);

        //Add ships to UI panel
        for (Ship ship: p1HangarBay) {
            jPanel.add(ship.getAssetLabel());
        }

        for (Ship ship: p2HangarBay) {
            jPanel.add(ship.getAssetLabel());
        }
    }

    public void deployShip(){
        if(shipToDeploy < 8){
            shipToDeploy++;
            p1HangarBay[shipToDeploy].showImage(1);
        }
        else {
            allShipsDeployed = true;
            for (int i = 0; i < 9; i++) {
                p1HangarBay[i].hideImage();
            }
        }
    }

    public boolean shipWasCliked(){
        return p1HangarBay[shipToDeploy].wasClicked();
    }

    public void moveShip(int xLocation, int yLocation){
        p1HangarBay[shipToDeploy].changePosition(xLocation, yLocation);
    }

    public boolean allShipsDeployed(){
        return allShipsDeployed;
    }

    public Ship buildWardog(boolean horizontal, boolean isVisible, int xLocation, int yLocation){
      return new Ship("Wardog",1,1,horizontal, Utility.wardogPaths,isVisible, xLocation, yLocation,116,58, 0,0 );
    }

    public Ship buildVoidRay(boolean horizontal, boolean isVisible, int xLocation, int yLocation){
        return new Ship("VoidRay",1,1,horizontal, Utility.voidRayPaths,isVisible, xLocation, yLocation,88,41, 16, 12);
    }

    public Ship buildStalker(boolean horizontal, boolean isVisible, int xLocation, int yLocation){
        return new Ship("Stalker",1,1,horizontal, Utility.stalkerPaths,isVisible, xLocation, yLocation,137,53, 32, 0);
    }

    public Ship buildCarrier(boolean horizontal, boolean isVisible, int xLocation, int yLocation){
        return new Ship("Carrier",1,1,horizontal, Utility.carrierPaths,isVisible, xLocation, yLocation,158,76, 16, 24);
    }

}
