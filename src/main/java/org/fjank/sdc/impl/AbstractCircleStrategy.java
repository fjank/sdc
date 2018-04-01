package org.fjank.sdc.impl;

/**
 * Abstract class for the specific circle strategies.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com
 */
abstract class AbstractCircleStrategy {
    final boolean[] matrix;
    final boolean[] ones;
    private final int matrixColCount;

    AbstractCircleStrategy(boolean[] matrix, int matrixColCount) {
        this.matrix = matrix;
        this.matrixColCount = matrixColCount;
        // Initialize a one array, to use in the fillCircle arraycopy optimization.
        this.ones = new boolean[matrixColCount];
        for (int i = 0; i < ones.length; i++) {
            ones[i] = true;
        }
    }

    int getIndex(int row, int col) {
        return row * matrixColCount + col;
    }

    abstract void fillCircle(int col, int row);
}
