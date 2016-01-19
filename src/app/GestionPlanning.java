/*
 * Start point
 */
package app;

import javax.swing.SwingUtilities;
import view.main.ChooseModeDialog;

/**
 *
 * @author Laurent
 */
public class GestionPlanning {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChooseModeDialog frame = new ChooseModeDialog();
            frame.setVisible(true);
        });
    }
    
}
