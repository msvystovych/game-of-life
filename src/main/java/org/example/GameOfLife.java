package org.example;

import org.example.model.Cell;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameOfLife {
    static int rows = 24;
    static int cols = 48;
    static int interval = 150;
    static int generation = 0;
    static ArrayList<Cell> cells = new ArrayList<>();

    public static void main(String[] args) {
        addDeadCells();
        initData();
        new Timer().scheduleAtFixedRate(createGenerationTask(), 1000, interval);
    }

    private static void initData() {
        // random generation of points
        Random rand = new Random();

        // loop through rows and columns
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                boolean fill = rand.nextInt(rows) / (rows / 2) > 0.8f;
                editCell(new Point(x, y), fill);
            }
        }
    }

    private static TimerTask createGenerationTask() {
        return new TimerTask() {
            @Override
            public void run() {
                ArrayList<Cell> nextGenerationCells = new ArrayList<>();

                // go through cells from the previous generation
                for (Cell cell : cells) {
                    int index = cells.indexOf(cell);
                    int neighbours = countNeighbours(index);
                    if (cell.alive) {
                        if (neighbours < 2) {nextGenerationCells.add(new Cell(false));} else if (neighbours == 2 || neighbours == 3) {
                            nextGenerationCells.add(new Cell(true));
                        } else if (neighbours > 3) {
                            nextGenerationCells.add(new Cell(false));
                        }
                    } else if (neighbours == 3) {nextGenerationCells.add(new Cell(true));} else {nextGenerationCells.add(new Cell(false));}
                }
                System.out.println("Generation: " + generation++);

                // prev cell is equal to next generation
                cells = nextGenerationCells;
                // call update to print every <interval> milliseconds
                print();
            }
        };
    }

    private static void addDeadCells() {
        // add dead cells
        for (int i = 0; i < rows * cols; i++) {
            cells.add(new Cell(false));
        }
    }

    public static int countNeighbours(int ind) {
        int count = 0;
        int top = ind + cols;
        int bot = ind - cols;

        // northwest
        if (top - 1 < cells.size() && cells.get(top - 1).alive) {count++;}
        // north
        if (top < cells.size() && cells.get(top).alive) {count++;}
        // northeast
        if (top + 1 < cells.size() && cells.get(top + 1).alive) {count++;}

        // west
        if (ind - 1 > -1 && cells.get(ind - 1).alive) {count++;}
        // east
        if (ind + 1 < cells.size() && cells.get(ind + 1).alive) {count++;}

        // southwest
        if (bot - 1 > -1 && cells.get(bot - 1).alive) {count++;}
        // south
        if (bot > -1 && cells.get(bot).alive) {count++;}
        // southeast
        if (bot + 1 > -1 && cells.get(bot + 1).alive) {count++;}

        return count;
    }

    public static void editCell(Point cell, boolean alive) {
        // cols * y + x to find index of cell at x, y
        if (cell.x < cols && cell.y < rows) {
            cells.get(cols * cell.y + cell.x).alive = alive;
        }
    }

    public static void print() {
        for (int i = 0; i < rows; i++) {
            System.out.println();
        }

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (cells.get(cols * y + x).alive) {
                    System.out.print(Cell.ALIVE + " ");
                } else {
                    System.out.print(Cell.DEAD + " ");
                }
            }
            System.out.flush();
            System.out.print("\n");
        }
    }
}