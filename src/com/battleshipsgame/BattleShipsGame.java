/*
 * Created By: Lim Yao Cheng
 */
package com.battleshipsgame;

import java.util.Scanner;

public class BattleShipsGame {
    public static void main(String[] args) {

        // Initialization
        Scanner input = new Scanner(System.in); //Create Scanner Object for input
        int playerNumShip = PlayerShip.MAX_NUM_SHIP;
        int computerNumShip = ComputerShip.MAX_NUM_SHIP;
        int [][] playerCoordinates;
        PlayerShip playerShip = new PlayerShip();
        playerCoordinates = playerShip.getCoordinates();

        // Step 1 - Create the ocean map
        OceanMap map = new OceanMap();

        // Step 2 - Deploy player's ships
        System.out.println("Deploy your ships");

        for(int i = 0; i < playerNumShip; i++){
            System.out.println("Enter X coordinate for your " + (i+1) + ". ship:");
        }
        // Step 4 - Battle
        // Step 4.1 - Player's Turn
        // Step 4.2 - Computer's Turn
        // Step 5 - Game Over
    }
}
