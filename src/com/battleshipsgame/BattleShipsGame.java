/*
 * Created By: Lim Yao Cheng
 */
package com.battleshipsgame;

import java.util.Random;
import java.util.Scanner;

public class BattleShipsGame {
    public final static int X_COORDINATE_MAX = 10; // set max for X Coordinate
    public final static int Y_COORDINATE_MAX = 10; // set max for Y Coordinate
    // Global Initialization
    public static Scanner input = new Scanner(System.in); //Create Scanner Object for input
    public static int[][] mapCoordinates;
    public static int PLAYER_NUM_SHIP = 5; // Keep count for player's number of ship
    public static int COMPUTER_NUM_SHIP = 5; // Keep count for player's number of ship

    public static void main(String[] args) {
        // Step 1 - Create the ocean map
        mapCoordinates = new int[X_COORDINATE_MAX][Y_COORDINATE_MAX]; // Create array for Map Coordinate
        printMap(mapCoordinates);  // Print ocean map

        // Step 2 - Deploy player's ships
        // Step 3 - Deploy computer's ships
        moveShip();


        // Step 4 - Battle
        do {
            startBattle();
        } while ((BattleShipsGame.PLAYER_NUM_SHIP != 0 &&
                BattleShipsGame.COMPUTER_NUM_SHIP != 0)); // while either players ship != 0


        // Step 5 - Game Over
        gameEnd();
    }

    public static void moveShip() {
        movePlayerShip(); // Deploy Player Ship
        moveComputerShip(); // Deploy Computer Ship
    }

    public static void movePlayerShip() {
        int xCoordinate = 0;
        int yCoordinate = 0;
        boolean valid; // use to check if user's input is valid
        System.out.println("Deploy your ships");

        for (int i = 0; i < PlayerShip.NUM_SHIP; ) {
            do {
                try {
                    // Ask for user's input on coordinate to set the location of player's ship on the map
                    System.out.print("\n\nEnter X coordinate [0-9] for your " + (i + 1) + ". ship:");
                    xCoordinate = input.nextInt();
                    do {
                        try {
                            System.out.print("\nEnter Y coordinate [0-9] for your " + (i + 1) + ". ship:");
                            yCoordinate = input.nextInt();
                            valid = true;
                        } catch (Exception e) { // catch when user's input is not numeric value
                            input.next();
                            System.err.println("\nInvalid Input." + "Please enter [0-9] only.\n");
                            valid = false;
                        }
                    } while (!valid);
                } catch (Exception e) { // catch when user's input is not numeric value
                    input.next();
                    System.err.println("\nInvalid Input. Please enter [0-9] only.\n");
                    valid = false;
                }
            } while (!valid);


            System.out.println();
            // Check if player's ship exist on the coordinates entered (based on the map)
            if (!(xCoordinate >= 0 && xCoordinate < 10) || !(yCoordinate >= 0 && yCoordinate < 10)) {
                System.out.println("\nYou can't place ships outside the 10 by 10 grid");
            } else if (mapCoordinates[xCoordinate][yCoordinate] == 1) {
                System.out.println("\nYou can NOT place 2 or more ships on the same location");
            } else {
                mapCoordinates[xCoordinate][yCoordinate] = 1; //setCoordinate for player's ships
                i++; // Player ship exist on the coordinate entered (based on the map)
            }
        }
        printMap(mapCoordinates); //print the ship location on the map

    }

    public static void moveComputerShip() {
        int xCoordinate;
        int yCoordinate;
        System.out.println("\nComputer is Deploying ships");
        for (int i = 0; i < ComputerShip.NUM_SHIP; ) {


            // set random coordinate to set the location of computer's ships (5) on the map
            Random rand = new Random();
            int index = rand.nextInt(10); // random index between [0 - 10]
            xCoordinate = index;
            index = rand.nextInt(10); // regenerate random index between [0 - 10]
            yCoordinate = index;

            // Check if player's ship or computer's sh exist on the coordinates entered (based on the map)
            if (mapCoordinates[xCoordinate][yCoordinate] == 0) {
                mapCoordinates[xCoordinate][yCoordinate] = 2; //setCoordinate for computer's ships
                i++; // Player ship exist on the coordinate entered (based on the map)
                System.out.println((i) + ". Ship DEPLOYED");
            }

        }

        printMap(mapCoordinates); //print the ship location on the map
    }

