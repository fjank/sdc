package io.github.fjank.sdc;

import io.github.fjank.sdc.impl.CircleStrategy;
import io.github.fjank.sdc.impl.CircleStrategy1;
import io.github.fjank.sdc.impl.CircleStrategy10;
import io.github.fjank.sdc.impl.CircleStrategy2;
import io.github.fjank.sdc.impl.CircleStrategy3;
import io.github.fjank.sdc.impl.CircleStrategy4;
import io.github.fjank.sdc.impl.CircleStrategy5;
import io.github.fjank.sdc.impl.CircleStrategy6;
import io.github.fjank.sdc.impl.CircleStrategy7;
import io.github.fjank.sdc.impl.CircleStrategy8;
import io.github.fjank.sdc.impl.CircleStrategy9;
import io.github.fjank.sdc.impl.GenericCircleStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Filters keypoints based on Suppression via Disk Covering.
 * <p>Efficiently selecting spatially distributed keypoints for visual tracking
 * Steffen Gauglitz Luca Foschini Matthew Turk Tobias Hollerer
 * <p>From paper:
 * <pre>We describe an algorithm dubbed Suppression via Disk Covering (SDC) to efficiently select a set of strong,
 * spatially distributed keypoints, and we show that selecting keypoint in this way significantly improves visual
 * tracking.
 * </pre>
 * Main method after constructing this filter is {@link #filterByRadius(int, List)}.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com
 * Based loosely on code from
 * <a href="https://github.com/RoboStat/ros_slam/blob/master/src/keypoint_filter.cpp">ros_slam</a>
 * @see <a href="http://lucafoschini.com/papers/Efficiently_ICIP11.pdf">Paper</a>
 */
public class KeyPointFilter1 {
    private final int rowCount;
    private final int colCount;
    private boolean[] matrix;
    private boolean[] zeros;
    private int matrixColCount;
    private int matrixRowCount;

    private int radiusInitialized;
    private CircleStrategy circleStrategy;

    /**
     * Creates a new KeyPointFilter for the specified image colCount and rowCount.
     * @param rowCount the rowCount of the image.
     * @param colCount the colCount of the image.
     */
    public KeyPointFilter1(int colCount, int rowCount) {
        if (colCount <= 0 || rowCount <= 0) {
            throw new IllegalArgumentException(
                "Both rows '" + rowCount + "' and cols '" + colCount + "' must be larger than zero.");
        }
        this.colCount = colCount;
        this.rowCount = rowCount;

    }

    /**
     * Filters the incoming list of points by a specified radius. Leaves the incoming list untouched, and returns the
     * filtered results. For description of radius, see paper.
     * @param radius the radius we are using for creating the covering circles.
     * @param input the list of points to filter.
     * @return the filtered list of KeyPoints.
     */
    public List<KeyPoint> filterByRadius(int radius, List<KeyPoint> input) {
        init(radius);
        List<KeyPoint> filtered = new ArrayList<>();
        // Eliminating by covering
        for (int i = 0, inputSize = input.size(); i < inputSize; i++) {
            KeyPoint point = input.get(i);
            int col = point.getXPos();
            int row = point.getYPos();
            if (!isSet(col, row)) {
                circleStrategy.fillCircle(col, row);
                filtered.add(point);
            }
        }
        return filtered;

    }

    private int getIndex(int row, int col) {
        return row * matrixColCount + col;
    }

    /**
     * Returns whether the position in the matrix is set (already covered by another disc) or not.
     * @param col the column of the position to check
     * @param row the row of the position to check
     * @return {@code} true if the position is set, {@code false} otherwise.
     */
    public boolean isSet(int col, int row) {
        return matrix[getIndex(row + radiusInitialized, col + radiusInitialized)];
    }

    /**
     * Sorts the points according to the response.
     * @param list the list of KeyPoint to sort.
     */
    public void sortPointsByResponse(List<KeyPoint> list) {
        if (list == null) {
            return;
        }
        list.sort((kp1, kp2) -> Float.compare(kp2.getResponse(), kp1.getResponse()));
    }

    /**
     * Returns the number of rows in the matrix for this filter.
     * @return the number of rows in the matrix for this filter.
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * Returns the number of columns in the matrix for this filter.
     * @return the number of columns in the matrix for this filter.
     */
    public int getColCount() {
        return colCount;
    }

    /**
     * Initializes this filter for the specified radius. This is automatically done if you use the
     * {@link #filterByRadius(int, List)} method. Must be done if you intend to invoke {@link #fillCircle(int, int)}.
     * @param radius the radius to initialize this filter with.
     */
    public void init(int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius '" + radius + "' must be larger than zero.");
        }
        if (radiusInitialized == radius) {
            // Already initialized, just reset.
            System.arraycopy(zeros, 0, matrix, 0, matrix.length);
            return;
        }
        this.matrixRowCount = rowCount + radius * 2;
        this.matrixColCount = colCount + radius * 2;
        this.matrix = new boolean[matrixRowCount * matrixColCount];
        this.zeros = new boolean[matrixRowCount * matrixColCount];

        radiusInitialized = radius;
        this.circleStrategy = getStrategy(radius);
    }

    private CircleStrategy getStrategy(int radius) {
        if (radius == 1) {
            return new CircleStrategy1(matrix, matrixColCount);
        }
        if (radius == 2) {
            return new CircleStrategy2(matrix, matrixColCount);
        }
        if (radius == 3) {
            return new CircleStrategy3(matrix, matrixColCount);
        }
        if (radius == 4) {
            return new CircleStrategy4(matrix, matrixColCount);
        }
        if (radius == 5) {
            return new CircleStrategy5(matrix, matrixColCount);
        }
        if (radius == 6) {
            return new CircleStrategy6(matrix, matrixColCount);
        }
        if (radius == 7) {
            return new CircleStrategy7(matrix, matrixColCount);
        }
        if (radius == 8) {
            return new CircleStrategy8(matrix, matrixColCount);
        }
        if (radius == 9) {
            return new CircleStrategy9(matrix, matrixColCount);
        }
        if (radius == 10) {
            return new CircleStrategy10(matrix, matrixColCount);
        }
        return new GenericCircleStrategy(radius, matrix, matrixColCount);
    }

    public void fillCircle(int col, int row) {
        if (circleStrategy == null) {
            throw new IllegalStateException("This filter is not initialized yet, you need to invoke init(radius).");
        }
        circleStrategy.fillCircle(col, row);
    }
}