/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.planning;

import app.PlanningController;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import model.BallBoy;
import model.Match;
import model.Umpire;
import model.swing.DoublePersonListRenderer;
import model.swing.PersonListModel;
import model.swing.PersonListRenderer;

/**
 *
 * @author laurent
 */
public class AddMatch extends JFrame {
    
    private AddMatch(int matchType, int dayNumber, int slotId, int courtId){
        
        AddMatch.matchType = matchType;
        AddMatch.dayNumber = dayNumber;
        AddMatch.slotId = slotId;
        AddMatch.interfaceCourtId = courtId;
        
        initComponents();
                
    }
    
    public static void display(int matchType, int dayNumber, int slotId, int courtId){
        
        frame = getFrame(matchType, dayNumber, slotId, courtId);
        frame.refresh();
        frame.setVisible(true);
        
    }
    
    public static void close(){
        
        frame = getFrame(0,0,0,0);
        
        frame.setVisible(false);
        frame.dispose(); // Destroy the frame
        frame = null;
        
        PlanningController.refreshPlanning();
        
    }
    
    public static AddMatch getFrame(int matchType, int dayNumber, int slotId, int courtId){
        
        if(AddMatch.frame == null){
            System.out.println("New frame AddMatch");
            frame = new AddMatch(matchType, dayNumber, slotId, courtId);
        }
                        
        return frame;
        
    } 
    
    private void initComponents() {
        
        int GLOBAL_WIDTH = (matchType == Match.KIND_SIMPLE)?420:480; 
        int COMBOBOX_WIDTH = (matchType == Match.KIND_SIMPLE)?150:170; 
        int LIST_WIDTH = (matchType == Match.KIND_SIMPLE)?190:230; 
        int NATIONALITY_WIDTH = (matchType == Match.KIND_SIMPLE)?20:40; 
        
        String PLAYERA_LABEL = (matchType == Match.KIND_SIMPLE)?"Joueur A":"Equipe A";
        String PLAYERB_LABEL = (matchType == Match.KIND_SIMPLE)?"Joueur B":"Equipe B";
        
        AddMatch.modelPlayerA = new PersonListModel();
        AddMatch.modelPlayerB = new PersonListModel();
        AddMatch.modelBallBoys = new PersonListModel();
        AddMatch.modelUmpire = new PersonListModel();
        AddMatch.modelNetUmpires = new PersonListModel();
        AddMatch.modelPhase = new javax.swing.DefaultComboBoxModel<>(PlanningController.PHASES_NAME);
        
        overPane = new javax.swing.JPanel();
        rowTitle = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        row1 = new javax.swing.JPanel();
        labelPlayerA = new javax.swing.JLabel();
        comboPlayerA = new javax.swing.JComboBox<>();
        //nationalityPlayerA = new javax.swing.JLabel();
        row2 = new javax.swing.JPanel();
        labelPlayerB = new javax.swing.JLabel();
        comboPlayerB = new javax.swing.JComboBox<>();
        //nationalityPlayerB = new javax.swing.JLabel();
        row3 = new javax.swing.JPanel();
        labelChairUmpire = new javax.swing.JLabel();
        comboChairUmpire = new javax.swing.JComboBox<>();
        //nationalityChairUmpire = new javax.swing.JLabel();
        row7 = new javax.swing.JPanel();
        labelNetUmpires = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listNetUmpires = new javax.swing.JList<>();
        row4 = new javax.swing.JPanel();
        labelBallBoys = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listBallBoys = new javax.swing.JList<>();
        row5 = new javax.swing.JPanel();
        labelMatchType = new javax.swing.JLabel();
        comboType = new javax.swing.JComboBox<>();
        row6 = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();
        btnValid = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(GLOBAL_WIDTH, 360));
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        overPane.setLayout(new javax.swing.BoxLayout(overPane, javax.swing.BoxLayout.Y_AXIS));

        rowTitle.setPreferredSize(new java.awt.Dimension(388, 30));

        title.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Ajouter un match");
        title.setPreferredSize(new java.awt.Dimension(151, 30));

