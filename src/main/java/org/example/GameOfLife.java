package org.example;

import org.example.model.Cell;
import org.example.model.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameOfLife {
    static int rows = 24;
    static int cols = 48;
    static int interval = 150;
    static int generation = 0;
    static List<Cell> cells = new ArrayList<>();

    public static void main(String[] args) {
        addDeadCells();
        initData();
        new Timer().scheduleAtFixedRate(createGenerationTask(), 1000, interval);
    }

    private static void initData() {
        Random rand = new Random();
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
                List<Cell> nextGenerationCells = new ArrayList<>();

                // go through cells from the previous generation
                for (Cell cell : cells) {
                    int index = cells.indexOf(cell);
                    int neighbours = countNeighbours(index);
                    if (cell.isAlive()) {
                        if (neighbours < 2) {
                            nextGenerationCells.add(new Cell(false));
                        } else if (neighbours == 2 || neighbours == 3) {
                            nextGenerationCells.add(new Cell(true));
                        } else if (neighbours > 3) {
                            nextGenerationCells.add(new Cell(false));
                        }
                    } else if (neighbours == 3) {
                        nextGenerationCells.add(new Cell(true));
                    } else {
                        nextGenerationCells.add(new Cell(false));
                    }
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
        for (int i = 0; i < rows * cols; i++) {
            cells.add(new Cell(false));
        }
    }

    public static int countNeighbours(int ind) {
        int count = 0;
        int top = ind + cols;
        int bottom = ind - cols;

        // northwest
        if (top - 1 < cells.size() && cells.get(top - 1).isAlive()) {count++;}
        // north
        if (top < cells.size() && cells.get(top).isAlive()) {count++;}
        // northeast
        if (top + 1 < cells.size() && cells.get(top + 1).isAlive()) {count++;}

        // west
        if (ind - 1 > -1 && cells.get(ind - 1).isAlive()) {count++;}
        // east
        if (ind + 1 < cells.size() && cells.get(ind + 1).isAlive()) {count++;}

        // southwest
        if (bottom - 1 > -1 && cells.get(bottom - 1).isAlive()) {count++;}
        // south
        if (bottom > -1 && cells.get(bottom).isAlive()) {count++;}
        // southeast
        if (bottom + 1 > -1 && cells.get(bottom + 1).isAlive()) {count++;}

        return count;
    }

    public static void editCell(Point cell, boolean alive) {
        // cols * y + x to find index of cell at x, y
        if (cell.getX() < cols && cell.getY() < rows) {
            cells.get(cols * cell.getY() + cell.getX()).setAlive(alive);
        }
    }

    public static void print() {
        for (int i = 0; i < rows; i++) {
            System.out.println();
        }

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (cells.get(cols * y + x).isAlive()) {
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