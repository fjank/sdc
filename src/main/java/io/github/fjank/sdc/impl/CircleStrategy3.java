package io.github.fjank.sdc.impl;

/**
 * Specific strategy for filling a 10 radius circle. Uses the same points as The generic strategy.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com
 */
public class CircleStrategy3 extends AbstractCircleStrategy implements CircleStrategy {
    public CircleStrategy3(boolean[] matrix, int matrixColCount) {
        super(matrix, matrixColCount);
    }

    @Override
    public void fillCircle(int col, int row) {
        System.arraycopy(ones, 0, matrix, getIndex(row, col + 2), 2);
        System.arraycopy(ones, 0, matrix, getIndex(row + 1, col + 1), 4);
        System.arraycopy(ones, 0, matrix, getIndex(row + 2, col), 6);
        System.arraycopy(ones, 0, matrix, getIndex(row + 3, col), 6);
        System.arraycopy(ones, 0, matrix, getIndex(row + 4, col), 6);
        System.arraycopy(ones, 0, matrix, getIndex(row + 5, col + 1), 4);
        System.arraycopy(ones, 0, matrix, getIndex(row + 6, col + 2), 2);
    }
}