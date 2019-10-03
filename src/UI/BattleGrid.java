package UI;

import Entities.Hangar;
import Entities.Ship;
import Utility.Utility;

import javax.swing.*;

public class BattleGrid {
    private Square[][] playerGrid = new Square[10][10];
    private Square[][] enemyGrid = new Square[10][10];
    private Hangar shipHangar;

    public BattleGrid(JPanel jPanel, Hangar shipHangar) {
        int newXPos = 23;
        int newYPos = 123;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Square sqr = new Square(shipHangar,"Square", i, j, Utility.squarePaths,false, newXPos, newYPos,65,65);
                playerGrid[i][j] = sqr;
                jPanel.add(sqr.getAssetLabel());
                newYPos += 65;
                if(j == 9) newYPos = 123;
            }
            newXPos += 64;
            if(i == 9) newXPos = 23;
        }
        this.shipHangar = shipHangar;
    }

    public void shotAt(Square sqr, boolean hit, char initial) {

    }

    public boolean hit(Square sqr) {
        return false;
    }

    public boolean miss(Square sqr) {
        return false;
    }

    public boolean empty(Square sqr) {
        return false;
    }

}
