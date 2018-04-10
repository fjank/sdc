package io.github.fjank.sdc.impl;

/**
 * Strategy interface for filling circles at a specific point.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com
 */
public interface CircleStrategy {
    /**
     * Fills a circle at the specified point.
     * @param col the column position if this point, zero based.
     * @param row the row position if this point, zero based.
     */
    void fillCircle(int col, int row);
}
