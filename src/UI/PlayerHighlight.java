package UI;
import Entities.Hangar;
import Utility.Utility;

import java.util.ArrayList;

public class PlayerHighlight extends UIObject {

    //Parameterized constructor
    public PlayerHighlight(
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
