package UI;

import Entities.Hangar;
import Entities.Ship;
import UI.UIObject;

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

    // Index notation constructor
    public Square(
            Hangar shipHangar,
            String name,
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
        this.shipHangar = shipHangar;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
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

    // -- Parent Method Implementation --
    //Custom mouse listener
    @Override
    public void mouseListenerAction(){
        if(!shipHangar.allShipsDeployed() && !shipHangar.shipWasCliked()){
            System.out.println("Deploying Ship at X-POS : " + super.getXPos() + " and  Y-POS: " + super.getYPos());
            shipHangar.deployShip();
        }
    }

    @Override
    public void mouseEnteredAction() {
        //System.out.println("Entered Square on X : " + rowIndex + " Y: " + columnIndex + " SHIP X POS: " + this.shipOnSquare.getXPos());
        if(!shipHangar.allShipsDeployed()){
            if(!shipHangar.shipWasCliked()){
                System.out.println("Temporarily moving ship to X-POS : " + super.getXPos() + " and  Y-POS: " + super.getYPos());
                shipHangar.moveShip(super.getXPos(), super.getYPos());
            }
            else{
                shipHangar.deployShip();
            }
        }
    }

    @Override
    public void mouseExitedAction() {
        //System.out.println("Exited Square on X : " + rowIndex + " Y: " + columnIndex);
    }

}