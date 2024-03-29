package Game;
import Entities.Hangar;
import UI.PlayerHighlight;
import UI.PlayerWins;
import UI.ShipDirection;
import Utility.Utility;

import java.awt.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

/**********************************************************************
 Instituto Tecnológico de Costa Rica
 Programación Orientada a Objetos
 II Semestre 2019
 Profesora: Samanta Ramijan Carmiol
 Estudiantes: Alejandro Rojas Jara, Jose Antonio Güell
 **********************************************************************/

public class BattleVoid {

    //UI Buttons

    //Switch ship directions
    public ShipDirection shipDirection;
    //Main Game Grid and center of main logic
    public BattleGrid battleGrid;
    //Factory for all ships of the game
    public Hangar shipHangar;

    JFrame frame;

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        BattleVoid gui = new BattleVoid();
        gui.createUI();
    }

    public void createUI() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyDrawPanel drawPanel = new MyDrawPanel();
        drawPanel.setLayout(null);
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        frame.setSize(1465, 835);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    class MyDrawPanel extends JPanel {
        Image boardAsset;

        MyDrawPanel() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
            boardAsset = new ImageIcon(Utility.mapPath).getImage();

            //Static ships for each player
            shipHangar = new Hangar(this);

            //Create Battle Grid (Matrix of Squares) on the UI
            battleGrid = new BattleGrid(this, shipHangar);

            shipHangar.drawShips();

            //UI Buttons
            shipDirection = new ShipDirection("shipDirection", Utility.shipDirectionPaths, false, 221, 49, 53, 53, shipHangar);
            add(shipDirection.getAssetLabel());
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(boardAsset, 0, 0, this);
        }

    }
}
