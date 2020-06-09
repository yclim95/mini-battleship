package com.battleshipsgame;

public class PlayerShip extends Ship implements Score {
    private int numShip;
    public static int MAX_NUM_SHIP = 5;

    public void setNumShip(int numShip) {
        this.numShip = numShip;
    }

    public int getNumShip() {
        return this.numShip;
    }
}
