package io.github.fjank.sdc.impl;

/**
 * Specific strategy for filling a 10 radius circle. Uses the same points as The generic strategy.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com
 */
public class CircleStrategy1 extends AbstractCircleStrategy implements CircleStrategy {
    public CircleStrategy1(boolean[] matrix, int matrixColCount) {
        super(matrix, matrixColCount);
    }

    @Override
    public void fillCircle(int col, int row) {
        System.arraycopy(ones, 0, matrix, getIndex(row + 1, col), 2);
    }
}