    public static void playerAttack() {
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
        int xCoordinate = 0;
        int yCoordinate = 0;
        boolean valid; // Use to check if the user's input is valid
        do {
            do {
                try {
                    System.out.println("\n\nYOUR TURN");
                    System.out.print("Enter X coordinate: ");
                    xCoordinate = input.nextInt();
                    do {
                        try {
                            System.out.print("\nEnter Y coordinate: ");
                            yCoordinate = input.nextInt();
                            valid = true;
                        } catch (Exception e) { // catch when user's input is not numeric value
                            input.next();
                            valid = false;
                            System.err.println("\nInvalid Input. Please enter [0-9] only.\n");
                        }
                    } while (!valid);

                } catch (Exception e) { // catch when user's input is not numeric value
                    input.next();
                    valid = false;
                    System.err.println("\nInvalid Input. Please enter [0-9] only.\n");
                }

            } while (!valid);

            valid = true; // Use to check if user's input is entered before
            //Check if is out of range or have been chosen
            if (!(xCoordinate >= 0 && xCoordinate < 10) || !(yCoordinate >= 0 && yCoordinate < 10))
                System.out.println("\nInvalid input. Re-enter valid coordinate [0-9]!");
            else {
                if (mapCoordinates[xCoordinate][yCoordinate] == 2) {
                    System.out.println("\nBoom! You sunk the ship!");
                    mapCoordinates[xCoordinate][yCoordinate] = 3; // means "!" / mark as 'sunk ship'
                    --BattleShipsGame.COMPUTER_NUM_SHIP;
                } else if (mapCoordinates[xCoordinate][yCoordinate] == 1) {
                    System.out.println("\nOh no, you sunk your own ship :(");
                    mapCoordinates[xCoordinate][yCoordinate] = 4; // means "x" / mark as 'player loses ship'
                    --BattleShipsGame.PLAYER_NUM_SHIP;
                } else if (mapCoordinates[xCoordinate][yCoordinate] == 3 ||
                        mapCoordinates[xCoordinate][yCoordinate] == 4 ||
                        mapCoordinates[xCoordinate][yCoordinate] == 5){
                    System.out.println("\nHave entered repeated coordinates. Please re-enter: ");
                    valid = false;
                }

                else {
                    System.out.println("\nSorry, you missed");
                    mapCoordinates[xCoordinate][yCoordinate] = 5; // means "-" / mark as 'missed'
                }
            }
        } while (!(xCoordinate >= 0 && xCoordinate < 10) || !(yCoordinate >= 0 && yCoordinate < 10)
                || !valid);

    }

    public static void computerAttack() {
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
        do {
            System.out.println("\nCOMPUTER'S TURN");
            // set random coordinate
            Random rand = new Random();
            int index = rand.nextInt(10); // random index between [0 - 10]
            xCoordinate = index;
            index = rand.nextInt(10); // regenerate random index between [0 - 10]
            yCoordinate = index;


            //Check if is out of range or have been chosen
            if (!(xCoordinate >= 0 && xCoordinate < 10) || !(yCoordinate >= 0 && yCoordinate < 10))
                System.out.println("\nInvalid input. Re-enter valid coordinate [0-9]!");
            else {
                if (mapCoordinates[xCoordinate][yCoordinate] == 1) {
                    System.out.println("\nThe Computer sunk one of your ships!");
                    mapCoordinates[xCoordinate][yCoordinate] = 4; // mark "x" / mark as 'player loses ship'
                    --BattleShipsGame.PLAYER_NUM_SHIP;
                } else if (mapCoordinates[xCoordinate][yCoordinate] == 2) {
                    System.out.println("\nThe Computer sunk one of its own ships");
                    mapCoordinates[xCoordinate][yCoordinate] = 3; // means "!" / mark as 'computer loses ship'
                    --BattleShipsGame.COMPUTER_NUM_SHIP;
                } else if (mapCoordinates[xCoordinate][yCoordinate] == 3 ||
                        mapCoordinates[xCoordinate][yCoordinate] == 4 ||
                        mapCoordinates[xCoordinate][yCoordinate] == 5) {
                    System.out.print("");
                } else {
                    System.out.println("\nComputer missed");
                    mapCoordinates[xCoordinate][yCoordinate] = 5; // means "-" / mark as 'missed'
                }
            }

        } while (!(xCoordinate >= 0 && xCoordinate < 10) || !(yCoordinate >= 0 && yCoordinate < 10));

    }

    public static void startBattle() {
        playerAttack(); // Player's turn
        computerAttack(); // Computer's turn
        printMap(mapCoordinates); //print the ship location on the map

        System.out.println();
        System.out.println("Your ships: " + BattleShipsGame.PLAYER_NUM_SHIP + " | Computer ships: " +
                BattleShipsGame.COMPUTER_NUM_SHIP);
        System.out.println();
    }

    public static void gameEnd() {
        System.out.println("Your ships: " + BattleShipsGame.PLAYER_NUM_SHIP + " | Computer ships: " +
                BattleShipsGame.COMPUTER_NUM_SHIP);

        if (ComputerShip.NUM_SHIP == 0)
            System.out.println("Hooray! You win the battle\n");
        else
            System.out.println("You lose the battle\n");
    }


    public static String createWhiteSpaces(String text, int numSpace) {
        // set "white spaces + text + white spaces"
        String space = new String(new char[numSpace]).replace('\0', ' ');

        return space + text + space; // return "white spaces + text + white spaces"
    }

    public static String getLabel(String text, int numSpace) {

        return createWhiteSpaces(text, numSpace); // return "white spaces + text + white spaces"
    }

    public static void printMap(int[][] coordinates) {
        for (int i = 0; i < coordinates.length; i++) {
            if (i == 0) { // If is the 1st row
                // print "white spaces + text + white spaces"
                System.out.println(getLabel("0123456789", 3));
            }
            System.out.print(i + " |");

            for (int j = 0; j <= coordinates[i].length; j++) {
                if (j == Y_COORDINATE_MAX) System.out.print("| " + i); // If it is the last column, print the following
                else {
                    if (coordinates[i][j] == 1) {
                        System.out.print("@"); // Player's ship
                    } else if (coordinates[i][j] == 3) {
                        System.out.print("!");  // Player/ Computer Sink Computer's/ Player's ship
                    } else if (coordinates[i][j] == 4) {
                        System.out.print("x"); // Player/Computer sink own ship
                    } else if (coordinates[i][j] == 5) {
                        System.out.print("-"); // Player/Computer guess wrong coordinate
                    } else System.out.print(" "); // Else print " " (blank space)
                }
            }
            System.out.println();
            //Last line : print
            if (i == coordinates.length - 1) System.out.println(getLabel("0123456789", 3));
        }
    }
}
