/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.swing;

import javax.swing.table.AbstractTableModel;
import model.ReservationCollection;

/**
 *
 * @author Laurent
 */
public class ReservationTableModel extends AbstractTableModel 
{

    private boolean[] canEdit = new boolean [] {
        false, true, true, true, true, true, true
    };
    
    private Object values[][] = new Object [][] {
        {"8h", null, null, null, null, null, null},
        {"11h", null, null, null, null, null, null},
        {"14h", null, null, null, null, null, null},
        {"18h", null, null, null, null, null, null},
        {"21h", null, null, null, null, null, null}
    };
    
    private String columnNames[] = new String [] {
        "Horaire", "Court A", "Court B", "Entraînement 1", "Entraînement 2", "Entraînement 3", "Entraînement 4"
    };
    
    private ReservationCollection reservationCollection;
    
    public ReservationTableModel() {
    }
    
    @Override
    public int getRowCount() {
        return values.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {            
        return values[rowIndex][columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
    
    public void setReservationCollection(ReservationCollection reservationCollection){
        this.reservationCollection = reservationCollection;
    }
    
    public ReservationCollection getReservationColleciton(){
        return reservationCollection;
    }
    
}
