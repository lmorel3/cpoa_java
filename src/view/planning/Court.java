/*
 * A Court corresponds to a court, with a name, informations and a isFree (unavailable or available)
 */
package view.planning;

import app.PlanningController;
import app.ReservationController;
import app.Settings;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Match;
import model.Reservation;

/**
 *
 * @author laurent
 */
public class Court extends JPanel {
    
    public Court(int dayNumber, int slotId, int courtId){
        
        informationsContainer = new javax.swing.JPanel();
        informations = new javax.swing.JLabel();
        image = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        phase = new javax.swing.JLabel();
        
        this.slotId = slotId;
        this.dayNumber = dayNumber;
        this.courtId = courtId;
        
        initComponents();
        
        setTitle("D"+dayNumber+" S"+slotId + "C"+courtId);
        
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
                // Launch the match creation
                System.out.println(match);
                if(match == null){
                    askForType();
                }else{
                    if(match instanceof Match)
                        editMatch();
                    else
                        askForDeletingReservation();
                }
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
    
    /**
     * Get the real courtId (in database) from the frame courtId (generated in CourtsContainer)
     * @param frameCourtId
     * @return real courtId (in database)
     */
    public static int getRealCourtId(int frameCourtId){

        // 0 - 1 - 2   <->  1 - 3 - 5
        // 3 - 4 - 5        2 - 4 - 6
                
        
        int realCourtId = -1;
        switch(frameCourtId) {
                    
            case 1:
                realCourtId = 0;
                break;
            case 2:
                realCourtId = 3;
                break;
            case 3:
                realCourtId = 1;
                break;
            case 4:
                realCourtId = 4;
                break;
            case 5:
                realCourtId = 2;
                break;
            case 6:
                realCourtId = 5;
                break;

        }
        
        return realCourtId;
        
    }
    
    private void askForType() {
        
        Object[] options = {"Simple",
                    "Double"};

        int question = JOptionPane.showOptionDialog(null, 
                "Choisissez le type de match.", "Type de match", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        
        if (question != 2){
            
            int matchType = Match.KIND_DOUBLE;
            if(question == 0){ matchType = Match.KIND_SIMPLE; }
            
            PlanningController.initMatchCreation(matchType, dayNumber, slotId, courtId);

        }
        
    }
            
    private void editMatch() {
        
        Object[] options = {"Modifier",
                    "Supprimer"};

        int question = JOptionPane.showOptionDialog(null, 
                "Que souhaitez-vous faire ?", "Editer le match ?", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        
        if (question != 2){
            
            if(question == 0){ EditMatch.matchType = Match.KIND_SIMPLE; }
            else{ EditMatch.matchType = Match.KIND_DOUBLE; }
            
            PlanningController.initMatchEdition((Match)match);

        }
        
    }
    
    private void askForDeletingReservation(){
        if (JOptionPane.showConfirmDialog(null, "Souhaitez-vous supprimer cette resevation ?", "Suppresion",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            ReservationController.deleteReservation((Reservation) match);
        }
    }
    
    private void updateInformations(){
        if(match instanceof Reservation){       
            setPhase("Réservation");
            setInformations(((Reservation) match).getReservationName());
            setStatus(Settings.COURT_STATUS_UNAVAILABLE);
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
    
    public void setMatch(Object match){
        this.match = match;
        updateInformations();
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
    
    public Object getMatch(){
        return match;
    }
        
    private final JLabel informations;
    private final JPanel informationsContainer; 
    private final JLabel image;
    private final JLabel title;
    private final JLabel phase;
    
    private Object match;
    
    private int status;
    
    private final int slotId, dayNumber, courtId;
    
}
