package Utility;

import Entities.Ship;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Utility {
    public static int squareLen = 65;

    public static int currentPlayer = 1;

    //SFX locations
    public static String deploySFXPaths = "assets\\sound\\deploy\\";
    public static String destroyedSFXPaths = "assets\\sound\\destroyed.wav";
    public static String hitSFXPaths = "assets\\sound\\hit.wav";
    public static String mapPath = "assets\\UI\\MapS.png";

    // Ships locations
    public static ArrayList<String> wardogPaths = new ArrayList<>() {
        {
            add("assets\\UI\\ships\\wardog.png");
            add("assets\\UI\\ships\\wardogV.png");
        }
    };
    public static ArrayList<String> voidRayPaths = new ArrayList<>() {
        {
            add("assets\\UI\\ships\\voidray.png");
            add("assets\\UI\\ships\\voidrayV.png");
        }
    };
    public static ArrayList<String> stalkerPaths = new ArrayList<>() {
        {
            add("assets\\UI\\ships\\stalker.png");
            add("assets\\UI\\ships\\stalkerV.png");
        }
    };
    public static ArrayList<String> carrierPaths = new ArrayList<>() {
        {
            add("assets\\UI\\ships\\carrier.png");
            add("assets\\UI\\ships\\carrierV.png");
        }
    };

    //UI Buttons Locations
    public static ArrayList<String> shipDirectionPaths = new ArrayList<>() {
        { add("assets\\UI\\DirectionActive.png"); }
    };

    public static ArrayList<String> player1HighlightPaths = new ArrayList<>() {
        {
            add("assets\\UI\\menu\\P1Highlight.png");
        }
    };

    public static ArrayList<String> player2HighlightPaths = new ArrayList<>() {
        {
            add("assets\\UI\\menu\\P2Highlight.png");
        }
    };

    public static ArrayList<String> playerWinsPaths = new ArrayList<>() {
        {
            add("assets\\UI\\menu\\P1Wins.png");
            add("assets\\UI\\menu\\P2Wins.png");
        }
    };

    //Square locations
    public static ArrayList<String> squarePaths = new ArrayList<>() {
        {
            add("assets\\UI\\board\\hit_square.png");
            add("assets\\UI\\board\\miss_square.png");
        }
    };


    public static void switchCurrentPlayer(){
        if(currentPlayer == 1) currentPlayer = 2;
        else currentPlayer = 1;
    }
}
