/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.reservation.Reservation;

/**
 *
 * @author laurent
 */
public class ChooseModeDialog extends JFrame implements ActionListener {
    
    JButton btnPlayer;
    JButton btnManager;
    
    private static ChooseModeDialog frame;
     
    public ChooseModeDialog(){
        
        super();
        
        initializeComponents();
                
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
        
    private static ChooseModeDialog getFrame(){
        
        if(ChooseModeDialog.frame == null){
            frame = new ChooseModeDialog();
        }
        
        return frame;
        
    }    
    
    private void initializeComponents(){
        
        setTitle("Choix du mode d'utilisation");
        setSize(300, 150);
        
        JPanel overPanel = new JPanel();
        
        JLabel text = new JLabel("<html><body>Bienvenue dans l'application<br>de gestion des plannings<br>du <strong>" 
                + "Grand Prix de Tennis de Lyon</strong><br><br><em>Choisissez le mode d'utilisation</em></body></html>");
        
        btnPlayer = new JButton("Joueur");
        btnManager = new JButton("Gestionnaire");
        
        overPanel.add(text);
        
        overPanel.add(btnPlayer);
        overPanel.add(btnManager);
        
        setContentPane(overPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        btnPlayer.addActionListener(this);
        btnManager.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        JButton source = (JButton) e.getSource();
                
        if(source == btnPlayer){
            Reservation.display();
        }else{
            Connection.display();
        }
        
        setVisible(false);

    }
    
    
}
