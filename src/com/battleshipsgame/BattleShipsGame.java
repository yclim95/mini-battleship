/*
 * Created By: Lim Yao Cheng
 */
package com.battleshipsgame;

import java.util.Random;
import java.util.Scanner;

public class BattleShipsGame {
    // Global Initialization
    public static Scanner input = new Scanner(System.in); //Create Scanner Object for input
    public static int[][] mapCoordinates;
    public static OceanMap map;
    public static PlayerShip playerShip;
    public static ComputerShip computerShip;

    public static void main(String[] args) {
        playerShip = new PlayerShip();
        computerShip = new ComputerShip();
        // Step 1 - Create the ocean map
        map = new OceanMap();
        mapCoordinates = map.getCoordinates();

        // Step 2 - Deploy player's ships
        // Step 3 - Deploy computer's ships
        deployShip();


        // Step 4 - Battle
        do{
            battle();
        }while((PlayerShip.NUM_SHIP!=0 && ComputerShip.NUM_SHIP!=0)); // while coordinate == 0 STOP



        // Step 5 - Game Over
        gameOver();
    }

    public static void deployShip(){
        deployPlayerShip();
        deployComputerShip();
    }

    public static void deployPlayerShip(){
        int xCoordinate;
        int yCoordinate;
        System.out.println("Deploy your ships");
        for (int i = 0; i < PlayerShip.NUM_SHIP;) {

            // Ask for user's input on coordinate to set the location of player's ship on the map
            System.out.print("Enter X coordinate for your " + (i + 1) + ". ship:");
            xCoordinate = input.nextInt();
            System.out.println();
            System.out.print("Enter Y coordinate for your " + (i + 1) + ". ship:");
            yCoordinate = input.nextInt();
            System.out.println();

            // Check if player's ship exist on the coordinates entered (based on the map)
            if (!(xCoordinate >= 0 && xCoordinate < 10) || !(yCoordinate >= 0 && yCoordinate < 10)) {
                System.out.println("You can't place ships outside the 10 by 10 grid");
            }else if (mapCoordinates[xCoordinate][yCoordinate] == 1){
                System.out.println("You can NOT place 2 or more ships on the same location");
            }
            else {
                mapCoordinates[xCoordinate][yCoordinate] = 1;
                i++; // Player ship exist on the coordinate entered (based on the map)
            }

        }
        map.setCoordinates(mapCoordinates); //setCoordinate for player's ships
        map.printMap(mapCoordinates); //print the ship location on the map
    }

    public static void deployComputerShip(){
        int xCoordinate;
        int yCoordinate;
        System.out.println("Computer is Deploying ships");
        for (int i = 0; i < ComputerShip.NUM_SHIP;) {


            // set random coordinate to set the location of computer's ships (5) on the map
            Random rand = new Random();
            int index = rand.nextInt(10); // random index between [0 - 10]
            xCoordinate = index;
            index = rand.nextInt(10); // regenerate random index between [0 - 10]
            yCoordinate = index;

            // Check if player's ship or computer's sh exist on the coordinates entered (based on the map)
            if (mapCoordinates[xCoordinate][yCoordinate] != 2 || mapCoordinates[xCoordinate][yCoordinate] != 1) {
                mapCoordinates[xCoordinate][yCoordinate] = 2;
                i++; // Player ship exist on the coordinate entered (based on the map)
                System.out.println((i+1) + ". Ship DEPLOYED");
            }

        }

        map.setCoordinates(mapCoordinates); //setCoordinate for computer's ships
        map.printMap(mapCoordinates); //print the ship location on the map
    }

