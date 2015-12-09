/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author laurent
 */
public class Slot extends JPanel {
    
    public Slot(){
        
        informationsContainer = new javax.swing.JPanel();
        informations = new javax.swing.JLabel();
        image = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        phase = new javax.swing.JLabel();
        
        initComponents();
        
    }
    
    private void initComponents(){
        
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        informationsContainer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        informationsContainer.setOpaque(false);
        informationsContainer.setLayout(new java.awt.BorderLayout());

        informations.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        informations.setForeground(new java.awt.Color(255, 255, 255));
        informations.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        informations.setText("<html><body style=\"text-align:center\">ROUQ/MORE<br>JEAN/MI-J<br>Arbitre : ABCD</body></html>");
        informationsContainer.add(informations, java.awt.BorderLayout.CENTER);

        add(informationsContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 140, 90));

        image.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        image.setForeground(new java.awt.Color(91, 135, 125));
        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/court_large.jpg"))); // NOI18N
        image.setText("■");
        image.setToolTipText("");
        add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        name.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        name.setText("Court central");
        add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        phase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        phase.setText("Qualification");
        add(phase, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 200, -1));
        
    }
    
    private final JLabel informations;
    private final JPanel informationsContainer; 
    private final JLabel image;
    private final JLabel name;
    private final JLabel phase;
    
}
