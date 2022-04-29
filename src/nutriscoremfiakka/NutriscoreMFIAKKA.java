package nutriscoremfiakka;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author arthu
 */
public class NutriscoreMFIAKKA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int timerDelay = 10000;
        int timerPeriod = timerDelay;

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        frame.setContentPane(panel);
        panel.setVisible(true);

        panel.setLayout(new BorderLayout());

        JNutriscore nutriscore = new JNutriscore();
        panel.add(nutriscore, BorderLayout.CENTER);

        Dimension preferredSize = nutriscore.getPreferredSize();
        panel.setPreferredSize(preferredSize);

        // Prepare the timer that generates a new value every ten seconds
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                int randomVal = HTTPRandomGenerator.getValue();
                nutriscore.setValue(randomVal);
            }

        };
        Timer timer = new Timer();
        timer.schedule(task, timerDelay, timerPeriod);

        frame.pack();
    }

}
