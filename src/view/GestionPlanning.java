/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.SwingUtilities;

/**
 *
 * @author Laurent
 */
public class GestionPlanning {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                MainView frame = new MainView();
                frame.setVisible(true);
                
                System.out.println("Frame visible");
                
            }
        });
    }
    
}
