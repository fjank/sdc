package io.github.fjank.sdc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * Tests for KeyPointFilter.
 * @author Frank Karlstrøm - frank.karlstrom@gmail.com - fk@databaseforum.no
 */
public class KeyPointFilterTest {

    /** Tests the fillCircle by using position 5, 5 radius 3. Should get a nice circle in the center of the grid. */
    @Test
    public void fillCircleCenter() {
        // Setup
        KeyPointFilter filter = new KeyPointFilter(10, 10);

        // Act
        filter.init(3);
        filter.fillCircle(5, 5);

        // Assert
        String expectedResult = ""
            + "0000000000\n"
            + "0000000000\n"
            + "0000110000\n"
            + "0001111000\n"
            + "0011111100\n"
            + "0011111100\n"
            + "0011111100\n"
            + "0001111000\n"
            + "0000110000\n"
            + "0000000000\n";

        assertMatrixResult(expectedResult, filter);
    }

    /** Tests the fillCircle by using position 0, 0 radius 5. Should get an arc in the top left corner. */
    @Test
    // The PMD warnings can be ignored, it's done this way for readability of the test.
    @SuppressWarnings(value = {"PMD.AddEmptyString", "PMD.AvoidDuplicateLiterals"})
    public void fillCircleTopLeft() {
        // Setup
        KeyPointFilter filter = new KeyPointFilter(10, 10);

        // Act
        // CHECKSTYLE IGNORE MagicNumber FOR NEXT 2 LINES
        filter.init(5);
        filter.fillCircle(0, 0);

        // Assert
        String expectedResult = ""
            + "1111100000\n"
            + "1111100000\n"
            + "1111100000\n"
            + "1111000000\n"
            + "1111000000\n"
            + "1100000000\n"
            + "0000000000\n"
            + "0000000000\n"
            + "0000000000\n"
            + "0000000000\n";

        assertMatrixResult(expectedResult, filter);
    }

    /** Tests the fillCircle by using position 0, 9 radius 5. Should get an arc in the top right corner. */
    @Test
    // The PMD warnings can be ignored, it's done this way for readability of the test.
    @SuppressWarnings(value = {"PMD.AddEmptyString", "PMD.AvoidDuplicateLiterals"})
    public void fillCircleTopRight() {
        // Setup
        KeyPointFilter filter = new KeyPointFilter(10, 10);

        // Act
        // CHECKSTYLE IGNORE MagicNumber FOR NEXT 2 LINES
        filter.init(5);
        filter.fillCircle(9, 0);

        // Assert
        String expectedResult = ""
            + "0000111111\n"
            + "0000111111\n"
            + "0000111111\n"
            + "0000011111\n"
            + "0000011111\n"
            + "0000000111\n"
            + "0000000000\n"
            + "0000000000\n"
            + "0000000000\n"
            + "0000000000\n";

        assertMatrixResult(expectedResult, filter);
    }

    /** Tests the fillCircle by using position 9, 9 radius 5. Should get an arc in the bottom right corner. */
    @Test
    // The PMD warnings can be ignored, it's done this way for readability of the test.
    @SuppressWarnings(value = {"PMD.AddEmptyString", "PMD.AvoidDuplicateLiterals"})
    public void fillCircleBottomRight() {
        // Setup
        KeyPointFilter filter = new KeyPointFilter(10, 10);

        // Act
        // CHECKSTYLE IGNORE MagicNumber FOR NEXT 2 LINES
        filter.init(5);
        filter.fillCircle(9, 9);

        // Assert
        String expectedResult = ""
            + "0000000000\n"
            + "0000000000\n"
            + "0000000000\n"
            + "0000000000\n"
            + "0000000111\n"
            + "0000011111\n"
            + "0000011111\n"
            + "0000111111\n"
            + "0000111111\n"
            + "0000111111\n";

        assertMatrixResult(expectedResult, filter);
    }

    /** Tests the fillCircle by using position 0, 9 radius 5. Should get an arc in the bottom left corner. */
    @Test
    public void fillCircleBottomLeft() {
        // Setup
        KeyPointFilter filter = new KeyPointFilter(10, 10);

        // Act
        filter.init(5);
        filter.fillCircle(0, 9);

        // Assert
        String expectedResult = ""
            + "0000000000\n"
            + "0000000000\n"
            + "0000000000\n"
            + "0000000000\n"
            + "1100000000\n"
            + "1111000000\n"
            + "1111000000\n"
            + "1111100000\n"
            + "1111100000\n"
            + "1111100000\n";

        assertMatrixResult(expectedResult, filter);
    }

