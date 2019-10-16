package Game;
import Entities.Hangar;
import UI.ShipDirection;
import Utility.Utility;

import java.awt.*;
import javax.swing.*;

public class BattleVoid {

    //UI Buttons

    //Switch ship directions
    public ShipDirection shipDirection;

    public BattleGrid battleGrid;
    public Hangar shipHangar;

    JFrame frame;

    public static void main(String[] args) {
        BattleVoid gui = new BattleVoid();
        gui.createUI();
    }

    public void createUI() {
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

        MyDrawPanel() {
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
