/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.reservation;

import app.ReservationController;
import app.Settings;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import view.main.ChooseModeDialog;

/**
 *
 * @author laurent
 */
public class ManageReservation extends JFrame {
    
    private ManageReservation() {
        initComponents();
    }
    
    public static void display(){
        
        frame = getFrame();
        
        frame.setVisible(true);
        
    }
    
    public static void close(){
        
        frame = getFrame();
        
        frame.setVisible(false);
        frame.dispose(); // Destroy the frame
        
    }
    
    private static ManageReservation getFrame(){
        
        if(ManageReservation.frame == null){
            frame = new ManageReservation();
        }
        
        return frame;
        
    }    
    
    private void initComponents(){
        
        overPane = new JPanel();
        tabbedPane = new JTabbedPane();
        
        ManageReservation.dailyReservations = new ArrayList<>();
        
        for (int i = 1; i <= Settings.NB_DAYS; i++) {
            dailyReservations.add(new DailyReservation(i-1));
            tabbedPane.add("Jour " + i, (DailyReservation) dailyReservations.get(i-1));
        }
        
        btnValid = new JButton();
        btnExit = new JButton();

        btnValid.setText("Valider");
        btnValid.addActionListener((java.awt.event.ActionEvent evt) -> {
            
            
            ReservationController.makeReservation(ManageReservation.getDailyReservations());
            
        });

        btnExit.setText("Annuler");
        btnExit.addActionListener((java.awt.event.ActionEvent evt) -> {
            askForClosing();
        });
        
        overPane.add(tabbedPane);
        
        overPane.add(btnValid);
        overPane.add(btnExit);
        
        setSize(711, 290);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(overPane);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
           @Override
           public void windowClosing(java.awt.event.WindowEvent windowEvent) {
               askForClosing();
           }
        });
        
    }
    
    private void askForClosing() {
        if (JOptionPane.showConfirmDialog(frame, 
                "Etes-vous sûr de vouloir vous fermer ? Toute modification non validée sera perdue.", "Fermer", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
        {
            ManageReservation.close();
            ChooseModeDialog.display();
        }
        
    }    
    
    public static ArrayList<DailyReservation> getDailyReservations(){
        return ManageReservation.dailyReservations;
    }
    
    /**
     * Get the DayPane of the day dayNumber (starting from 0)
     * @param dayNumber (0 is day 1)
     * @return the DayPane
     */
    public static DailyReservation getDailyReservation(int dayNumber){
        return (DailyReservation) ManageReservation.dailyReservations.get(dayNumber);
    }
    
    private JPanel overPane;
    private static ArrayList<DailyReservation> dailyReservations;
    private JButton btnValid, btnExit;
    
    private static ManageReservation frame;
    private JTabbedPane tabbedPane;
    
}
