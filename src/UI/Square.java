package UI;

import Entities.Hangar;
import Entities.Ship;
import Utility.Utility;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;

/*
    The grid will be a matrix of logical "Squares".
    Each will have a logical row and column Index to represent it.
    It will also have X and Y positions to represent the location on the UI
 */
public class Square extends UIObject {
    // Declare variables
    private int rowIndex;
    private int columnIndex;
    private Ship shipOnSquare;
    private Hangar shipHangar;
    //Reference to parent grid
    private BattleGrid battleGrid;
    //1 - Inactive, 2 - Hit, 3 - Miss
    private int state;
    private int playerNumber;
    private boolean lastUsedSquare = false;

    // Index notation constructor
    public Square(
            BattleGrid battleGrid,
            Hangar shipHangar,
            String name,
            int playerNumber,
            int rowIndex,
            int columnIndex,
            ArrayList<String> assetLocations,
            boolean isVisible,
            int xLocation,
            int yLocation,
            int xSize,
            int ySize) {
        //Initialize Square as UIObject
        super(name, assetLocations, isVisible, xLocation, yLocation, xSize, ySize);
        //Initialize the Square's logical attributes
        this.battleGrid = battleGrid;
        this.shipHangar = shipHangar;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.playerNumber = playerNumber;
        this.state = 1;
    }

    // Return values
    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setShipOnSquare(Ship shipOnSquare){
        this.shipOnSquare = shipOnSquare;
    }

    public Ship getShipOnSquare(){
        return this.shipOnSquare;
    }

    public boolean isEmpty(){ if(this.shipOnSquare == null) return true; else return false;}

    // -- Parent Method Implementation --
    //Custom mouse listener
    @Override
    public void mouseListenerAction() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (Utility.currentPlayer == this.playerNumber && state == 1) {
            //Players still placing ships
            if (!shipHangar.allShipsDeployed()) {
                Ship shipToDeploy = shipHangar.getShipToDeploy(Utility.currentPlayer);
                //Make sure ship fits from this position.
                if (battleGrid.checkShipFits(this.rowIndex, this.columnIndex, shipToDeploy.getSize(), shipToDeploy.isHorizontal())) {
                    System.out.println("Deploying Ship at X-POS : " + super.getXPos() + " and  Y-POS: " + super.getYPos());
                    shipHangar.deployShip(Utility.currentPlayer);
                    battleGrid.placeShipOnSquares(this.rowIndex, this.columnIndex, shipToDeploy);
                    //Done deploying all Player1's ships
                    if (shipHangar.getTotalShipsDeployed() == 9) {
                        Utility.currentPlayer = 2;
                        //All ships deployed, lets play, back to Player 1.
                        if(shipHangar.allShipsDeployed()) Utility.currentPlayer = 1;
                        //No need to switch when game starts, since Player 1 "plays" on enemie's Grid.
                        else battleGrid.switchPlayerGrid();
                    }
                }
            }
            //All ships deployed, lets play.
            else{
                if (!this.isEmpty()) {
                    super.showImage(1);
                    //Hit!
                    state = 2;
                    shipOnSquare.takeDamage();
                } else {
                    super.showImage(2);
                    //Miss!
                    state = 3;
                }
                Utility.switchCurrentPlayer();
                battleGrid.switchPlayerGrid();
            }
        }
    }

    @Override
    public void mouseEnteredAction(){
        if(Utility.currentPlayer == this.playerNumber && state == 1){
            if(!shipHangar.allShipsDeployed()){
                Ship shipToMove = shipHangar.getShipToDeploy(Utility.currentPlayer);
                //Make sure the ship fits on the battleGrid.
                if(battleGrid.checkShipFits(this.rowIndex, this.columnIndex, shipToMove.getSize(), shipToMove.isHorizontal())){
                    //System.out.println("Temporarily moving ship to X-POS : " + super.getXPos() + " and  Y-POS: " + super.getYPos());
                    shipToMove.changePosition(super.getXPos(), super.getYPos());
                    this.lastUsedSquare = true;
                }
            }
        }
    }

    @Override
    public void mouseExitedAction() {}

    @Override
    public void mousePressedAction() {}

    @Override
    public void mouseReleasedAction() {}

}