import java.util.ArrayList;

/**
 * Thread object class for each algorithm
 * 
 * @author Nic Falcione & Corinne Fair
 * @version 6/24/2019
 */
public class OptimizationThread extends Thread {

    /** Name of the thread to be executed */
    private String name;

    /** ArrayList holding Real Number solutions */
    private ArrayList<RealNumberSolution> threadUrn = new ArrayList<RealNumberSolution>();

    /** Heuristics object */
    private Heuristics algorithm;

    /**
     * Constructor to initialize a new Optimization Thread
     * 
     * @param s Name of the thread
     * @param h Heuristics Object
     */
    public OptimizationThread(String s, Heuristics h) {
        name = s;
        algorithm = h;
    }

    /**
     * Inherited runnable method to be called when the thread is started
     */
    public void run() {
        if (name.equals("SA")) {
            algorithm.simulatedAnnealingAlgorithm();
        } else if (name.equals(Constants.EIGHTY_TWENTY)) {
            algorithm.eightyTwentyAlgorithm();
        } else if (name.equals(Constants.EIGHTY_TWENTY_ALL_REALS)) {

            algorithm.eightyTwentyAllRealsAlgorithm();
        } else if (name.equals(Constants.EIGHTY_TWENTY_ALL_REALS_MULTI_URNS)) {
            for (int i = 0; i < Constants.URN_SIZE; i++) {
                threadUrn.add(new RealNumberSolution());
            }
            algorithm.eightyTwentyAllRealsAlgorithm(threadUrn);
        } else if (name.equals(Constants.TWO_DIMENSION)) {
            algorithm.twoDXAlgorithm();
        } else if (name.equals(Constants.RANDOM)) {
            algorithm.tryRandomSolution();
        } else if (name.equals(Constants.RANDOM_REALS)) {
            algorithm.tryRandomRealsSolution();
        } else {
            System.out.println("Invalid input given.");
        }
    }

}
