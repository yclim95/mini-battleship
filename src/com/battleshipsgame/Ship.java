package com.battleshipsgame;

public class Ship implements Coordinate {
    private final int X_COORDINATE_MAX = 10;
    private final int Y_COORDINATE_MAX = 10;
    private int[][] coordinates;

    Ship() {
        this.coordinates = new int[X_COORDINATE_MAX][Y_COORDINATE_MAX];
    }

    public void setCoordinates(int[][] coordinates) {
        this.coordinates = coordinates;
    }

    public int[][] getCoordinates() {
        return this.coordinates;
    }

}