    /**
     * Tests the fillCircle by using position 5, 5 radius 3 and 0, 0 radius 5. Should get a combination of two
     * circles. (UNION).
     */
    @Test
    public void fillCircleCombined() {
        // Setup
        KeyPointFilter filter = new KeyPointFilter(10, 10);

        // Act
        filter.init(4);
        filter.fillCircle(5, 5);
        filter.fillCircle(0, 0);

        // Assert
        String expectedResult = ""
            + "1111000000\n"
            + "1111111000\n"
            + "1111111100\n"
            + "1111111110\n"
            + "1111111110\n"
            + "0111111110\n"
            + "0111111110\n"
            + "0111111110\n"
            + "0011111100\n"
            + "0001111000\n";

        assertMatrixResult(expectedResult, filter);
    }

    @Test
    public void fillCircle1() {
        // Setup
        KeyPointFilter filter = new KeyPointFilter(4, 3);

        // Act
        filter.init(1);
        filter.fillCircle(2, 1);

        // Assert
        String expectedResult = ""
            + "0000\n"
            + "0110\n"
            + "0000\n";

        assertMatrixResult(expectedResult, filter);
    }

    @Test
    public void fillCircle2() {
        // Setup
        KeyPointFilter filter = new KeyPointFilter(6, 5);

        // Act
        filter.init(2);
        filter.fillCircle(3, 2);

        // Assert
        String expectedResult = ""
            + "000000\n"
            + "001100\n"
            + "011110\n"
            + "001100\n"
            + "000000\n";

        assertMatrixResult(expectedResult, filter);
    }

    @Test
    public void fillCircle3() {
        // Setup
        KeyPointFilter filter = new KeyPointFilter(8, 9);

        // Act
        filter.init(3);
        filter.fillCircle(4, 4);

        // Assert
        String expectedResult = ""
            + "00000000\n"
            + "00011000\n"
            + "00111100\n"
            + "01111110\n"
            + "01111110\n"
            + "01111110\n"
            + "00111100\n"
            + "00011000\n"
            + "00000000\n";

        assertMatrixResult(expectedResult, filter);
    }

    @Test
    public void fillCircle4() {
        // Setup
        KeyPointFilter filter = new KeyPointFilter(10, 11);

        // Act
        filter.init(4);
        filter.fillCircle(5, 5);

        // Assert
        String expectedResult = ""
            + "0000000000\n"
            + "0001111000\n"
            + "0011111100\n"
            + "0111111110\n"
            + "0111111110\n"
            + "0111111110\n"
            + "0111111110\n"
            + "0111111110\n"
            + "0011111100\n"
            + "0001111000\n"
            + "0000000000\n";

        assertMatrixResult(expectedResult, filter);
    }

    @Test
    public void fillCircle5() {
        // Setup
        KeyPointFilter filter = new KeyPointFilter(12, 13);

        // Act
        filter.init(5);
        filter.fillCircle(6, 6);

        // Assert
        String expectedResult = ""
            + "000000000000\n"
            + "000011110000\n"
            + "001111111100\n"
            + "001111111100\n"
            + "011111111110\n"
            + "011111111110\n"
            + "011111111110\n"
            + "011111111110\n"
            + "011111111110\n"
            + "001111111100\n"
            + "001111111100\n"
            + "000011110000\n"
            + "000000000000\n";

        assertMatrixResult(expectedResult, filter);
    }

    @Test
    public void fillCircle6() {
        // Setup
        KeyPointFilter filter = new KeyPointFilter(14, 15);

        // Act
        filter.init(6);
        filter.fillCircle(7, 7);

        // Assert
        String expectedResult = ""
            + "00000000000000\n"
            + "00000111100000\n"
            + "00011111111000\n"
            + "00111111111100\n"
            + "00111111111100\n"
            + "01111111111110\n"
            + "01111111111110\n"
            + "01111111111110\n"
            + "01111111111110\n"
            + "01111111111110\n"
            + "00111111111100\n"
            + "00111111111100\n"
            + "00011111111000\n"
            + "00000111100000\n"
            + "00000000000000\n";

        assertMatrixResult(expectedResult, filter);
    }

    @Test
    public void fillCircle7() {
        // Setup
        KeyPointFilter filter = new KeyPointFilter(16, 17);

        // Act
        filter.init(7);
        filter.fillCircle(8, 8);

        // Assert
        String expectedResult = ""
            + "0000000000000000\n"
            + "0000011111100000\n"
            + "0001111111111000\n"
            + "0011111111111100\n"
            + "0011111111111100\n"
            + "0111111111111110\n"
            + "0111111111111110\n"
            + "0111111111111110\n"
            + "0111111111111110\n"
            + "0111111111111110\n"
            + "0111111111111110\n"
            + "0111111111111110\n"
            + "0011111111111100\n"
            + "0011111111111100\n"
            + "0001111111111000\n"
            + "0000011111100000\n"
            + "0000000000000000\n";

        assertMatrixResult(expectedResult, filter);
    }

