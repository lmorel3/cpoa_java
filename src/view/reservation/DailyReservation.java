/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.reservation;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author laurent
 */
public class DailyReservation extends JPanel {

    public DailyReservation() {
        initComponents();
    }
    
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        tableContent = new javax.swing.JTable();
        
        tableContent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"8h", null, null, null, null, null, null},
                {"11h", null, null, null, null, null, null},
                {"14h", null, null, null, null, null, null},
                {"18h", null, null, null, null, null, null},
                {"21h", null, null, null, null, null, null}
            },
            new String [] {
                "Horaire", "Court A", "Court B", "Entraînement 1", "Entraînement 2", "Entraînement 3", "Entraînement 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        tableContent.setRowHeight(32);
        tableContent.setShowGrid(true);
        scrollPane.setViewportView(tableContent);
        
        btnValid = new JButton();
        btnExit = new JButton();

        btnValid.setText("Valider");

        btnExit.setText("Annuler");
        btnExit.addActionListener((java.awt.event.ActionEvent evt) -> {
            
            
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(this);
        setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnValid)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnValid)
                    .addComponent(btnExit))
                .addGap(0, 0, Short.MAX_VALUE))
        );

    }// </editor-fold>                        

    
    private JScrollPane scrollPane;
    private JTable tableContent;
    private JButton btnValid, btnExit;
    
    
}
