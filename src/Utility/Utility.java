package Utility;

import Entities.Ship;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Utility {
    public static int squareLen = 65;

    // Ships locations
    public static ArrayList<String> wardogPaths = new ArrayList<>() {
        { add("assets\\ships\\wardog.png"); }
    };
    public static ArrayList<String> voidRayPaths = new ArrayList<>() {
        { add("assets\\ships\\voidray.png"); }
    };
    public static ArrayList<String> stalkerPaths = new ArrayList<>() {
        { add("assets\\ships\\stalker.png"); }
    };
    public static ArrayList<String> carrierPaths = new ArrayList<>() {
        { add("assets\\ships\\carrier.png"); }
    };

    //Abilities locations
    public static ArrayList<String> deathRayPaths = new ArrayList<>() {
        { add("assets\\abilities\\ray.png"); }
    };
    public static ArrayList<String> photonCanonPaths = new ArrayList<>() {
        { add("assets\\abilities\\canon.png"); }
    };
    public static ArrayList<String> repairPaths = new ArrayList<>() {
        { add("assets\\abilities\\repair.png"); }
    };
    public static ArrayList<String> shieldPaths = new ArrayList<>() {
        { add("assets\\abilities\\shield.png"); }
    };

    //Square locations
    public static ArrayList<String> squarePaths = new ArrayList<>() {
        {
            add("assets\\board\\hit_square.png");
            add("assets\\board\\miss_square.png");
        }
    };
}
