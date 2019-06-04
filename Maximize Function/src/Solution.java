import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Solution Object Class
 * 
 * @author Nic Falcione & Corinne Fair
 *
 */
public class Solution implements Comparable<Solution> {

    /** The ArrayList of xValues */
    private ArrayList<Integer> xValues = new ArrayList<Integer>();

    /** The objective value */
    private double objectiveVal;

    /** A randomizer */
    private Random rand = new Random();

    /** The random multiplier */
    private double randMultiplier = 1.2;

    /** The optimal value */
    private int isOptimal = 0;

    /**
     * Default Constructor Initializes all X values to -5, objectiveVal to
     * minimum value
     */
    public Solution() {
        // Populating X value list with -5
        for (int i = 0; i < Constants.X_LIST_SIZE; i++) {
            xValues.add(new Integer(-5));
        }
        // Setting objective value to -10^307
        objectiveVal = (-Math.pow(10, 307));
//        randMultiplier = (rand.nextInt((120 - 80) + 1) + 80) / 100.0;
    }

    /**
     * Solution constructor to create solution with a given set of X's within
     * set of X values to fill list of set size, defaults objective value to 1
     * 
     * @param list The given set of X values
     * @throws NumberFormatException If the given list is not within the list
     *                               size or if an element is not within the
     *                               range
     */
    public Solution(List<Integer> list) throws NumberFormatException {
        // Check that given list size == accepted list size
        if (list.size() != Constants.X_LIST_SIZE) {
            throw new NumberFormatException("List was not "
                    + Constants.X_LIST_SIZE + " but was " + list.size());
        }
        // Check that each element is within bounds, if yes add element
        for (Integer i : list) {
            if (i < Constants.X_MIN || i > Constants.X_MAX) {
                throw new NumberFormatException("x[" + i
                        + "] is not within the range [" + Constants.X_MIN + ","
                        + Constants.X_MAX + "]");
            }
            xValues.add(i);
        }
//        randMultiplier = (rand.nextInt((120 - 80) + 1) + 80) / 100.0;
        // Pre calculation objective value
        objectiveVal = 1.0;
    }

    /**
     * Gets this Solution's random multiplier
     * 
     * @return the random multiplier
     */
    public double getRandMultiplier() {
        return randMultiplier;
    }

    /**
     * Gets this Solution's objective value
     * 
     * @return The objective value
     */
    public double getObjectiveVal() {
        return objectiveVal;
    }

    /**
     * Calculates this Solution's objective value and initializes random value
     * Formula: Summation i = 0 - 9, a(2i) * x(i) + a(2i+1) * x(i)^2
     */
    public void calcObjectiveVal() {
        objectiveVal = 0.0;
        // Count for a vector
        int count = 0;
        for (int i = 0; i < Constants.X_LIST_SIZE; i++) {
            // a(2i) * x(i)
            objectiveVal += (Constants.A_VALUES.get(count) * xValues.get(i));
            count++;
            // + a(2i+1) * x(i)^2
            objectiveVal += (Constants.A_VALUES.get(count)
                    * Math.pow(xValues.get(i), 2));
            count++;
        }
        // Set random multiplier between .8 and 1.2
//        randMultiplier = (rand.nextInt((120 - 80) + 1) + 80) / 100.0;
        // Multiply objective value by randMultiplier
        objectiveVal *= randMultiplier;
    }

    /**
     * Sets x values to a random set within list size and list range
     */
    public void randomizeSolution() {
        xValues.clear();
        for (int i = 0; i < Constants.X_LIST_SIZE; i++) {
            // Adds random x in range of x min and x max
            xValues.add(rand.nextInt((Constants.X_MAX - (Constants.X_MIN)) + 1)
                    + (Constants.X_MIN));
        }
    }

    /**
     * Gets this Solution's X Values
     * 
     * @return An ArrayList of the x values
     */
    public ArrayList<Integer> getxValues() {
        return xValues;
    }

    /**
     * Sets this Solution's X Value in an index
     * 
     */
    public void setxValues(int i, int x) {
        xValues.set(i, x);
    }

    /**
     * Returns this Solution's isOptimal value
     * 
     * @return the optimal value
     */
    public int getIsOptimal() {
        return isOptimal;
    }

    /**
     * Sets this Solution's isOptimal value
     * 
     * @param b true if optimal, false otherwise
     */
    public void setIsOptimal(boolean b) {
        if (b) {
            this.isOptimal = 1;
        } else {
            this.isOptimal = 0;
        }
    }

    /**
     * Returns this Solution in the following format: "X Values: [List of X
     * Values];\nObjective Value: objVal; Rand Multiplier: randMult; isOptimal"
     * 
     * @return The String value of this Solution
     */
    public String toString() {
        String s = "X Values: ";

        for (int i = 0; i < getxValues().size(); i++) {
            if (i != getxValues().size() - 1) {
                s += (getxValues().get(i) + ", ");
            } else {
                s += (getxValues().get(i));
            }
        }
        s += (";\nObjective Value: " + getObjectiveVal() + "; Rand Multiplier: "
                + getRandMultiplier() + "; " + getIsOptimal());
        return s;
    }

    /**
     * Compares two Solutions
     * 
     * @return -1 if this Solution is less than other solution, 1 if this
     *         Solution is greater than other Solution, 0 if they are equal
     */
    public int compareTo(Solution o) {
        if (this.objectiveVal - o.objectiveVal < 0) {
            return -1;
        } else if (this.objectiveVal - o.objectiveVal > 0) {
            return 1;
        }
        return 0;
    }
}
