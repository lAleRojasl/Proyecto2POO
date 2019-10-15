package Entities;

import Utility.Utility;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class Hangar {

    private static Ship[] p1HangarBay = new Ship[9];
    private static Ship[] p2HangarBay = new Ship[9];
    private int shipToDeploy = 0;
    private int totalShipsDeployed = 0;
    private JPanel jPanel;

    public Hangar(JPanel jPanel){
        //Build Wardogs
        p1HangarBay[0] = buildWardog(true, true, 350, 50);
        p1HangarBay[1] = buildWardog(true, false, 30, 50);

        p2HangarBay[0] = buildWardog(true, true, 1050, 50);
        p2HangarBay[1] = buildWardog(true, false, 1050, 50);

        //Build VoidRays
        p1HangarBay[2] = buildVoidRay(true, false, 310, 45);
        p1HangarBay[3] = buildVoidRay(true, false, 310, 45);
        p1HangarBay[4] = buildVoidRay(true, false, 310, 45);

        p2HangarBay[2] = buildVoidRay(true, false, 1010, 45);
        p2HangarBay[3] = buildVoidRay(true, false, 1010, 45);
        p2HangarBay[4] = buildVoidRay(true, false, 1010, 45);

        //Build Stalkers
        p1HangarBay[5] = buildStalker(true, false, 280, 45);
        p1HangarBay[6] = buildStalker(true, false, 280, 45);
        p1HangarBay[7] = buildStalker(true, false, 280, 45);

        p2HangarBay[5] = buildStalker(true, false, 980, 45);
        p2HangarBay[6] = buildStalker(true, false, 980, 45);
        p2HangarBay[7] = buildStalker(true, false, 980, 45);

        //Build Carrier
        p1HangarBay[8] = buildCarrier(true, false, 275, 15);

        p2HangarBay[8] = buildCarrier(true, false, 1075, 15);

        //Reference to main panel
        this.jPanel = jPanel;

    }

    public void drawShips(){
        //Add ships to UI panel
        for (Ship ship: p1HangarBay) {
            jPanel.add(ship.getAssetLabel());
        }

        for (Ship ship: p2HangarBay) {
            jPanel.add(ship.getAssetLabel());
        }
    }

    public void deployShip(int playerNum) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if(totalShipsDeployed <= 18){
            shipToDeploy++;
            totalShipsDeployed++;
            if(shipToDeploy == 9) shipToDeploy = 0;
            if(playerNum == 1){
                p1HangarBay[shipToDeploy].showImage(1);
                p1HangarBay[shipToDeploy].playSound();
            }
            else{
                p2HangarBay[shipToDeploy].showImage(1);
            }
        }
        else {
            shipToDeploy = 0;
            //hideAllShips(playerNum);
        }
    }

    public void hideAllShips(int playerNum){
        if(playerNum == 1){
            for (int i = 0; i < 9; i++) {
                p1HangarBay[i].hideImage();
            }
        }
        else if(playerNum == 2) {
            for (int i = 0; i < 9; i++) {
                p2HangarBay[i].hideImage();
            }
        }
    }

    public int getTotalShipsDeployed(){
        return this.totalShipsDeployed;
    }

    public Ship getShipToDeploy(int playerNum) {
        if(playerNum == 1){
            return p1HangarBay[shipToDeploy];
        }
        else if(playerNum == 2) {
            return p2HangarBay[shipToDeploy];
        }
        else return null;
    }

    public boolean allShipsDeployed(){
        return totalShipsDeployed == 18;
    }



    public Ship buildWardog(boolean horizontal, boolean isVisible, int xLocation, int yLocation){
      return new Ship("Wardog",1, horizontal, Utility.wardogPaths,isVisible, xLocation, yLocation,59,59, 0,0 );
    }

    public Ship buildVoidRay(boolean horizontal, boolean isVisible, int xLocation, int yLocation){
        return new Ship("VoidRay",2,horizontal, Utility.voidRayPaths,isVisible, xLocation, yLocation,88,41, 16, 12);
    }

    public Ship buildStalker(boolean horizontal, boolean isVisible, int xLocation, int yLocation){
        return new Ship("Stalker",3, horizontal, Utility.stalkerPaths,isVisible, xLocation, yLocation,192,65, 0, 0);
    }

    public Ship buildCarrier(boolean horizontal, boolean isVisible, int xLocation, int yLocation){
        return new Ship("Carrier",4, horizontal, Utility.carrierPaths,isVisible, xLocation, yLocation,258,65, 0, 0);
    }

}
