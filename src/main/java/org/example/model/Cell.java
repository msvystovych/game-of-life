package org.example.model;

public class Cell {
    public static final char ALIVE = 'X';
    public static final char DEAD = '-';
    public Boolean alive;

    public Cell(Boolean alive) {
        this.alive = alive;
    }
}