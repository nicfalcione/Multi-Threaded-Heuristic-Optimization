import java.util.Random;

/**
 * Two Dimensional Solution object class
 * 
 * @author Nic Falcione & Corrine Fair
 * @version 6/24/2019
 */
public class TwoDSolution implements Comparable<TwoDSolution> {

    /** The objective value */
    private double objectiveVal;

    /** A randomizer */
    private Random rand = new Random();

    /** The X value to be used to calculate and optimize the objective value */
    private double xVal;

    /**
     * Default Contructor for a 2D solution object
     */
    public TwoDSolution() {
        xVal = Constants.X_MIN
                + (Constants.X_MAX - (Constants.X_MIN)) * rand.nextDouble();
        objectiveVal = 1.0;
    }

    /**
     * Constructor to initialize a 2D solution object witha specified X value
     * 
     * @param x X value
     */
    public TwoDSolution(double x) {
        xVal = x;
        objectiveVal = 1.0;
    }

    /**
     * Calculates objective value of the 2D Solution
     */
    public void calcObjectiveVal() {
        objectiveVal = (Constants.TWOD_COEFFICIENTS.get(0) * Math.pow(xVal, 3))
                + (Constants.TWOD_COEFFICIENTS.get(1) * Math.pow(xVal, 2))
                + (Constants.TWOD_COEFFICIENTS.get(2) * xVal);
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
     * Getter for the solution's x value
     * 
     * @return the x value
     */
    public double getX() {
        return xVal;
    }

    /**
     * setter for the solution's x value
     */
    public void setX(double x) {
        xVal = x;
    }

    /**
     * Sets x value to a random value within list size and list range
     */
    public void randomizeSolution() {
        xVal = Constants.TWO_DIM_X_MIN
                + (Constants.TWO_DIM_X_MAX - Constants.TWO_DIM_X_MIN)
                        * rand.nextDouble();
    }

    /**
     * Returns this Solution in the following format: "X Value: xVal; Objective
     * Value: objVal;"
     * 
     * @return The String value of this Solution
     */
    public String toString() {
        String s = "X Value: " + xVal + " ";

        s += (";\nObjective Value: " + getObjectiveVal());
        return s;
    }

    /**
     * Compares two Solutions
     * 
     * @return -1 if this Solution is less than other solution, 1 if this
     *         Solution is greater than other Solution, 0 if they are equal
     */
    public int compareTo(TwoDSolution o) {
        if (this.objectiveVal - o.objectiveVal < 0) {
            return -1;
        } else if (this.objectiveVal - o.objectiveVal > 0) {
            return 1;
        }
        return 0;
    }
}
