package org.fjank.sdc.impl;

/**
 * Specific strategy for filling a 10 radius circle. Uses the same points as The generic strategy.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com
 */
public class CircleStrategy9 extends AbstractCircleStrategy implements CircleStrategy {
    public CircleStrategy9(boolean[] matrix, int matrixColCount) {
        super(matrix, matrixColCount);
    }

    @Override
    public void fillCircle(int col, int row) {
        System.arraycopy(ones, 0, matrix, getIndex(row, col + 6), 6);
        System.arraycopy(ones, 0, matrix, getIndex(row + 1, col + 4), 10);
        System.arraycopy(ones, 0, matrix, getIndex(row + 2, col + 2), 14);
        System.arraycopy(ones, 0, matrix, getIndex(row + 3, col + 2), 14);
        System.arraycopy(ones, 0, matrix, getIndex(row + 4, col + 1), 16);
        System.arraycopy(ones, 0, matrix, getIndex(row + 5, col + 1), 16);
        System.arraycopy(ones, 0, matrix, getIndex(row + 6, col), 18);
        System.arraycopy(ones, 0, matrix, getIndex(row + 7, col), 18);
        System.arraycopy(ones, 0, matrix, getIndex(row + 8, col), 18);
        System.arraycopy(ones, 0, matrix, getIndex(row + 9, col), 18);
        System.arraycopy(ones, 0, matrix, getIndex(row + 10, col), 18);
        System.arraycopy(ones, 0, matrix, getIndex(row + 11, col), 18);
        System.arraycopy(ones, 0, matrix, getIndex(row + 12, col), 18);
        System.arraycopy(ones, 0, matrix, getIndex(row + 13, col + 1), 16);
        System.arraycopy(ones, 0, matrix, getIndex(row + 14, col + 1), 16);
        System.arraycopy(ones, 0, matrix, getIndex(row + 15, col + 2), 14);
        System.arraycopy(ones, 0, matrix, getIndex(row + 16, col + 2), 14);
        System.arraycopy(ones, 0, matrix, getIndex(row + 17, col + 4), 10);
        System.arraycopy(ones, 0, matrix, getIndex(row + 18, col + 6), 6);
    }
}