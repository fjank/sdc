package io.github.fjank.sdc;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Since the keypoint filter is designed to optimize FAST Feature Detector points, this needs to run fast as
 * possible.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com
 */
public class KeyPointFilterBenchmark {
    /**
     * The actual benchmark for the filter. filters the list to get the best ~1500 points.
     * This should at least keep a speed of ~1000 ops/sec.
     * @param state the benchmark state object that contains the filter and the list.
     * @return the filtered list.
     */
    //@Benchmark
    public List<KeyPoint> benchmarkFilterByRadius(KeyPointFilterState state) {
        return state.filter.filterByRadius(state.radius, state.list);

    }

    /** The state object for this benchmark, that keeps the list of points in memory. */
    @State(Scope.Thread)
    public static class KeyPointFilterState {
        @Param({"5", "10", "15", "20"})
        private int radius;
        @Param({"512", "1024", "2048", "4096"})
        private int matrixSize;

        private static final int NUMBER_OF_POINTS = 12_000;
        private static final int RESPONSE_RANGE = 255;
        private List<KeyPoint> list;
        private KeyPointFilter filter;

        /**
         * Create a list of 12.000 points with random x/y/response values. We simulate 12000 points on a 640x480
         * image.
         */
        @Setup(Level.Trial)
        public void doSetup() {
            this.list = new ArrayList<>();
            for (int i = 0; i < NUMBER_OF_POINTS; i++) {
                int x = (int) (Math.random() * matrixSize);
                int y = (int) (Math.random() * matrixSize);
                float response = (float) (Math.random() * RESPONSE_RANGE);
                list.add(new DefaultKeyPoint(x, y, response));
            }
            this.filter = new KeyPointFilter(matrixSize, matrixSize);
        }
    }
}
