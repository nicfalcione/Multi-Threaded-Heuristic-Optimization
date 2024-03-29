import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * The front end Swing frame for the heuristics program
 * 
 * @author Nic Falcione
 * @version 6/24/19
 */
@SuppressWarnings("serial")
public class HeuristicFrame extends JFrame {

    /**
     * Constructor to begin a new GUI Frame
     */
    public HeuristicFrame() {
        setTitle("Heuristic Optimization Demo");
        setSize(900, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Font font = new Font("TimesRoman", Font.BOLD, 24);

        JButton calculate = new JButton("Calculate");
        calculate.setFont(font);

        JLabel threads = new JLabel("Thread Count: ");
        threads.setFont(font);
        JTextField userThreads = new JTextField();
        userThreads.setFont(font);
        JLabel threadType = new JLabel("Thread type? ");
        threadType.setFont(font);

        String[] threadTypes = new String[] { "Random", "eightyTwenty" };
        JComboBox<String> threadList = new JComboBox<String>(threadTypes);
        threadList.setFont(font);

        JLabel iterations = new JLabel("Max Iterations: ");
        iterations.setFont(font);
        JTextField userIterations = new JTextField();
        userIterations.setFont(font);
        JLabel stopCondition = new JLabel("Stopping Condition: ");
        stopCondition.setFont(font);
        JTextField userStop = new JTextField();
        userStop.setFont(font);

        JPanel panel = new JPanel(new GridLayout(6, 1));

        panel.add(threads);
        panel.add(userThreads);
        panel.add(threadType);
        panel.add(threadList);
        panel.add(iterations);
        panel.add(userIterations);
        panel.add(stopCondition);
        panel.add(userStop);
        panel.add(calculate);

        calculate.addActionListener(new ActionListener() {

            /**
             * ActionListener interface implementation method
             * 
             * @param e Action to be caught
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Constants.NUM_THREADS = Integer.parseInt(userThreads.getText());
                Constants.STOP_CONDITION = Long.parseLong(userStop.getText());
                Constants.MAX_ITERATIONS = Long
                        .parseLong(userIterations.getText());
                Constants.THREAD_TYPE = (String) threadList.getSelectedItem();

                // Keep track of start time
                long startTime = System.nanoTime();

                Heuristics h = new Heuristics();
                h.populateUrn();
                h.initializeThreads();

                String tResult = h.getUrn().get(0).toString();

                long endTime = System.nanoTime();
                long totalTime = endTime - startTime;

                UIManager.put("OptionPane.messageFont",
                        new Font("Arial", Font.BOLD, 24));
                JOptionPane.showMessageDialog(null,
                        ("Program took "
                                + ((double) totalTime / (Math.pow(10, 9)))
                                + " seconds to run.\n")
                                + "Algorithm optimal found: " + tResult);
            }
        });

        add(panel, BorderLayout.CENTER);
    }

    /**
     * Starts GUI Fram program
     * 
     * @param args String command line arguments
     */
    public static void main(String[] args) {
        JFrame f = new HeuristicFrame();
        f.setVisible(true);
    }

}
