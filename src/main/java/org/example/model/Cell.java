package org.example.model;

public class Cell {
    public static final char ALIVE = 'X';
    public static final char DEAD = '-';
    private boolean alive;

    public Cell(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}