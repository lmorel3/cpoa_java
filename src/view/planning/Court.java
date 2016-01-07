/*
 * A Court corresponds to a court, with a name, informations and a isFree (unavailable or available)
 */
package view.planning;

import app.Settings;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author laurent
 */
public class Court extends JPanel {
    
    public Court(){
        
        informationsContainer = new javax.swing.JPanel();
        informations = new javax.swing.JLabel();
        image = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
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
        informations.setText("<html><body style=\"text-align:center\">" + Settings.EMPTY_COURT_DESC + "</body></html>");
        informationsContainer.add(informations, java.awt.BorderLayout.CENTER);

        add(informationsContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 140, 90));

        status = Settings.COURT_STATUS_FREE;
        
        image.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        image.setForeground(new java.awt.Color(91, 135, 125));
        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/court_large.jpg"))); // NOI18N
        image.setText("■");
        image.setToolTipText("");
        add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        title.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        title.setText("Court central");
        add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        phase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        phase.setText("Qualification");
        add(phase, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 200, -1));
        
        informationsContainer.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                askForType();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
    }
    
        
    private void askForType() {
        
        Object[] options = {"Simple",
                    "Double"};

        int question = JOptionPane.showOptionDialog(Planning.getFrame(), 
                "Choisissez le type de match.", "Type de match", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        
        if (question != 2){
            
            if(question == 0){ EditMatch.matchType = Settings.MATCH_TYPE_SIMPLE; }
            else{ EditMatch.matchType = Settings.MATCH_TYPE_DOUBLE; }
            
            EditMatch.display();

        }
        
    }
    
    public void setTitle(String newTitle){
        title.setText(newTitle);
    }
    
    public void setPhase(String newPhase){
        phase.setText(newPhase);
    }
    
    public void setInformations(String newInformations){
        // ToDo : replace String by Match and get informations about it
        informations.setText("<html><body style=\"text-align:center\">" + newInformations + "</body></html>");
    }
    
    public void setStatus(int newStatus){
        status = newStatus;
        switch(status){
            case Settings.COURT_STATUS_FREE:
                image.setForeground(new java.awt.Color(91, 135, 125));
                break;
            case Settings.COURT_STATUS_CLOSED:
                image.setForeground(new java.awt.Color(145, 54, 54));
                break;
            case Settings.COURT_STATUS_UNAVAILABLE:
                image.setForeground(new java.awt.Color(206, 117, 103));
                break;
        }
    }
    
    public String getTitle(){
        return title.getText();
    }
    
    public String getPhase(){
        return phase.getText();
    }
    
    public String getInformations(){
        return informations.getText();
    }
    
    public int getStatus(){
        return status;
    }
        
    private final JLabel informations;
    private final JPanel informationsContainer; 
    private final JLabel image;
    private final JLabel title;
    private final JLabel phase;
    
    private int status;
    
}
