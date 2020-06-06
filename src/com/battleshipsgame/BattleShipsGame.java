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
        int[][] playerCoordinates;

        PlayerShip playerShip = new PlayerShip();
        playerCoordinates = playerShip.getCoordinates();

        // Step 1 - Create the ocean map
        OceanMap map = new OceanMap();

        // Step 2 - Deploy player's ships
        int xCoordinate = 0;
        int yCoordinate = 0;
        boolean check;
        System.out.println("Deploy your ships");
        for (int i = 0; i < playerNumShip; i++) {

            do {
                // Ask for user's input on coordinate to set the location of player's ship on the map
                System.out.print("Enter X coordinate for your " + (i + 1) + ". ship:");
                xCoordinate = input.nextInt();
                System.out.println();
                System.out.print("Enter Y coordinate for your " + (i + 1) + ". ship:");
                yCoordinate = input.nextInt();
                System.out.println();

                check = true;
                // Check if player's ship exist on the coordinates entered (based on the map)
                if (playerCoordinates[xCoordinate][yCoordinate] == 1) {
                    System.out.println("You can NOT place 2 or more ships on the same location");
                    System.out.println("You can't place ships outside the 10 by 10 grid");
                    check = false;
                }else {playerCoordinates[xCoordinate][yCoordinate] = 1;}
            } while (check==false); // while layer's ship exist on the coordinates entered (based on the map)

        }
        playerShip.setCoordinates(playerCoordinates);
        map.printMap(playerShip.getCoordinates());
        // Step 4 - Battle
        // Step 4.1 - Player's Turn
        // Step 4.2 - Computer's Turn
        // Step 5 - Game Over
    }
}