        javax.swing.GroupLayout rowTitleLayout = new javax.swing.GroupLayout(rowTitle);
        rowTitle.setLayout(rowTitleLayout);
        rowTitleLayout.setHorizontalGroup(
            rowTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
            .addGroup(rowTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rowTitleLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        rowTitleLayout.setVerticalGroup(
            rowTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(rowTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rowTitleLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        overPane.add(rowTitle);

        row1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        labelPlayerA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelPlayerA.setText(PLAYERA_LABEL);
        labelPlayerA.setPreferredSize(new java.awt.Dimension(150, 16));
        row1.add(labelPlayerA);

        comboPlayerA.setModel(AddMatch.modelPlayerA);
        comboPlayerA.setPreferredSize(new java.awt.Dimension(LIST_WIDTH, 27));
        
        if((AddMatch.matchType == Match.KIND_SIMPLE)){
            ListCellRenderer listRenderer = new PersonListRenderer();
            comboPlayerA.setRenderer(listRenderer);
        }else{
            ListCellRenderer listRenderer = new DoublePersonListRenderer();
            comboPlayerA.setRenderer(listRenderer);
        }
        
        row1.add(comboPlayerA);

        /*nationalityPlayerA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nationalityPlayerA.setText("ES - FR");
        nationalityPlayerA.setPreferredSize(new java.awt.Dimension(NATIONALITY_WIDTH, 16));
        row1.add(nationalityPlayerA);*/

        overPane.add(row1);

        row2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        labelPlayerB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelPlayerB.setText(PLAYERB_LABEL);
        labelPlayerB.setPreferredSize(new java.awt.Dimension(150, 16));
        row2.add(labelPlayerB);

        comboPlayerB.setModel(AddMatch.modelPlayerB);
        comboPlayerB.setPreferredSize(new java.awt.Dimension(LIST_WIDTH, 27));
        
        if((AddMatch.matchType == Match.KIND_SIMPLE)){
            ListCellRenderer listRenderer = new PersonListRenderer();
            comboPlayerB.setRenderer(listRenderer);
        }else{
            ListCellRenderer listRenderer = new DoublePersonListRenderer();
            comboPlayerB.setRenderer(listRenderer);
        }
                
        row2.add(comboPlayerB);

        /*nationalityPlayerB.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nationalityPlayerB.setText("CH - FR");
        nationalityPlayerB.setPreferredSize(new java.awt.Dimension(NATIONALITY_WIDTH, 16));
        row2.add(nationalityPlayerB);*/

        overPane.add(row2);

        row3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        labelChairUmpire.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelChairUmpire.setText("Arbitre de chaise");
        labelChairUmpire.setPreferredSize(new java.awt.Dimension(150, 16));
        row3.add(labelChairUmpire);

        comboChairUmpire.setModel(AddMatch.modelUmpire);
        comboChairUmpire.setPreferredSize(new java.awt.Dimension(LIST_WIDTH, 27));
        
        ListCellRenderer listRenderer = new PersonListRenderer();
        comboChairUmpire.setRenderer(listRenderer);
        
        row3.add(comboChairUmpire);

        /*nationalityChairUmpire.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nationalityChairUmpire.setText("FR");
        nationalityChairUmpire.setPreferredSize(new java.awt.Dimension(NATIONALITY_WIDTH, 16));
        row3.add(nationalityChairUmpire);*/

        overPane.add(row3);

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5);
        flowLayout1.setAlignOnBaseline(true);
        row7.setLayout(flowLayout1);

        labelNetUmpires.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNetUmpires.setText("Arbitres de filet");
        labelNetUmpires.setPreferredSize(new java.awt.Dimension(150, 16));
        row7.add(labelNetUmpires);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(LIST_WIDTH, 55));

        listNetUmpires.setModel(AddMatch.modelNetUmpires);
        jScrollPane2.setViewportView(listNetUmpires);

        row7.add(jScrollPane2);

        overPane.add(row7);

        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5);
        flowLayout2.setAlignOnBaseline(true);
        row4.setLayout(flowLayout2);

        labelBallBoys.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelBallBoys.setText("Ramasseurs de balles");
        labelBallBoys.setPreferredSize(new java.awt.Dimension(150, 16));
        row4.add(labelBallBoys);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(LIST_WIDTH, 55));

        listBallBoys.setModel(AddMatch.modelBallBoys);
        jScrollPane1.setViewportView(listBallBoys);

        row4.add(jScrollPane1);

        overPane.add(row4);

        row5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        labelMatchType.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelMatchType.setText("Type de match");
        labelMatchType.setPreferredSize(new java.awt.Dimension(150, 16));
        row5.add(labelMatchType);

        comboType.setModel(AddMatch.modelPhase);
        comboType.setMinimumSize(new java.awt.Dimension(125, 27));
        comboType.setPreferredSize(new java.awt.Dimension(LIST_WIDTH, 27));
        row5.add(comboType);

