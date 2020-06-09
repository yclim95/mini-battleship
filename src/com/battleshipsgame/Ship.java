package com.battleshipsgame;

public class Ship implements Score{
    private static final int STARTING_SCORE = 5;
    private int score;

    Ship(){
        this.score = STARTING_SCORE;
    }
    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
