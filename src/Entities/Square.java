package Entities;

import Game.BattleGrid;
import UI.UIObject;
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
    //Reference to the Ship object on this square
    private Ship shipOnSquare;
    //Reference to the Hangar, which handles the ship arrays for the players
    private Hangar shipHangar;
    //Reference to parent grid, which handles logical checks that involve multiple squares
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

    public void setShipOnSquare(Ship shipOnSquare){
        this.shipOnSquare = shipOnSquare;
    }

    public boolean isEmpty(){ if(this.shipOnSquare == null) return true; else return false;}

    public void handleShipPlacement() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (Utility.currentPlayer == this.playerNumber) {
            Ship shipToDeploy = shipHangar.getShipToDeploy(Utility.currentPlayer);
            //Make sure ship fits from this position.
            if (battleGrid.checkShipFits(this.rowIndex, this.columnIndex, shipToDeploy.getSize(), shipToDeploy.isHorizontal())) {
                //Deploy ship on this position
                shipHangar.deployShip(Utility.currentPlayer);
                //And all the adjacent squares where the ship will be (depending on it's size)
                battleGrid.placeShipOnSquares(this.rowIndex, this.columnIndex, shipToDeploy);

                //Player 1 done deploying all their ships
                if (shipHangar.getTotalShipsDeployed() == 9) {
                    //Player 1 done, hide ships
                    shipHangar.hideAllShips(Utility.currentPlayer);
                    Utility.switchCurrentPlayer();
                    battleGrid.switchPlayerHighlight(Utility.currentPlayer);
                    battleGrid.switchPlayerGrid();
                }

                //Player 2 done deploying all their ships
                if (shipHangar.allShipsDeployed()) {
                    //Player 2 done, hide ships
                    shipHangar.hideAllShips(Utility.currentPlayer);
                    //Back to Player 1 to start playing.
                    Utility.switchCurrentPlayer();
                    battleGrid.switchPlayerHighlight(Utility.currentPlayer);
                }
            }
        }
    }

    public void handlePlayerTurn() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (Utility.currentPlayer != this.playerNumber && state == 1) {
            //There's a ship on this square, its a Hit!
            if (!this.isEmpty()) {
                super.showImage(1);
                state = 2;
                shipOnSquare.takeDamage();

                //Ship was destroyed - check if it was the last one
                if (shipOnSquare.isDestroyed()) {
                    if (shipHangar.allShipsDestroyedForPlayer(Utility.currentPlayer)) {
                        battleGrid.finishGame(Utility.currentPlayer);
                    }
                }
            }

            //The square is empty, its a Miss!
            else {
                super.showImage(2);
                state = 3;
            }

            if(!shipHangar.allShipsDestroyedForPlayer(Utility.currentPlayer)){
                //Switch turn to other player
                Utility.switchCurrentPlayer();
                battleGrid.switchPlayerGrid();
                battleGrid.switchPlayerHighlight(Utility.currentPlayer);
            }
        }
    }


    // -- Parent Method Implementation --
    //Custom mouse listener - Activates whenever a player clicks on a Square on the Grid.
    @Override
    public void mouseListenerAction() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        //Game's over, disable any further interaction
        if(battleGrid.gameOver()) return;

        //Players still placing ships
        if (!shipHangar.allShipsDeployed()) {
            handleShipPlacement();
        }
        //All ships deployed, lets play.
        //Take turns clicking on each grid, until all ships have been destroyed
        else handlePlayerTurn();
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