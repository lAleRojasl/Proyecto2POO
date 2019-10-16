package Entities;

import UI.UIObject;
import Utility.Utility;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;

public class Ship extends UIObject {

    /*Logic Related Attributes*/
    private int size;
    private int hitPoints;
    private boolean isDestroyed;
    private boolean horizontal;
    private int xPosOffset = 0;
    private int yPosOffset = 0;

    //Parameterized constructor
    public Ship(
                String name,
                int size,
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
        this.hitPoints = size;
        this.horizontal = horizontal;
        this.xPosOffset = xPosOffset;
        this.yPosOffset = yPosOffset;
    }

    public void changePosition(int xPos, int yPos){
        super.changeAssetPosition(xPos + xPosOffset, yPos + yPosOffset);
    }

    public void switchDirection(){
        this.horizontal = !this.horizontal;
        if(this.horizontal){
            super.showImage(1);
            //super.changeAssetPosition(super.getXPos() + xPosOffset, super.getYPos() + yPosOffset);
        }
        else{
            super.showImage(2);
            //super.changeAssetPosition(super.getXPos() - xPosOffset, super.getYPos() - yPosOffset);
        }
        super.changeAssetSize(super.getYSize(), super.getXSize());
    }

    public boolean isHorizontal(){
        return this.horizontal;
    }

    public int getSize(){
        return this.size;
    }

    public void takeDamage(){
        this.hitPoints -= 1;
        if(this.hitPoints == 0) this.isDestroyed = true;
    }

    public boolean isDestroyed(){
        return this.isDestroyed;
    }

    public void repairDamage(){
        this.hitPoints++;
    }

    public void playSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        System.out.print("PLAYING SOUND?");
        if(this.size == 1){
            super.playSound(Utility.deploySFXPaths + "wardog.wav");
        }
        if(this.size == 2 || this.size == 3){
            super.playSound(Utility.deploySFXPaths + "voidray.wav");
        }
        if(this.size == 4){
            super.playSound(Utility.deploySFXPaths + "carrier.wav");
        }
    }

    // -- Parent Method Implementation --
    //Custom mouse listener
    @Override
    public void mouseListenerAction(){}

    @Override
    public void mouseEnteredAction(){}

    @Override
    public void mouseExitedAction(){}

    @Override
    public void mousePressedAction(){}

    @Override
    public void mouseReleasedAction(){}
}
