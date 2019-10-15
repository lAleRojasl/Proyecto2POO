package UIButtons;
import UI.UIObject;

import java.util.ArrayList;

public abstract class Ability extends UIObject {
    private int maxCooldown;
    private int curCooldown;
    private boolean usable;

    //Parameterized constructor
    public Ability(
            String name,
            int maxCooldown,
            boolean usable,
            ArrayList<String> assetLocations,
            boolean isVisible,
            int xLocation,
            int yLocation,
            int xSize,
            int ySize
    ){
        //Initialize Ship as UIObject
        super(name, assetLocations, isVisible, xLocation, yLocation, xSize, ySize);
        //Initialize the Ability's logical attributes
        this.maxCooldown = maxCooldown;
        this.curCooldown = maxCooldown;
        this.usable = usable;

    }

    //-- Shared methods --
    //Reduces cooldown by 1
    public void reduceCooldown() {
        this.curCooldown--;
        if(this.curCooldown == 0){
            this.usable = true;
            this.curCooldown = maxCooldown;
        }
    }

    public void setIsUsable(boolean isUsable){
        this.usable = isUsable;
    }

    //We can activate the ability
    public boolean isUsable(){
        return usable;
    }

    //-- Abstract methods --
    //Use the ability, this starts the cooldown
    public abstract void activate();

    // -- Parent Method Implementation --
    //Custom mouse listener
    @Override
    public void mouseListenerAction(){
        if(this.usable){
            super.hideImage();
            //custom Activate function for each ability
            activate();
        }
    }

    @Override
    public void mouseEnteredAction(){
    }

    @Override
    public void mouseExitedAction(){
    }

    @Override
    public void mousePressedAction(){
    }

    @Override
    public void mouseReleasedAction(){
    }

}
