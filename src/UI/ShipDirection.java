package UI;
import Entities.Hangar;
import Utility.Utility;
import java.util.ArrayList;

/**********************************************************************
 Instituto Tecnológico de Costa Rica
 Programación Orientada a Objetos
 II Semestre 2019
 Profesora: Samanta Ramijan Carmiol
 Estudiantes: Alejandro Rojas Jara, Jose Antonio Güell
 **********************************************************************/

public class ShipDirection extends UIObject {

    private Hangar shipHangar;

    //Parameterized constructor
    public ShipDirection(
            String name,
            ArrayList<String> assetLocations,
            boolean isVisible,
            int xLocation,
            int yLocation,
            int xSize,
            int ySize,
            Hangar shipHangar
    ){
        //Initialize Ship as UIObject
        super(name, assetLocations, isVisible, xLocation, yLocation, xSize, ySize);
        this.shipHangar = shipHangar;
    }

    @Override
    public void mousePressedAction() {
        if(!shipHangar.allShipsDeployed()){
            super.showImage(1);
            this.shipHangar.getShipToDeploy(Utility.currentPlayer).switchDirection();
        }
    }

    @Override
    public void mouseReleasedAction(){
        if(!shipHangar.allShipsDeployed())
            super.hideImage();
    }

    @Override
    public void mouseListenerAction(){}

    @Override
    public void mouseEnteredAction(){}

    @Override
    public void mouseExitedAction(){}
}
