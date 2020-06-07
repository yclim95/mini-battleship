/*
 * Created By: Lim Yao Cheng
 */
package com.battleshipsgame;

import java.util.Random;
import java.util.Scanner;

public class BattleShipsGame {
    public static void main(String[] args) {

        // Initialization
        Scanner input = new Scanner(System.in); //Create Scanner Object for input
        int playerNumShip = PlayerShip.MAX_NUM_SHIP;
        int computerNumShip = ComputerShip.MAX_NUM_SHIP;
        int[][] playerCoordinates;
        int[][] computerCoordinates;

        PlayerShip playerShip = new PlayerShip();
        playerCoordinates = playerShip.getCoordinates();

        ComputerShip computerShip = new ComputerShip();
        computerCoordinates = computerShip.getCoordinates();

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
        playerShip.setCoordinates(playerCoordinates); //setCoordinate for player's ships
        map.printMap(playerShip.getCoordinates()); //print the ship location on the map

        // Step 3 - Deploy computer's ships
        System.out.println("Computer is Deploying ships");
        for (int i = 0; i < computerNumShip; i++) {

            do {
                // set random coordinate to set the location of computer's ships (5) on the map
                Random rand = new Random();
                int index = rand.nextInt(10); // random index between [0 - 10]
                xCoordinate = index;
                index = rand.nextInt(10); // regenerate random index between [0 - 10]
                yCoordinate = index;

                check = true;
                // Check if player's ship exist on the coordinates entered (based on the map)
                if (computerCoordinates[xCoordinate][yCoordinate] == 2) {
                    check = false;
                }else {computerCoordinates[xCoordinate][yCoordinate] = 2;
                System.out.println((i+1) + ". Ship DEPLOYED");}
            } while (check==false); // while layer's ship exist on the coordinates entered (based on the map)

        }

        computerShip.setCoordinates(computerCoordinates); //setCoordinate for computer's ships
        map.printMap(computerShip.getCoordinates()); //print the ship location on the map

        // Step 4 - Battle
        // Step 4.1 - Player's Turn
        /*
         * 4.1.2 Player correctly guessed coordinates of computer’s ship (computer loses ship).
         * 4.1.2.1 You should tell the user "Boom! You sunk the ship!"
         * 4.1.2.2 You should mark this as a hit when printing the map as a "!". You can choose how to store this
         *          result within your own code.
         * 4.1.3 Player entered coordinates of his/her own ship (player loses ship).
         * 4.1.3.1 You should tell the user "Oh no, you sunk your own ship :("
         * 4.1.3.2 You should mark this as an "x" when printing the map, replacing the "@"
         * 4.1.4 Player missed. No ship on the entered coordinates. "Sorry, you missed"
         * 4.1.4.1 You should mark this as an "-" when printing the map.
         */
        System.out.println("YOUR TURN");
        System.out.print("Enter X coordinate: ");
        xCoordinate = input.nextInt();
        System.out.print("Enter Y coordinate: ");
        yCoordinate = input.nextInt();

        if (computerCoordinates[xCoordinate][yCoordinate] == 2){
            System.out.println("Boom! You sunk the ship!");
            computerCoordinates[xCoordinate][yCoordinate] = 3; // means "!" / mark as 'sunk ship'
            playerCoordinates[xCoordinate][yCoordinate] = 3; // mark in player's map
        }else if (playerCoordinates[xCoordinate][yCoordinate] == 1){
            System.out.println("Oh no, you sunk your own ship :(");
            playerCoordinates[xCoordinate][yCoordinate] = 4; // means "x" / mark as 'player loses ship'
        }else {
            System.out.println("Sorry, you missed");
            playerCoordinates[xCoordinate][yCoordinate] = 5; // means "-" / mark as 'missed'
        }


        computerShip.setCoordinates(computerCoordinates); //update Coordinate mark for computer's ships
        playerShip.setCoordinates(playerCoordinates); //update player map coordinates
        map.printMap(playerShip.getCoordinates()); //print the ship location on the map




        // Step 4.2 - Computer's Turn
        /*
         * 4.2.1 Computer guessed coordinates of the player’s ship (player loses ship).
         * 4.2.1.1 You should inform the user "The Computer sunk one of your ships!"
         * 4.2.1.2 You should mark this as an "x" when printing the map
         * 4.2.2 Computer guessed coordinates of its own ship (computer loses ship).
         * 4.2.1.2 You should inform the user "The Computer sunk one of its own ships"
         * 4.2.1.3 You should mark this as a "!" when printing the map
         * 4.2.3 Computer missed. No ship on guessed coordinates.
         * 4.2.3.1 You should inform the user "Computer missed".
         * 4.2.3.2 You do not need to mark the map with the missed computer guesses, however you will want to
                    decide a way to store this information in your map so the computer doesn't duplicate guesses
                    later.
         */
        System.out.println("COMPUTER'S TURN");
        // set random coordinate
        Random rand = new Random();
        int index = rand.nextInt(10); // random index between [0 - 10]
        xCoordinate = index;
        index = rand.nextInt(10); // regenerate random index between [0 - 10]
        yCoordinate = index;

        if (playerCoordinates[xCoordinate][yCoordinate] == 1){
            System.out.println("The Computer sunk one of your ships!");
            playerCoordinates[xCoordinate][yCoordinate] = 4; // mark "x" / mark as 'player loses ship'
        }else if (computerCoordinates[xCoordinate][yCoordinate] == 2){
            System.out.println("The Computer sunk one of its own ships");
            playerCoordinates[xCoordinate][yCoordinate] = 3; // means "!" / mark as 'computer loses ship'
        }else {
            System.out.println("Computer missed");
            computerCoordinates[xCoordinate][yCoordinate] = 6; // means "-" / mark as 'missed'
        }


        computerShip.setCoordinates(computerCoordinates); //update Coordinate mark for computer's ships
        playerShip.setCoordinates(playerCoordinates); //update player map coordinates
        map.printMap(playerShip.getCoordinates()); //print the ship location on the map



        // Step 5 - Game Over
    }
}
