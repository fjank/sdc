package io.github.fjank.sdc;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
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
public class KeyPointFilterBenchmark2 {
    /**
     * The actual benchmark for the filter. filters the list to get the best ~1500 points.
     * This should at least keep a speed of ~1000 ops/sec.
     * @param state the benchmark state object that contains the filter and the list.
     * @return the filtered list.
     */
    @Benchmark
    public List<KeyPoint> benchmarkFilterByRadius(KeyPointFilterState state) {
        List<KeyPoint> returnValue = new ArrayList<>();
        int radius = 12;
        while (returnValue.size() < 1000) {
            returnValue = state.filter.filterByRadius(radius, state.list);
            radius -= 2;
            if (radius == 0) {
                break;
            }
        }
        return returnValue;

    }

    /** The state object for this benchmark, that keeps the list of points in memory. */
    @State(Scope.Thread)
    public static class KeyPointFilterState {
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
            for (int i = 0; i < 3000; i++) {
                int x = (int) (Math.random() * 640);
                int y = (int) (Math.random() * 480);
                float response = (float) (Math.random() * RESPONSE_RANGE);
                list.add(new DefaultKeyPoint(x, y, response));
            }
            this.filter = new KeyPointFilter(640, 480);
        }
    }
}
