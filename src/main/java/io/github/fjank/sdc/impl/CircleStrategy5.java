package io.github.fjank.sdc.impl;

/**
 * Specific strategy for filling a 10 radius circle. Uses the same points as The generic strategy.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com
 */
public class CircleStrategy5 extends AbstractCircleStrategy implements CircleStrategy {
    public CircleStrategy5(boolean[] matrix, int matrixColCount) {
        super(matrix, matrixColCount);
    }

    @Override
    public void fillCircle(int col, int row) {
        System.arraycopy(ones, 0, matrix, getIndex(row, col + 3), 4);
        System.arraycopy(ones, 0, matrix, getIndex(row + 1, col + 1), 8);
        System.arraycopy(ones, 0, matrix, getIndex(row + 2, col + 1), 8);
        System.arraycopy(ones, 0, matrix, getIndex(row + 3, col), 10);
        System.arraycopy(ones, 0, matrix, getIndex(row + 4, col), 10);
        System.arraycopy(ones, 0, matrix, getIndex(row + 5, col), 10);
        System.arraycopy(ones, 0, matrix, getIndex(row + 6, col), 10);
        System.arraycopy(ones, 0, matrix, getIndex(row + 7, col), 10);
        System.arraycopy(ones, 0, matrix, getIndex(row + 8, col + 1), 8);
        System.arraycopy(ones, 0, matrix, getIndex(row + 9, col + 1), 8);
        System.arraycopy(ones, 0, matrix, getIndex(row + 10, col + 3), 4);
    }
}