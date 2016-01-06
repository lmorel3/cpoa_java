/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design_test;

/**
 *
 * @author laurent
 */
public class EditMatch extends javax.swing.JFrame {

    /**
     * Creates new form EditMatch
     */
    public EditMatch() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        overPane = new javax.swing.JPanel();
        rowTitle = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        row1 = new javax.swing.JPanel();
        labelPlayerA = new javax.swing.JLabel();
        comboPlayerA = new javax.swing.JComboBox<>();
        nationalityPlayerA = new javax.swing.JLabel();
        row2 = new javax.swing.JPanel();
        labelPlayerB = new javax.swing.JLabel();
        comboPlayerB = new javax.swing.JComboBox<>();
        nationalityPlayerB = new javax.swing.JLabel();
        row3 = new javax.swing.JPanel();
        labelChairUmpire = new javax.swing.JLabel();
        comboChairUmpire = new javax.swing.JComboBox<>();
        nationalityChairUmpire = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(410, 360));
        setResizable(false);

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
        labelPlayerA.setText("Joueur A");
        labelPlayerA.setPreferredSize(new java.awt.Dimension(150, 16));
        row1.add(labelPlayerA);

        comboPlayerA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboPlayerA.setMinimumSize(new java.awt.Dimension(125, 27));
        comboPlayerA.setPreferredSize(new java.awt.Dimension(150, 27));
        comboPlayerA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPlayerAActionPerformed(evt);
            }
        });
        row1.add(comboPlayerA);

        nationalityPlayerA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nationalityPlayerA.setText("ES");
        nationalityPlayerA.setPreferredSize(new java.awt.Dimension(20, 16));
        row1.add(nationalityPlayerA);

        overPane.add(row1);

        row2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        labelPlayerB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelPlayerB.setText("Joueur B");
        labelPlayerB.setPreferredSize(new java.awt.Dimension(150, 16));
        row2.add(labelPlayerB);

        comboPlayerB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboPlayerB.setMinimumSize(new java.awt.Dimension(125, 27));
        comboPlayerB.setPreferredSize(new java.awt.Dimension(150, 27));
        comboPlayerB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPlayerBActionPerformed(evt);
            }
        });
        row2.add(comboPlayerB);

        nationalityPlayerB.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nationalityPlayerB.setText("CH");
        nationalityPlayerB.setPreferredSize(new java.awt.Dimension(20, 16));
        row2.add(nationalityPlayerB);

        overPane.add(row2);

        row3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        labelChairUmpire.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelChairUmpire.setText("Arbitre de chaise");
        labelChairUmpire.setPreferredSize(new java.awt.Dimension(150, 16));
        row3.add(labelChairUmpire);

        comboChairUmpire.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboChairUmpire.setMinimumSize(new java.awt.Dimension(125, 27));
        comboChairUmpire.setPreferredSize(new java.awt.Dimension(150, 27));
        comboChairUmpire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboChairUmpireActionPerformed(evt);
            }
        });
        row3.add(comboChairUmpire);

        nationalityChairUmpire.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nationalityChairUmpire.setText("FR");
        nationalityChairUmpire.setPreferredSize(new java.awt.Dimension(20, 16));
        row3.add(nationalityChairUmpire);

        overPane.add(row3);

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5);
        flowLayout1.setAlignOnBaseline(true);
        row7.setLayout(flowLayout1);

        labelNetUmpires.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNetUmpires.setText("Arbitres de filet");
        labelNetUmpires.setPreferredSize(new java.awt.Dimension(150, 16));
        row7.add(labelNetUmpires);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(190, 55));

        listNetUmpires.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
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

        jScrollPane1.setPreferredSize(new java.awt.Dimension(190, 55));

        listBallBoys.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listBallBoys);

        row4.add(jScrollPane1);

        overPane.add(row4);

        row5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        labelMatchType.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelMatchType.setText("Type de match");
        labelMatchType.setPreferredSize(new java.awt.Dimension(150, 16));
        row5.add(labelMatchType);

        comboType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualification", "Huitième de finale", "Quart de finale", "Demie finale", "Finale" }));
        comboType.setMinimumSize(new java.awt.Dimension(125, 27));
        comboType.setPreferredSize(new java.awt.Dimension(190, 27));
        comboType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTypeActionPerformed(evt);
            }
        });
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboPlayerAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPlayerAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboPlayerAActionPerformed

    private void comboPlayerBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPlayerBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboPlayerBActionPerformed

    private void comboChairUmpireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboChairUmpireActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboChairUmpireActionPerformed

    private void comboTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTypeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditMatch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditMatch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditMatch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditMatch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditMatch().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JList<String> listBallBoys;
    private javax.swing.JList<String> listNetUmpires;
    private javax.swing.JLabel nationalityChairUmpire;
    private javax.swing.JLabel nationalityPlayerA;
    private javax.swing.JLabel nationalityPlayerB;
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
    // End of variables declaration//GEN-END:variables
}
