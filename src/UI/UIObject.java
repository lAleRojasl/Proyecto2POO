package UI;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**********************************************************************
 Instituto Tecnológico de Costa Rica
 Programación Orientada a Objetos
 II Semestre 2019
 Profesora: Samanta Ramijan Carmiol
 Estudiantes: Alejandro Rojas Jara, Jose Antonio Güell
 **********************************************************************/

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
                try {
                    mouseListenerAction();
                } catch (UnsupportedAudioFileException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                }
            }

            public void mouseEntered(MouseEvent e) {
                mouseEnteredAction();
            }

            public void mouseExited(MouseEvent e) {
                mouseExitedAction();
            }

            public void mousePressed(MouseEvent e){
                mousePressedAction();
            }

            public void mouseReleased(MouseEvent e){
                mouseReleasedAction();
            }
        });
    }

    protected void changeAssetPosition(int xPos, int yPos){
        this.assetLabel.setLocation(xPos, yPos);
    }

    protected void changeAssetSize(int xSize, int ySize){ this.assetLabel.setSize(xSize, ySize);}

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

    public static synchronized void playSound(final String url, boolean loopIt) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        InputStream in = new FileInputStream(url);
        InputStream bufferedIn = new BufferedInputStream(in);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        if(loopIt) clip.loop(5);
    }


    public JLabel getAssetLabel(){
        return assetLabel;
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

    public int getXSize(){ return this.assetLabel.getWidth(); }

    public int getYSize(){ return this.assetLabel.getHeight(); }

    //Custom listener for each UIObject
    public abstract void mouseListenerAction() throws UnsupportedAudioFileException, IOException, LineUnavailableException;

    //Custom listener for each UIObject
    public abstract void mouseEnteredAction();

    //Custom listener for each UIObject
    public abstract void mouseExitedAction();

    //Custom listener for each UIObject
    public abstract void mousePressedAction();

    //Custom listener for each UIObject
    public abstract void mouseReleasedAction();
}
