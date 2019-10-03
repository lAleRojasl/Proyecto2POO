package Entities;

import UI.UIObject;
import java.util.ArrayList;

public class Ship extends UIObject {

    /*Logic Related Attributes*/
    private int size;
    private int currentHits;
    private boolean horizontal;
    private int xPosOffset = 0;
    private int yPosOffset = 0;
    private boolean wasClicked = false;

    //Parameterized constructor
    public Ship(
                String name,
                int size,
                int currentHits,
                boolean horizontal,
                ArrayList<String> assetLocations,
                boolean isVisible,
                int xLocation,
                int yLocation,
                int xSize,
                int ySize,
                int xPosOffset,
                int yPosOffset
    ){
        //Initialize Ship as UIObject
        super(name, assetLocations, isVisible, xLocation + xPosOffset, yLocation + yPosOffset, xSize, ySize);
        //Initialize the Ship's logical attributes
        this.size = size;
        this.currentHits = currentHits;
        this.horizontal = horizontal;
        this.xPosOffset = xPosOffset;
        this.yPosOffset = yPosOffset;
    }

    public void changePosition(int xPos, int yPos){
        super.changeAssetPosition(xPos + xPosOffset, yPos + yPosOffset);
    }

    public boolean wasClicked(){
        return wasClicked;
    }

    public void switchDirection(){
        this.horizontal = !this.horizontal;
    }

    // -- Parent Method Implementation --
    //Custom mouse listener
    @Override
    public void mouseListenerAction(){
        //System.out.println("Clicking on Ship! X POS : " + super.getXPos() + " Y POS: " + super.getYPos());
        this.wasClicked = true;
        //super.showImage(1);
    }

    @Override
    public void mouseEnteredAction() {

    }

    @Override
    public void mouseExitedAction() {

    }
}
