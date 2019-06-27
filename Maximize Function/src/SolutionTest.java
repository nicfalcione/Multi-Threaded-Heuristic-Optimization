import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Solution Object Test class
 * 
 * @author Nic Falcione & Corrine Fair
 * @version 6/24/2019
 */
class SolutionTest {

    /**
     * Tests to verify Solution constructor throws exception with Integer
     * outside of valid range
     */
    @Test
    void testSolutionInvalidArrayListOfInteger() {
        List<Integer> list = Arrays.asList(-5, -5, 10, 1, 1, 1, 1, 1, 1, 1);
        assertThrows(NumberFormatException.class, () -> {
            new Solution(list);
        });
    }

    /**
     * Tests to verify Solution constructor throws exception with list of
     * incorrect size
     */
    @Test
    void testSolutionInvalidArrayListOfIntegerSize() {
        List<Integer> list = Arrays.asList(-5, -5, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1);
        assertThrows(NumberFormatException.class, () -> {
            new Solution(list);
        });
    }

    /**
     * Tests Solution constructor and randomize solution method
     */
    @Test
    void testRandomSolution() {
        Solution s = new Solution();
        s.randomizeSolution();

        if (s.getxValues().size() > Constants.X_LIST_SIZE) {
            fail("List has " + s.getxValues().size() + " values and not "
                    + Constants.X_LIST_SIZE);
        }

        for (int i = 0; i < s.getxValues().size(); i++) {
            if (s.getxValues().get(i) < Constants.X_MIN
                    || s.getxValues().get(i) > Constants.X_MAX) {
                fail("List has an invalid value.");
            }
        }
    }
}
