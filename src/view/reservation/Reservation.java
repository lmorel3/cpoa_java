/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.reservation;

import app.AppController;
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
public class Reservation extends JFrame {
    
    private Reservation() {
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
    
    private static Reservation getFrame(){
        
        if(Reservation.frame == null){
            frame = new Reservation();
        }
        
        return frame;
        
    }    
    
    private void initComponents(){
        
        overPane = new JPanel();
        tabbedPane = new JTabbedPane();
        
        dailyReservations = new ArrayList<>();
        
        for (int i = 1; i <= Settings.NB_DAYS; i++) {
            dailyReservations.add(new DailyReservation());
            tabbedPane.add("Jour " + i, (DailyReservation) dailyReservations.get(i-1));
        }
        
        btnValid = new JButton();
        btnExit = new JButton();

        btnValid.setText("Valider");
        btnValid.addActionListener((java.awt.event.ActionEvent evt) -> {
            
            AppController.makeReservation();
            
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
            Reservation.close();
            ChooseModeDialog.display();
        }
        
    }    
    private JPanel overPane;
    private ArrayList<DailyReservation> dailyReservations;
    private JButton btnValid, btnExit;
    
    private static Reservation frame;
    private JTabbedPane tabbedPane;
    
}
