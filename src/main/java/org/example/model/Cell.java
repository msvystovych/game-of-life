package org.example.model;

public class Cell {
    public static final char ALIVE = 'X';
    public static final char DEAD = '-';
    public boolean alive;

    public Cell(boolean alive) {
        this.alive = alive;
    }
}