        overPane.add(row5);

        row6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 12, 5));

        btnExit.setText("Annuler");
        btnExit.setPreferredSize(new java.awt.Dimension(95, 29));
        row6.add(btnExit);

        btnValid.setText("Valider");
        btnValid.setPreferredSize(new java.awt.Dimension(95, 29));
        row6.add(btnValid);

        overPane.add(row6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(overPane, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(overPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
                
        addWindowListener(new java.awt.event.WindowAdapter() {
           @Override
           public void windowClosing(java.awt.event.WindowEvent windowEvent) {
               askForClosing();
           }
        });
        
        btnExit.addActionListener((java.awt.event.ActionEvent evt) -> {
            
            askForClosing();
            
        });
        
        btnValid.addActionListener((java.awt.event.ActionEvent evt) -> {
            
            PlanningController.createMatch();
            
        });
                
        pack();
    }// </editor-fold> 
    
    private void askForClosing() {
        if (JOptionPane.showConfirmDialog(frame, 
                "Etes-vous sûr de vouloir vous fermer ? Toute modification non validée sera perdue.", "Fermer", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
        {
            AddMatch.close();
        }
    }
    
    public static List<Umpire> getNetUmpires(){
        
        List<Umpire> netUmpires = new ArrayList<>();
        
        int[] selectedIndexes = listNetUmpires.getSelectedIndices();
        
        // Get all the selected items using the indices
        for (int i = 0; i < selectedIndexes.length; i++) {
            netUmpires.add((Umpire) modelNetUmpires.getElementAt(selectedIndexes[i]));
        }
        
        return netUmpires;
        
    }
    
    public static List<BallBoy> getBallBoys(){
        
        List<BallBoy> ballboys = new ArrayList<>();
        
        int[] selectedIndexes = listNetUmpires.getSelectedIndices();
        
        // Get all the selected items using the indices
        for (int i = 0; i < selectedIndexes.length; i++) {
            ballboys.add((BallBoy) modelNetUmpires.getElementAt(selectedIndexes[i]));
        }
        
        return ballboys;       
        
    }
    
    private void refresh(){
        
        comboPlayerA.updateUI();
        comboPlayerB.updateUI();
        comboChairUmpire.updateUI();
        listNetUmpires.updateUI();
        listBallBoys.updateUI();
        comboType.updateUI();
        
        if(AddMatch.modelPlayerA.getSize() > 0){ comboPlayerA.setSelectedIndex(0); }
        if(AddMatch.modelPlayerB.getSize() > 0){ comboPlayerB.setSelectedIndex(0); }
        if(AddMatch.modelUmpire.getSize() > 0){ comboChairUmpire.setSelectedIndex(0); }
        
    }
    
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnValid;
    private javax.swing.JComboBox<String> comboChairUmpire;
    private javax.swing.JComboBox<String> comboPlayerA;
    private javax.swing.JComboBox<String> comboPlayerB;
    private javax.swing.JComboBox<String> comboType;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelBallBoys;
    private javax.swing.JLabel labelChairUmpire;
    private javax.swing.JLabel labelMatchType;
    private javax.swing.JLabel labelNetUmpires;
    private javax.swing.JLabel labelPlayerA;
    private javax.swing.JLabel labelPlayerB;
    public static javax.swing.JList<String> listBallBoys;
    public static javax.swing.JList<String> listNetUmpires;
    //private javax.swing.JLabel nationalityChairUmpire;
    //private javax.swing.JLabel nationalityPlayerA;
    //private javax.swing.JLabel nationalityPlayerB;
    private javax.swing.JPanel overPane;
    private javax.swing.JPanel row1;
    private javax.swing.JPanel row2;
    private javax.swing.JPanel row3;
    private javax.swing.JPanel row4;
    private javax.swing.JPanel row5;
    private javax.swing.JPanel row6;
    private javax.swing.JPanel row7;
    private javax.swing.JPanel rowTitle;
    private javax.swing.JLabel title;
    
    private static AddMatch frame;
    
    public static PersonListModel modelPlayerA;
    public static PersonListModel modelPlayerB;
    public static PersonListModel modelUmpire;
    public static PersonListModel modelNetUmpires;
    public static PersonListModel modelBallBoys;
    public static javax.swing.DefaultComboBoxModel modelPhase;

        
    public static int matchType, dayNumber, slotId, interfaceCourtId;

    
}