    @Test
    public void fillCircle8() {
        // Setup
        KeyPointFilter filter = new KeyPointFilter(18, 19);

        // Act
        filter.init(8);
        filter.fillCircle(9, 9);

        // Assert
        String expectedResult = ""
            + "000000000000000000\n"
            + "000000111111000000\n"
            + "000011111111110000\n"
            + "000111111111111000\n"
            + "001111111111111100\n"
            + "001111111111111100\n"
            + "011111111111111110\n"
            + "011111111111111110\n"
            + "011111111111111110\n"
            + "011111111111111110\n"
            + "011111111111111110\n"
            + "011111111111111110\n"
            + "011111111111111110\n"
            + "001111111111111100\n"
            + "001111111111111100\n"
            + "000111111111111000\n"
            + "000011111111110000\n"
            + "000000111111000000\n"
            + "000000000000000000\n";

        assertMatrixResult(expectedResult, filter);
    }

    @Test
    public void fillCircle9() {
        // Setup
        KeyPointFilter filter = new KeyPointFilter(20, 21);

        // Act
        filter.init(9);
        filter.fillCircle(10, 10);

        // Assert
        String expectedResult = ""
            + "00000000000000000000\n"
            + "00000001111110000000\n"
            + "00000111111111100000\n"
            + "00011111111111111000\n"
            + "00011111111111111000\n"
            + "00111111111111111100\n"
            + "00111111111111111100\n"
            + "01111111111111111110\n"
            + "01111111111111111110\n"
            + "01111111111111111110\n"
            + "01111111111111111110\n"
            + "01111111111111111110\n"
            + "01111111111111111110\n"
            + "01111111111111111110\n"
            + "00111111111111111100\n"
            + "00111111111111111100\n"
            + "00011111111111111000\n"
            + "00011111111111111000\n"
            + "00000111111111100000\n"
            + "00000001111110000000\n"
            + "00000000000000000000\n";

        assertMatrixResult(expectedResult, filter);
    }

    @Test
    public void fillCircle10() {
        // Setup
        KeyPointFilter filter = new KeyPointFilter(22, 23);

        // Act
        filter.init(10);
        filter.fillCircle(11, 11);

        // Assert
        String expectedResult = ""
            + "0000000000000000000000\n"
            + "0000000011111100000000\n"
            + "0000011111111111100000\n"
            + "0000111111111111110000\n"
            + "0001111111111111111000\n"
            + "0011111111111111111100\n"
            + "0011111111111111111100\n"
            + "0011111111111111111100\n"
            + "0111111111111111111110\n"
            + "0111111111111111111110\n"
            + "0111111111111111111110\n"
            + "0111111111111111111110\n"
            + "0111111111111111111110\n"
            + "0111111111111111111110\n"
            + "0111111111111111111110\n"
            + "0011111111111111111100\n"
            + "0011111111111111111100\n"
            + "0011111111111111111100\n"
            + "0001111111111111111000\n"
            + "0000111111111111110000\n"
            + "0000011111111111100000\n"
            + "0000000011111100000000\n"
            + "0000000000000000000000\n";

        assertMatrixResult(expectedResult, filter);
    }

