/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.reservation;

import app.Settings;
import java.awt.List;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author laurent
 */
public class Reservation extends JFrame {

    public Reservation() {
        initComponents();
    }
    
    private void initComponents(){
        
        overPane = new JTabbedPane();
        
        dailyReservations = new ArrayList<>();
        
        for (int i = 1; i <= Settings.NB_DAYS; i++) {
            dailyReservations.add(new DailyReservation());
            overPane.add("Jour " + i, (DailyReservation) dailyReservations.get(i-1));
        }
        
        setSize(711, 290);
        setResizable(false);
        setLocationRelativeTo(this);
        setContentPane(overPane);
        
    }
    
    JTabbedPane overPane;
    ArrayList<DailyReservation> dailyReservations;
    
}
