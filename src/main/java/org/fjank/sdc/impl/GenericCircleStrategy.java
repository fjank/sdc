package org.fjank.sdc.impl;

/**
 * A generic circle filling strategy that uses Bresenham algorithm to find the points that encloses the circle.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com
 */
public class GenericCircleStrategy implements CircleStrategy {
    private final boolean[] matrix;
    private final boolean[] ones;
    private final int radius;
    private final int matrixColCount;

    public GenericCircleStrategy(int radius, boolean[] matrix, int matrixColCount) {
        this.radius = radius;
        this.matrix = matrix;
        this.matrixColCount = matrixColCount;
        // Initialize a one array, to use in the coverAround arraycopy optimization.
        this.ones = new boolean[matrixColCount];
        for (int i = 0; i < ones.length; i++) {
            ones[i] = true;
        }
    }

    /** Use a generic bresenham algorithm to find circle edges, System.arraycopy to fill. */
    @Override
    public void fillCircle(int col, int row) {
        // CHECKSTYLE IGNORE MagicNumber FOR NEXT 1 LINES.
        int x = 0;
        int d = (5 - radius * 4) / 4;
        int y = radius;
        int rowOffset = radius + row;
        int colOffset = radius + col;
        do {
            //Since we are filling a circle, we fill using System.arraycopy, from left to right.
            int yStart = colOffset - y;
            int yLength = 2 * y;
            // Row A bottom
            System.arraycopy(ones, 0, matrix, getIndex(rowOffset - x, yStart), yLength);
            if (x != 0) {
                int xStart = colOffset - x;
                int xLength = 2 * x;
                // Row A top
                System.arraycopy(ones, 0, matrix, getIndex(rowOffset + x, yStart), yLength);
                // Row B bottom
                System.arraycopy(ones, 0, matrix, getIndex(rowOffset - y, xStart), xLength);
                // Row B top
                System.arraycopy(ones, 0, matrix, getIndex(rowOffset + y, xStart), xLength);
            }

            if (d < 0) {
                d += 2 * x + 1;
            } else {
                d += 2 * (x - y) + 1;
                y--;
            }
            x++;
        } while (x <= y);
    }

    private int getIndex(int row, int col) {
        return row * matrixColCount + col;
    }
}