    /** Tests the fillCircle by using a big circle. */
    @Test
    public void fillCircle20() {
        // Setup
        KeyPointFilter filter = new KeyPointFilter(50, 50);

        // Act
        filter.init(20);
        filter.fillCircle(25, 25);

        // Assert
        String expectedResult = ""
            + "00000000000000000000000000000000000000000000000000\n"
            + "00000000000000000000000000000000000000000000000000\n"
            + "00000000000000000000000000000000000000000000000000\n"
            + "00000000000000000000000000000000000000000000000000\n"
            + "00000000000000000000000000000000000000000000000000\n"
            + "00000000000000000000111111111100000000000000000000\n"
            + "00000000000000000111111111111111100000000000000000\n"
            + "00000000000000011111111111111111111000000000000000\n"
            + "00000000000001111111111111111111111110000000000000\n"
            + "00000000000011111111111111111111111111000000000000\n"
            + "00000000001111111111111111111111111111110000000000\n"
            + "00000000001111111111111111111111111111110000000000\n"
            + "00000000011111111111111111111111111111111000000000\n"
            + "00000000111111111111111111111111111111111100000000\n"
            + "00000000111111111111111111111111111111111100000000\n"
            + "00000001111111111111111111111111111111111110000000\n"
            + "00000001111111111111111111111111111111111110000000\n"
            + "00000011111111111111111111111111111111111111000000\n"
            + "00000011111111111111111111111111111111111111000000\n"
            + "00000011111111111111111111111111111111111111000000\n"
            + "00000111111111111111111111111111111111111111100000\n"
            + "00000111111111111111111111111111111111111111100000\n"
            + "00000111111111111111111111111111111111111111100000\n"
            + "00000111111111111111111111111111111111111111100000\n"
            + "00000111111111111111111111111111111111111111100000\n"
            + "00000111111111111111111111111111111111111111100000\n"
            + "00000111111111111111111111111111111111111111100000\n"
            + "00000111111111111111111111111111111111111111100000\n"
            + "00000111111111111111111111111111111111111111100000\n"
            + "00000111111111111111111111111111111111111111100000\n"
            + "00000111111111111111111111111111111111111111100000\n"
            + "00000011111111111111111111111111111111111111000000\n"
            + "00000011111111111111111111111111111111111111000000\n"
            + "00000011111111111111111111111111111111111111000000\n"
            + "00000001111111111111111111111111111111111110000000\n"
            + "00000001111111111111111111111111111111111110000000\n"
            + "00000000111111111111111111111111111111111100000000\n"
            + "00000000111111111111111111111111111111111100000000\n"
            + "00000000011111111111111111111111111111111000000000\n"
            + "00000000001111111111111111111111111111110000000000\n"
            + "00000000001111111111111111111111111111110000000000\n"
            + "00000000000011111111111111111111111111000000000000\n"
            + "00000000000001111111111111111111111110000000000000\n"
            + "00000000000000011111111111111111111000000000000000\n"
            + "00000000000000000111111111111111100000000000000000\n"
            + "00000000000000000000111111111100000000000000000000\n"
            + "00000000000000000000000000000000000000000000000000\n"
            + "00000000000000000000000000000000000000000000000000\n"
            + "00000000000000000000000000000000000000000000000000\n"
            + "00000000000000000000000000000000000000000000000000\n";

        assertMatrixResult(expectedResult, filter);
    }

    /** Creates a String representation of the matrix (0 and 1), and compares with the expected result. */
    private void assertMatrixResult(String expectedResult, KeyPointFilter filter) {
        StringBuilder actualResult = new StringBuilder();
        for (int row = 0; row < filter.getRowCount(); row++) {
            for (int col = 0; col < filter.getColCount(); col++) {
                actualResult.append(filter.isSet(col, row) ? '1' : '0');
            }
            actualResult.append('\n');
        }
        assertThat(actualResult.toString()).isEqualTo(expectedResult);
    }

    /** Tests that the sorting is behaving correct, largest response should come first in the list. */
    @Test
    public void sortKeyPointsByResponse() {
        // Setup
        KeyPointFilter filter = new KeyPointFilter(10, 10);
        List<KeyPoint> list = new ArrayList<>();
        KeyPoint seven = new DefaultKeyPoint(0, 0, 7);
        KeyPoint one = new DefaultKeyPoint(0, 0, 1);
        KeyPoint hundred = new DefaultKeyPoint(0, 0, 100);
        KeyPoint zero = new DefaultKeyPoint(0, 0, 0);
        KeyPoint fourteen = new DefaultKeyPoint(0, 0, 14);
        list.add(seven);
        list.add(one);
        list.add(hundred);
        list.add(zero);
        list.add(fourteen);

        // Act
        filter.sortPointsByResponse(list);

        // Assert - assert that the list is now indeed sorted from highest to lowest.
        assertThat(list).containsExactly(hundred, fourteen, seven, one, zero).inOrder();
    }
    /*===== NEGATIVE TESTS ===========================================================================================*/

    /** Checks that fillCircle without init will cause an exception. */
    @Test(expected = IllegalStateException.class)
    public void fillCircleWithoutInit() {
        KeyPointFilter filter = new KeyPointFilter(10, 10);
        filter.fillCircle(2, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newFilterZeroCols() {
        new KeyPointFilter(0, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newFilterZeroRows() {
        new KeyPointFilter(10, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newFilterNegativeCols() {
        new KeyPointFilter(-5, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newFilterNegativeRows() {
        new KeyPointFilter(10, -5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void initNegativeRadius() {
        KeyPointFilter filter = new KeyPointFilter(10, 10);
        filter.init(-5);
    }

    /** null list should be supported, nothing should happen. */
    @Test
    public void sortNullList() {
        KeyPointFilter filter = new KeyPointFilter(10, 10);
        filter.sortPointsByResponse(null);
    }
}