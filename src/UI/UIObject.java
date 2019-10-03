package UI;

import Utility.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class UIObject {

    /*UI Related Attributes*/
    private String name;
    private ArrayList<String> assetLocations;
    private ArrayList<ImageIcon> assetImages;
    private JLabel assetLabel;

    //Parameterized constructor
    public UIObject(
            String name,
            ArrayList<String> assetLocations,
            boolean isVisible,
            int xLocation,
            int yLocation,
            int xSize,
            int ySize
    ){
        this.name = name;
        this.assetLocations = assetLocations;
        //Create an ImageIcon for each supplied image location.
        this.assetImages = new ArrayList<>();
        this.setAssetImages(xSize, ySize);
        assetLabel = new JLabel();
        //Default label depends on whether image isVisible from the start
        if(isVisible)
            assetLabel.setIcon(assetImages.get(1));
        else
            assetLabel.setIcon(assetImages.get(0));
        assetLabel.setVisible(true);
        assetLabel.setLocation(xLocation,yLocation);
        assetLabel.setSize(xSize,ySize);

        assetLabel.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e) {
                mouseListenerAction();
            }

            public void mouseEntered(MouseEvent e) {
                mouseEnteredAction();
            }

            public void mouseExited(MouseEvent e) {
                mouseEnteredAction();
            }
        });
    }

    public String getName(){
        return this.name;
    }

    protected void changeAssetPosition(int xPos, int yPos){
        this.assetLabel.setLocation(xPos, yPos);
    }

    protected void setAssetImages(int xSize, int ySize){
        BufferedImage nullImage = new BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_ARGB);
        ImageIcon nullIcon = new ImageIcon(nullImage);
        //First one is always a null ImageIcon by default, which is used to "hide" the asset.
        assetImages.add(nullIcon);
        //Rest depend on supplied paths
        for (int i = 0; i < assetLocations.size(); i++) {
            assetImages.add(new ImageIcon(this.assetLocations.get(i)));
        }
    }

    public JLabel getAssetLabel(){
        return assetLabel;
    }

    public ImageIcon getAssetImage(int imagePos){
        return assetImages.get(imagePos);
    }

    public void hideImage(){
        this.assetLabel.setIcon(this.assetImages.get(0));
    }

    public void showImage(int imagePos){
        this.assetLabel.setIcon(this.assetImages.get(imagePos));
    }

    public int getXPos(){
        return this.assetLabel.getX();
    }

    public int getYPos(){
        return this.assetLabel.getY();
    }

    //Custom listener for each UIObject
    public abstract void mouseListenerAction();

    //Custom listener for each UIObject
    public abstract void mouseEnteredAction();

    //Custom listener for each UIObject
    public abstract void mouseExitedAction();
}