    public static void playerTurn(){
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
        int xCoordinate;
        int yCoordinate;
        do{
            System.out.println("YOUR TURN");
            System.out.print("Enter X coordinate: ");
            xCoordinate = input.nextInt();
            System.out.print("Enter Y coordinate: ");
            yCoordinate = input.nextInt();

            //Check if is out of range or have been chosen
            if(!(xCoordinate >= 0 && xCoordinate < 10) || !(yCoordinate >= 0 && yCoordinate < 10))
                System.out.println("Invalid input. Re-enter valid coordinate [0-9]!");
            else{
                if (mapCoordinates[xCoordinate][yCoordinate] == 2){
                    System.out.println("Boom! You sunk the ship!");
                    mapCoordinates[xCoordinate][yCoordinate] = 3; // means "!" / mark as 'sunk ship'
                    ComputerShip.NUM_SHIP--;
                    computerShip.setScore(ComputerShip.NUM_SHIP);
                }else if (mapCoordinates[xCoordinate][yCoordinate] == 1){
                    System.out.println("Oh no, you sunk your own ship :(");
                    mapCoordinates[xCoordinate][yCoordinate] = 4; // means "x" / mark as 'player loses ship'
                    PlayerShip.NUM_SHIP--;
                    playerShip.setScore(PlayerShip.NUM_SHIP);
                    computerShip.setScore(ComputerShip.NUM_SHIP + 1);
                }
                else{
                    System.out.println("Sorry, you missed");
                    mapCoordinates[xCoordinate][yCoordinate] = 5; // means "-" / mark as 'missed'
                }
            }
        }while(!(xCoordinate >= 0 && xCoordinate < 10) || !(yCoordinate >= 0 && yCoordinate < 10));

        map.setCoordinates(mapCoordinates); //update Coordinate mark on the map
    }

    public static void computerTurn(){
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
        int xCoordinate;
        int yCoordinate;
        do{
            System.out.println("COMPUTER'S TURN");
            // set random coordinate
            Random rand = new Random();
            int index = rand.nextInt(10); // random index between [0 - 10]
            xCoordinate = index;
            index = rand.nextInt(10); // regenerate random index between [0 - 10]
            yCoordinate = index;


            //Check if is out of range or have been chosen
            if(!(xCoordinate >= 0 && xCoordinate < 10) || !(yCoordinate >= 0 && yCoordinate < 10))
                System.out.println("Invalid input. Re-enter valid coordinate [0-9]!");
            else{
                if (mapCoordinates[xCoordinate][yCoordinate] == 1){
                    System.out.println("The Computer sunk one of your ships!");
                    mapCoordinates[xCoordinate][yCoordinate] = 4; // mark "x" / mark as 'player loses ship'
                    PlayerShip.NUM_SHIP--;
                    playerShip.setScore(PlayerShip.NUM_SHIP);
                }else if (mapCoordinates[xCoordinate][yCoordinate] == 2){
                    System.out.println("The Computer sunk one of its own ships");
                    mapCoordinates[xCoordinate][yCoordinate] = 3; // means "!" / mark as 'computer loses ship'
                    ComputerShip.NUM_SHIP--;
                    computerShip.setScore(ComputerShip.NUM_SHIP);
                    playerShip.setScore(PlayerShip.NUM_SHIP + 1);
                }else {
                    System.out.println("Computer missed");
                    mapCoordinates[xCoordinate][yCoordinate] = 6; // means "-" / mark as 'missed'
                }
            }

        }while(!(xCoordinate >= 0 && xCoordinate < 10) || !(yCoordinate >= 0 && yCoordinate < 10));

        map.setCoordinates(mapCoordinates); //update coordinate mark on the map
    }

    public static void battle(){
        playerTurn();
        computerTurn();
        map.printMap(mapCoordinates); //print the ship location on the map

        System.out.println();
        System.out.println("Your ships: " + PlayerShip.NUM_SHIP + " | Computer ships: " + ComputerShip.NUM_SHIP);
        System.out.println();
    }

    public static void gameOver(){
        System.out.println("Your ships: " + PlayerShip.NUM_SHIP + " | Computer ships: " + ComputerShip.NUM_SHIP);
        System.out.println("Player Score: " + playerShip.getScore() + " | Computer Score: " + computerShip.getScore());

        if (ComputerShip.NUM_SHIP==0)
            System.out.println("Hooray! You win the battle\n");
        else
            System.out.println("You lose the battle\n");
    }
}
