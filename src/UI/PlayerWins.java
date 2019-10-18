package UI;

import java.util.ArrayList;

public class PlayerWins extends UIObject {

    //Parameterized constructor
    public PlayerWins(
            String name,
            ArrayList<String> assetLocations,
            boolean isVisible,
            int xLocation,
            int yLocation,
            int xSize,
            int ySize
    ){
        //Initialize Ship as UIObject
        super(name, assetLocations, isVisible, xLocation, yLocation, xSize, ySize);
    }

    public void changeAssetSize(int xSize, int ySize){
        super.changeAssetSize(xSize, ySize);
    }

    @Override
    public void mousePressedAction() {
    }

    @Override
    public void mouseReleasedAction(){
    }

    @Override
    public void mouseListenerAction(){}

    @Override
    public void mouseEnteredAction(){}

    @Override
    public void mouseExitedAction(){}
}
