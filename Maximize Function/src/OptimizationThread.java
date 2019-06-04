/**
 * Thread object class
 * 
 * @author Nic Falcione & Corinne Fair
 *
 */
public class OptimizationThread extends Thread {

    /** Name of the thread to be executed */
    private String name;

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
        } else if (name.equals("eightyTwenty")) {
            algorithm.eightyTwentyAlgorithm();
        } else if (name.equals("eightyTwentyAllReals")) {
            algorithm.eightyTwentyAllRealsAlgorithm();
        } else if (name.equals("twoD")) {
            algorithm.twoDXAlgorithm();
        } else if (name.equals("Random")) {
            algorithm.tryRandomSolution();
        } else {

        }
    }

}
