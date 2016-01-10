/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.reservation;

import app.Settings;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.MatchCollection;
import model.ReservationCollection;
import model.swing.ReservationTableModel;

/**
 *
 * @author laurent
 */
public class DailyReservation extends JPanel {

    public DailyReservation(int dayNumber) {
        initComponents(dayNumber);
    }
    
    
    
    private void initComponents(int DayNumber) {

        scrollPane = new javax.swing.JScrollPane();
        tableContent = new javax.swing.JTable();
        
        reservationTableModel = new ReservationTableModel(ReservationCollection.read(Settings.generateDate(DayNumber),Settings.generateDate(DayNumber+1)), MatchCollection.readByDate(Settings.generateDate(DayNumber)));
        tableContent.setModel(reservationTableModel);
        
        tableContent.setRowHeight(30);
        tableContent.setShowGrid(true);
        scrollPane.setViewportView(tableContent);


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(this);
        setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                ))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGap(0, 0, Short.MAX_VALUE))
        );

    }// </editor-fold>                        

    public ReservationTableModel getReservationTableModel(){
        return reservationTableModel;
    }

    private JScrollPane scrollPane;
    private JTable tableContent;
    
    private ReservationTableModel reservationTableModel;
    
}
