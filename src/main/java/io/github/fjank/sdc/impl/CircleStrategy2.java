package io.github.fjank.sdc.impl;

/**
 * Specific strategy for filling a 10 radius circle. Uses the same points as The generic strategy.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com
 */
public class CircleStrategy2 extends AbstractCircleStrategy implements CircleStrategy {
    public CircleStrategy2(boolean[] matrix, int matrixColCount) {
        super(matrix, matrixColCount);
    }

    @Override
    public void fillCircle(int col, int row) {
        System.arraycopy(ones, 0, matrix, getIndex(row + 1, col + 1), 2);
        System.arraycopy(ones, 0, matrix, getIndex(row + 2, col), 4);
        System.arraycopy(ones, 0, matrix, getIndex(row + 3, col + 1), 2);
    }
}