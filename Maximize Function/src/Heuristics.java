import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Main Heuristics Algorithm Class
 * 
 * @author Nic Falcione & Corinne Fair
 *
 */
public class Heuristics {

    /** Arraylist of threads */
    private ArrayList<OptimizationThread> threads = new ArrayList<OptimizationThread>();

    /** ArrayList holding most optimal 10 Dimensional solution values */
    private ArrayList<Solution> Urn = new ArrayList<Solution>(
            Constants.URN_SIZE.intValue());

    /** ArrayList holding most optimal 10 Dimensional solution values */
    private ArrayList<RealNumberSolution> realsUrn = new ArrayList<RealNumberSolution>(
            Constants.URN_SIZE.intValue());

    /** ArrayList holding most optimal 2D solution values */
    private ArrayList<TwoDSolution> twoDUrn = new ArrayList<TwoDSolution>();

    /** A randomizer */
    private static Random rand = new Random();

    /**
     * File I/O implementation to write values to given file. Uses same
     * formatting as Solution.toString()
     * 
     * @param s The Solution to write to file
     */
    public void writeToFile(Solution s) {
        try {
            FileWriter fileWriter = new FileWriter(Constants.FILE_PATHNAME,
                    true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            PrintWriter print = new PrintWriter(bw);
            for (int i = 0; i < s.getxValues().size(); i++) {
                if (i != s.getxValues().size() - 1) {
                    print.print(s.getxValues().get(i) + ", ");
                } else {
                    print.print(s.getxValues().get(i));
                }
            }
            print.println("; " + s.getObjectiveVal() + "; "
                    + s.getRandMultiplier() + "; " + s.getIsOptimal());
            print.close();
        } catch (IOException e) {
            System.out.println("An Exception has occured.");
        }
    }

    /**
     * Populates the Urn for the 10 dimensional function algorithm
     */
    public void populateUrn() {
        for (int i = 0; i < Constants.URN_SIZE.intValue(); i++) {
            Urn.add(new Solution());
        }
    }

    /**
     * Populates the Urn for the 10 dimensional function algorithm
     */
    public void populateRealsUrn() {
        for (int i = 0; i < Constants.URN_SIZE.intValue(); i++) {
            realsUrn.add(new RealNumberSolution());
        }
    }

    /**
     * Populates the Urn for the 2D function algorithm
     */
    public void populateTwoDUrn() {
        for (int i = 0; i < Constants.URN_SIZE.intValue(); i++) {
            twoDUrn.add(new TwoDSolution());
        }
    }

    /**
     * Runs the heuristic algorithm for the ten dimensional x function with All
     * real numbers
     * 
     * @return String output to console
     */
    public String eightyTwentyAllRealsAlgorithm() {
//        // Counter for the iterations
        int iteration = 0;

        int itCount = 0;

        double obj = 0.0;

        // Main outer loop for the algorithm
        while (iteration < Constants.MAX_ITERATIONS) {
            RealNumberSolution random = new RealNumberSolution();

            // Randomizes solution on first iterations
            if (iteration == 0) {
                random.randomizeSolution();
            }

            else if (iteration != 0) {
                double r = Math.random();

                if (r >= 0.2) {
                    synchronized (this) {
                        int randIndex = rand.nextInt(
                                ((Constants.URN_SIZE.intValue() - 1) - 0 + 1)
                                        + 0);
                        random = new RealNumberSolution(
                                realsUrn.get(randIndex).getxValues());
                        for (int i = 0; i < Constants.X_LIST_SIZE; i++) {
                            if (Math.abs(realsUrn.get(0).getxValues().get(i)
                                    - random.getxValues()
                                            .get(i)) >= 0.0000001) {
                                double randomX = (Constants.TWO_DIM_X_MIN
                                        + (Constants.TWO_DIM_X_MAX
                                                - Constants.TWO_DIM_X_MIN)
                                                * rand.nextDouble());
                                random.setxValues(i, randomX);
                            }
                        }
                    }
                } else {
                    random.randomizeSolution();
                }
            }

            int count = 0;
            while (count < Constants.MAX_REPS) {
                RealNumberSolution temp = new RealNumberSolution(
                        random.getxValues());
                temp.calcObjectiveVal();
                synchronized (this) {
                    if (realsUrn.get(Constants.URN_SIZE.intValue() - 1)
                            .getObjectiveVal() < temp.getObjectiveVal()) {
                        boolean isSame = false;
                        for (int i = 0; i < Constants.URN_SIZE
                                .intValue(); i++) {
                            if (Math.abs(realsUrn.get(i).getObjectiveVal()
                                    - temp.getObjectiveVal()) < 0.0001) {
                                isSame = true;
                            }
                        }

                        if (isSame) {
                            break;
                        }

                        realsUrn.remove(Constants.URN_SIZE.intValue() - 1);
                        realsUrn.add(temp);
                    }
                }
                synchronized (this) {
                    Collections.sort(realsUrn);
                    Collections.reverse(realsUrn);
                    count++;
                }
            }
//              writeToFile(Urn.get(0));
            iteration++;

            synchronized (this) {
                if (iteration == 0) {
                    obj = realsUrn.get(0).getObjectiveVal();
                } else if (itCount >= Constants.STOP_CONDITION) {
                    break;
                } else {
                    if (Math.abs(realsUrn.get(0).getObjectiveVal()
                            - obj) > 0.0000000001) {
                        obj = realsUrn.get(0).getObjectiveVal();
                        itCount = 0;
                    }
                }
                itCount++;
            }
        }
//        Iterator<Solution> iter = Urn.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next().toString());
//        }
        System.out.println("Took " + iteration + " iterations.");
//        System.out.println(realsUrn.get(0).toString());
        return realsUrn.get(0).toString();
    }

    /**
     * Runs the heuristic algorithm for the ten dimensional x function
     * 
     * @return String output to console
     */
    public String eightyTwentyAlgorithm() {
//        // Counter for the iterations
        int iteration = 0;

        int itCount = 0;

        double obj = 0.0;

        // Main outer loop for the algorithm
        while (iteration < Constants.MAX_ITERATIONS) {
            Solution random = new Solution();

            // Randomizes solution on first iterations
            if (iteration == 0) {
                random.randomizeSolution();
            }

            else if (iteration != 0) {
                double r = Math.random();

                if (r >= 0.2) {
                    synchronized (this) {
                        int randIndex = rand.nextInt(
                                ((Constants.URN_SIZE.intValue() - 1) - 0 + 1)
                                        + 0);
                        random = new Solution(Urn.get(randIndex).getxValues());
                        for (int i = 0; i < Constants.X_LIST_SIZE; i++) {
                            if (Urn.get(0).getxValues().get(i) != random
                                    .getxValues().get(i)) {
                                int randomX = (rand.nextInt(
                                        (Constants.X_MAX - (Constants.X_MIN))
                                                + 1)
                                        + (Constants.X_MIN));
                                random.setxValues(i, randomX);
                            }
                        }
                    }
                } else {
                    random.randomizeSolution();
                }
            }

            int count = 0;
            while (count < Constants.MAX_REPS) {
                Solution temp = new Solution(random.getxValues());
                temp.calcObjectiveVal();
                synchronized (this) {
                    if (Urn.get(Constants.URN_SIZE.intValue() - 1)
                            .getObjectiveVal() < temp.getObjectiveVal()) {
                        boolean isSame = false;
                        for (int i = 0; i < Constants.URN_SIZE
                                .intValue(); i++) {
                            if (Math.abs(Urn.get(i).getObjectiveVal()
                                    - temp.getObjectiveVal()) < 0.01) {
                                isSame = true;
                            }
                        }

                        if (isSame) {
                            break;
                        }

                        Urn.remove(Constants.URN_SIZE.intValue() - 1);
                        Urn.add(temp);
                    }
                }
                synchronized (this) {
                    Collections.sort(Urn);
                    Collections.reverse(Urn);
                    count++;
                }
            }
//              writeToFile(Urn.get(0));
            iteration++;

            // Stopping condition to minimize iteration for efficiency
            synchronized (this) {
                if (iteration == 0) {
                    obj = Urn.get(0).getObjectiveVal();
                } else if (itCount >= Constants.STOP_CONDITION) {
                    break;
                } else {
                    if (Math.abs(Urn.get(0).getObjectiveVal() - obj) > 0.1) {
                        obj = Urn.get(0).getObjectiveVal();
                        itCount = 0;
                    }
                }
                itCount++;
            }
        }
        /*
         * Uncomment to print entire urn at the end of run
         */
//        Iterator<Solution> iter = Urn.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next().toString());
//        }
        System.out.println("Took " + iteration + " iterations.");
//        System.out.println(Urn.get(0).toString());
        return Urn.get(0).toString();
    }

    /**
     * Calculates the acceptance probability for the simulated annealing
     * algorithm
     * 
     * @param currentObjective current objective value
     * @param newObjective     new objective value
     * @param t                temperature
     * @return the acceptance probability
     */
    public double acceptanceProbability(double currentObjective,
            double newObjective, double t) {
        if (currentObjective > newObjective) {
            return 1.0;
        }
        return Math.exp((newObjective - currentObjective) / t);
    }

    /**
     * Just the skeleton code
     * 
     * @return algorithm output
     */
    public String simulatedAnnealingAlgorithm() {
        // Initial Temperature
        double temp = 10000000.0;

        double coolingRate = 0.00001;

        Solution current = new Solution();
        current.randomizeSolution();
        current.calcObjectiveVal();

        Solution best = new Solution(current.getxValues());
        best.calcObjectiveVal();

        int count = 0;
        while (temp > 1) {
            Solution newSolution = new Solution(current.getxValues());
            newSolution.randomizeSolution();
            newSolution.calcObjectiveVal();
            double r = Math.random();

            if (r > 0.5) {
                for (int i = 0; i < Constants.X_LIST_SIZE; i++) {
                    if (best.getxValues().get(i) != newSolution.getxValues()
                            .get(i)) {
                        int randomX = (rand.nextInt(
                                (Constants.X_MAX - (Constants.X_MIN)) + 1)
                                + (Constants.X_MIN));
                        newSolution.setxValues(i, randomX);
                    }
                }
                newSolution.calcObjectiveVal();
            } else {
                newSolution.randomizeSolution();
                newSolution.calcObjectiveVal();
            }

            double rand = Math.random();
            if (this.acceptanceProbability(current.getObjectiveVal(),
                    newSolution.getObjectiveVal(), temp) > rand) {
                current = new Solution(newSolution.getxValues());
                current.calcObjectiveVal();
            }

            if (current.getObjectiveVal() > best.getObjectiveVal()) {
                best = new Solution(current.getxValues());
                best.calcObjectiveVal();
            }

            temp *= 1 - coolingRate;
            count++;
        }
        System.out.println("Took " + count + " iterations.");
        return best.toString();
    }

    /** Algorithm that tries random solutions */
    public void tryRandomSolution() {
        Solution temp = new Solution();

        int iteration = 0;

        while (iteration < Constants.MAX_ITERATIONS) {
            temp.randomizeSolution();
            temp.calcObjectiveVal();
            synchronized (this) {
                if (Urn.get(Constants.URN_SIZE.intValue() - 1)
                        .getObjectiveVal() < temp.getObjectiveVal()) {
                    boolean isSame = false;
                    for (int i = 0; i < Constants.URN_SIZE.intValue(); i++) {
                        if (Math.abs(Urn.get(i).getObjectiveVal()
                                - temp.getObjectiveVal()) < 0.01) {
                            isSame = true;
                        }
                    }

                    if (isSame) {
                        break;
                    }

                    Urn.remove(Constants.URN_SIZE.intValue() - 1);
                    Urn.add(temp);

                    Collections.sort(Urn);
                    Collections.reverse(Urn);
                }
            }
            synchronized (this) {
                iteration++;
            }
        }
    }

    /**
     * Runs the optimization algorithm on the 2D function defined
     * 
     * @return String output to console
     */
    public String twoDXAlgorithm() {
        int iteration = 0;
        while (iteration < Constants.MAX_ITERATIONS) {
            TwoDSolution random = new TwoDSolution();

            if (iteration == 0) {
                random.randomizeSolution();
            }

            else if (iteration != 0) {
                double r = Math.random();

                if (r >= 0.8) {
                    double bestX = twoDUrn.get(0).getX();
                    double delta = 0.000001;

                    double newX = (bestX - delta)
                            + ((bestX + delta) - (bestX - delta))
                                    * rand.nextDouble();
                    random = new TwoDSolution(newX);
                } else {
                    random.randomizeSolution();
                }
            }

            int count = 0;
            while (count < Constants.MAX_REPS) {
                TwoDSolution temp = new TwoDSolution(random.getX());
                temp.calcObjectiveVal();
                if (twoDUrn.get(Constants.URN_SIZE.intValue() - 1)
                        .getObjectiveVal() < temp.getObjectiveVal()) {
                    boolean isSame = false;
                    for (int i = 0; i < Constants.URN_SIZE.intValue(); i++) {
                        if (Math.abs(twoDUrn.get(i).getObjectiveVal()
                                - temp.getObjectiveVal()) < 0.00000001) {
                            isSame = true;
                        }
                    }

                    if (isSame) {
                        break;
                    }
                    twoDUrn.remove(Constants.URN_SIZE.intValue() - 1);
                    twoDUrn.add(temp);
                }

                Collections.sort(twoDUrn);
                Collections.reverse(twoDUrn);
                count++;
            }
//              writeToFile(twoDUrn.get(0));
            iteration++;
        }
//        Iterator<TwoDSolution> iter = twoDUrn.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next().toString());
//        }
        return twoDUrn.get(0).toString();
    }

    /**
     * Method to initialize threads
     */
    public void initializeThreads() {

        // Create threads
        for (int i = 0; i < Constants.NUM_THREADS; i++) {
            threads.add(new OptimizationThread(
                    Constants.EIGHTY_TWENTY_ALL_REALS, this));
        }

        // Start threads
        for (Thread t : threads) {
            t.start();
        }

        // Try to join threads
        try {
            for (int i = 0; i < threads.size(); i++) {
                threads.get(i).join();
                System.out.println("Thread " + (i + 1) + " has finished.");
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getClass());
//            e.printStackTrace();
        }
    }

    /**
     * Main method to run the heuristic algorithms
     * 
     * @param args String arguments
     */
    public static void main(String[] args) {
        // Keep track of start time
        long startTime = System.nanoTime();

        Heuristics h = new Heuristics();
//        h.populateUrn();
        h.populateRealsUrn();
        h.initializeThreads();
//        h.populateTwoDUrn();

//        String r = h.twoDXAlgorithm();
//        String result = h.eightyTwentyAlgorithm();
//        String resultTwo = h.simulatedAnnealingAlgorithm();
//        String realsResult = h.eightyTwentyAllRealsAlgorithm();

//        String tResult = h.Urn.get(0).toString();
        String tAllRealsResult = h.realsUrn.get(0).toString();

//        System.out.println("Algorithm optimal found: " + r);
//        System.out.println("Algorithm optimal found: " + result);
//        System.out.println("Algorithm optimal found: " + resultTwo);
//        System.out.println("Algorithm optimal found: " + tResult);
//        System.out.println("Algorithm optimal found: " + realsResult);
        System.out.println("Algorithm optimal found: " + tAllRealsResult);

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(
                "Program took " + ((double) totalTime / (Math.pow(10, 9)))
                        + " seconds to run.");
    }
}
