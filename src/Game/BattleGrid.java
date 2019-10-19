package Game;

import Entities.Hangar;
import Entities.Ship;
import Entities.Square;
import UI.PlayerHighlight;
import UI.PlayerWins;
import Utility.Utility;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

/**********************************************************************
 Instituto Tecnológico de Costa Rica
 Programación Orientada a Objetos
 II Semestre 2019
 Profesora: Samanta Ramijan Carmiol
 Estudiantes: Alejandro Rojas Jara, Jose Antonio Güell
 **********************************************************************/

public class BattleGrid {
    private Square[][] player1Grid = new Square[10][10];
    private Square[][] player2Grid = new Square[10][10];
    private Square[][] activePlayerGrid;
    private PlayerHighlight player1Highlight;
    private PlayerHighlight player2Highlight;
    private PlayerWins playerWins;
    private boolean gameOver = false;

    public BattleGrid(JPanel jPanel, Hangar shipHangar) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        //Shows on the UI when a player wins
        playerWins = new PlayerWins("Player Won", Utility.playerWinsPaths, false, 540, 191, 1, 1);
        jPanel.add(playerWins.getAssetLabel());

        //Main Grids
        int newXPos = 26;
        int newYPos = 123;
        int p2Offset = 762;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Square sqrP1 = new Square(this, shipHangar, "Square", 1, j, i, Utility.squarePaths,false, newXPos, newYPos,65,65);
                Square sqrP2 = new Square(this, shipHangar, "Square", 2, j, i, Utility.squarePaths,false, newXPos + p2Offset, newYPos,65,65);
                player1Grid[i][j] = sqrP1;
                player2Grid[i][j] = sqrP2;
                jPanel.add(sqrP1.getAssetLabel());
                jPanel.add(sqrP2.getAssetLabel());
                newYPos += 65;
                if(j == 9) newYPos = 123;
            }
            newXPos += 64;
            if(i == 9) newXPos = 26;
        }
        //For simplicity we will use a reference to the grids to switch between them.
        activePlayerGrid = player1Grid;
        //Shows on the UI which player is currently playing
        player1Highlight = new PlayerHighlight("Player1Highlight", Utility.player1HighlightPaths, true, 46, 61, 135, 24);
        player2Highlight = new PlayerHighlight("Player2Highlight", Utility.player2HighlightPaths, false, 1271, 62, 143, 24);
        jPanel.add(player1Highlight.getAssetLabel());
        jPanel.add(player2Highlight.getAssetLabel());
    }

    public boolean checkShipFits(int rowIndex, int columnIndex, int size, boolean horizontal){
        int compareIndex = rowIndex;
        if(horizontal) compareIndex = columnIndex;
        //Check that ship doesn't exceed grid boundaries
        if(10 - compareIndex >= size){
            //Check that there aren't any other ship on the spaces
            for (int i = 0; i < size; i++) {
                //Found a ship on one of the nearby spaces, cant place
                if(horizontal){
                    if(!this.activePlayerGrid[columnIndex+i][rowIndex].isEmpty()){
                        return false;
                    }
                }
                else{
                    if(!this.activePlayerGrid[columnIndex][rowIndex+i].isEmpty()){
                        return false;
                    }
                }
            }
            return true;
        }
        else return false;
    }

    public void placeShipOnSquares(int rowIndex, int columnIndex, Ship deployedShip){
        for (int i = 0; i < deployedShip.getSize(); i++) {
            if(deployedShip.isHorizontal())
                this.activePlayerGrid[columnIndex+i][rowIndex].setShipOnSquare(deployedShip);
            else
                this.activePlayerGrid[columnIndex][rowIndex+i].setShipOnSquare(deployedShip);
        }
    }

    public void switchPlayerGrid(){
        if(this.activePlayerGrid == player1Grid)
            this.activePlayerGrid = player2Grid;
        else
            this.activePlayerGrid = player1Grid;
    }

    public void switchPlayerHighlight(int playerNum){
        if(playerNum == 1){
            player2Highlight.hideImage();
            player1Highlight.showImage(1);
        }
        else{
            player1Highlight.hideImage();
            player2Highlight.showImage(1);
        }
    }

    public boolean gameOver(){
        return this.gameOver;
    }

    public void finishGame(int winner){
        this.gameOver = true;
        //Display Winner message
        playerWins.changeAssetSize(375, 475);
        playerWins.showImage(winner);
    }

}
