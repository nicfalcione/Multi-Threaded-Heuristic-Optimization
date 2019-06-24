import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Constants file
 * 
 * @author Nic Falcione & Corinne Fair
 */
public class Constants {

    /** Two dimensional function threead algorithm name */
    public static final String TWO_DIMENSION = "twoD";

    /** Eighty twenty algorithm thread name */
    public static final String EIGHTY_TWENTY = "eightyTwenty";

    /** Eighty twenty all reals algorithm thread name with multiple urns */
    public static final String EIGHTY_TWENTY_ALL_REALS_MULTI_URNS = "eightyTwentyAllRealsMultiUrns";

    /** Random algorithm thread name */
    public static final String RANDOM = "Random";

    /** Random reals algorithm thread name */
    public static final String RANDOM_REALS = "RandomReals";

    /** Eighty twenty all reals algorithm thread name */
    public static final String EIGHTY_TWENTY_ALL_REALS = "eightyTwentyAllReals";

    /** Output File path - MUST BE CHANGED TO LOCAL MACHINE PATH */
    public static final String FILE_PATHNAME = "C:/Users/NOF00/eclipse-workspace/Maximize Function/output.txt";

    /** Output File */
    public static File FILE = new File(FILE_PATHNAME);

    /**
     * Max number of repetitions evaluating solutions of one set of x values -
     * ideally greater than or equal to urn size
     */
    public static final int MAX_REPS = 1;

    /** Max number of iterations of the entire algorithm */
    public static long MAX_ITERATIONS = 25000;

    /**
     * Size of urn holding top optimal solutions - ideally less than or equal to
     * max reps
     */
    public static final int URN_SIZE = 100;

    /** Range of possible x values */
    public static final List<Integer> X_RANGE = Arrays.asList(-5, -4, -3, -2,
            -1, 0, 1, 2, 3, 4, 5);

    /** Minimum x value */
    public static final int X_MIN = -5;

    /** Maximum x value */
    public static final int X_MAX = 5;

    /** Minimum x value */
    public static final double TWO_DIM_X_MIN = -5.0;

    /** Maximum x value */
    public static final double TWO_DIM_X_MAX = 5.0;

    /** Size of x value list */
    public static final int X_LIST_SIZE = 10;

    /** List of hardcoded Coefficient values for the 2D Function optimization */
    public static final List<Double> TWOD_COEFFICIENTS = Arrays
            .asList((1.0 / 3.0), (1.0 / 2.0), -12.0);

    /** Hardcoded a values */
    public static final List<Integer> A_VALUES_TWO = Arrays.asList(3, 4, 2, -4,
            0, 1, 3, -3, 5, 5, 4, -1, -4, 4, 2, 3, 3, -4, 0, -2);

    /** Hardcoded a values */
    public static final List<Integer> A_VALUES = Arrays.asList(3, 4, -4, -4, 0,
            -1, 3, -3, 5, 5, 4, -3, -4, 4, 2, 3, 3, -4, -1, -2);

    /** Constants for number of threads to be run */
    public static int NUM_THREADS = 1;

    /** Stopping condition when optimal is found for all threads */
    public static long STOP_CONDITION = 10000;

    public static String THREAD_TYPE = "Random";
}
