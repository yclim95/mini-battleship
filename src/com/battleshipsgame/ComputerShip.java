package com.battleshipsgame;

public class ComputerShip extends Ship implements Score {
    public final static int MAX_NUM_SHIP = 5;
    private int numShip;

    public int getNumShip() {
        return this.numShip;
    }

    public void setNumShip(int numShip) {
        this.numShip = numShip;
    }
}
