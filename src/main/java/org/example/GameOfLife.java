package org.example;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameOfLife {
    // init global vars
    static int rows = 24;
    static int cols = 48;
    static int interval = 150; // interval timer for speed of simulation
    static int generation = 0;

    // dynamically store cells in list
    static ArrayList<Cell> cells = new ArrayList<Cell>();

    public static void main(String[] args) throws IOException {
        //			printTitle();

        // add dead cells
        for(int i = 0; i < rows * cols; i++) {
            cells.add(new Cell(false));
        }

        // rand genertion of points
        Random rand = new Random();

        // loop through rows and columns
        for(int y = 0; y < rows; y++) {
            for(int x = 0; x < cols; x++) {
                boolean fill = false;

                // boolean fill is true if randomly generated number divided by half the num of rows is greater than 0.8, otherwise it's false
                fill = rand.nextInt(rows) / (rows/2) > 0.8f ? true : false;

                // should fill at x or y...
                editCell(new Point(x, y), fill);
            }
        }

        // generation timer
        Timer timer = new Timer();

        // generation task
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // list that stores next generation
                ArrayList<Cell> nextCells = new ArrayList<Cell>();

                // foreach cell in prev generation...
                for (Cell cell : cells) {

                    // get its pos. in list
                    int index = cells.indexOf(cell);

                    // num of adjacent cells
                    int neighbours = countNeighbours(cell, index);

                    if (cell.alive) {
                        if (neighbours < 2)
                            nextCells.add(new Cell(false));
                        else if (neighbours == 2 || neighbours == 3)
                            nextCells.add(new Cell(true));
                        else if (neighbours > 3)
                            nextCells.add(new Cell(false));
                    }
                    else if (neighbours == 3)
                        nextCells.add(new Cell(true));
                    else nextCells.add(new Cell(false));
                }


                generation++;

                System.out.println("Generation: " + generation);

                // prev cell is equal to next generation
                cells = nextCells;
                // call update to print every <interval> milliseconds
                updateCell();
            }
        };

        timer.scheduleAtFixedRate(task, 1000, interval);
    }



    public static int countNeighbours(Cell cell, int ind) {
        // init var to count
        int count = 0;

        // store neighbour pos
        int top =  ind + cols;
        int bot = ind - cols;

        // check neighbours and increment count...

        // northwest
        if(top - 1 < cells.size() && cells.get(top - 1).alive) { count++; }
        // north
        if(top 	   < cells.size() && cells.get(top).alive)     { count++; }
        // northeast
        if(top + 1 < cells.size() && cells.get(top + 1).alive) { count++; }

        // west
        if(ind - 1 > -1 	  && cells.get(ind - 1).alive) { count++; }
        // east
        if(ind + 1 < cells.size() && cells.get(ind + 1).alive) { count++; }

        // southwest
        if(bot - 1 > -1 	  && cells.get(bot - 1).alive) { count++; }
        // south
        if(bot 	   > -1 	  && cells.get(bot).alive)     { count++; }
        // southeast
        if(bot + 1 > -1 	  && cells.get(bot + 1).alive) { count++; }

        return count;
    }

    public static void editCell(Point cell, boolean alive) {
        // cols * y + x to find index of cell at x, y
        if(cell.x < cols && cell.y < rows) {
            cells.get(cols * cell.y + cell.x).alive = alive;
        }
    }

    public static void updateCell() {
        // print empty lines
        for(int i = 0 ; i < rows; i++) {
            System.out.println();
        }

        for(int y = 0; y < rows; y++) {
            for(int x = 0; x < cols; x++) {
                if(cells.get(cols * y + x).alive) {
                    System.out.print(Cell.ALIVE + " ");
                } else {
                    System.out.print(Cell.DEAD + " "); //□
                }
            }
            System.out.flush();
            System.out.print("\n");
        }
    }

    public static void printTitle() {
        System.out.println( "======================================================================================="  );

        System.out.println( " #####     #    #     # #######       ####### #######       #       ### ####### #######"  );
        System.out.println( "#     #   # #   ##   ## #             #     # #             #        #  #       #"		   );
        System.out.println( "#        #   #  # # # # #             #     # #             #        #  #       #"		   );
        System.out.println( "#  #### #     # #  #  # #####         #     # #####         #        #  #####   #####"    );
        System.out.println( "#     # ####### #     # #             #     # #             #        #  #       #     "   );
        System.out.println( "#     # #     # #     # #             #     # #             #        #  #       #       " );
        System.out.println( "#####   #     # #     # #######       ####### #             ####### ### #       #######"  );

        System.out.println( "======================================================================================="  );
    }
}