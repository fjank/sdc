package io.github.fjank.sdc.impl;

/**
 * Specific strategy for filling a 10 radius circle. Uses the same points as The generic strategy.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com
 */
public class CircleStrategy7 extends AbstractCircleStrategy implements CircleStrategy {
    public CircleStrategy7(boolean[] matrix, int matrixColCount) {
        super(matrix, matrixColCount);
    }

    @Override
    public void fillCircle(int col, int row) {
        System.arraycopy(ones, 0, matrix, getIndex(row, col + 4), 6);
        System.arraycopy(ones, 0, matrix, getIndex(row + 1, col + 2), 10);
        System.arraycopy(ones, 0, matrix, getIndex(row + 2, col + 1), 12);
        System.arraycopy(ones, 0, matrix, getIndex(row + 3, col + 1), 12);
        System.arraycopy(ones, 0, matrix, getIndex(row + 4, col), 14);
        System.arraycopy(ones, 0, matrix, getIndex(row + 5, col), 14);
        System.arraycopy(ones, 0, matrix, getIndex(row + 6, col), 14);
        System.arraycopy(ones, 0, matrix, getIndex(row + 7, col), 14);
        System.arraycopy(ones, 0, matrix, getIndex(row + 8, col), 14);
        System.arraycopy(ones, 0, matrix, getIndex(row + 9, col), 14);
        System.arraycopy(ones, 0, matrix, getIndex(row + 10, col), 14);
        System.arraycopy(ones, 0, matrix, getIndex(row + 11, col + 1), 12);
        System.arraycopy(ones, 0, matrix, getIndex(row + 12, col + 1), 12);
        System.arraycopy(ones, 0, matrix, getIndex(row + 13, col + 2), 10);
        System.arraycopy(ones, 0, matrix, getIndex(row + 14, col + 4), 6);
    }
}