/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.swing;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;
import model.Court;
import model.Match;
import model.Reservation;
import model.ReservationCollection;

/**
 *
 * @author Laurent
 */
public class ReservationTableModel extends AbstractTableModel {

    private Object values[][] = new Object [][] {
        {"8h", null, null, null, null, null, null},
        {"11h", null, null, null, null, null, null},
        {"14h", null, null, null, null, null, null},
        {"18h", null, null, null, null, null, null},
        {"21h", null, null, null, null, null, null}
    };
    
    private boolean isEditable[][] = new boolean[][] {
        {false, true, true, true, true, true, true},
        {false, true, true, true, true, true, true},
        {false, true, true, true, true, true, true},
        {false, true, true, true, true, true, true},
        {false, true, true, true, true, true, true},
        
    };
    
    private ArrayList<Integer[]> createdReservations;
    
    private final String columnNames[] = new String [] {
        "Horaire", "Court A", "Court B", "Entraînement 1", "Entraînement 2", "Entraînement 3", "Entraînement 4"
    };
    
    private ReservationCollection reservationCollection;
    
    public ReservationTableModel() {
    }
    
    public ReservationTableModel(ArrayList<Reservation> reservations, ArrayList<Match> matchs) {
        
        createdReservations = new ArrayList();
        
        String matchText = "MATCH";
        for(Reservation reservation : reservations) {
            
          this.values[reservation.getSlotId()][reservation.getCourtId()] = reservation.getReservationName();
          this.isEditable[reservation.getSlotId()][reservation.getCourtId()] = false;
                              
        }
        
        for(Match match : matchs) {
            
            this.values[match.getSlot()][match.getCourt().getCourtId()] = matchText;
            this.isEditable[match.getSlot()][match.getCourt().getCourtId()] = false;
            
        }
        
    }

    public ArrayList<Integer[]> getCreatedReservations() {
        return createdReservations;
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
    public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int slotId, int courtId) {            
        return values[slotId][courtId];
    }

    @Override
     public void setValueAt(Object value, int slotId, int courtId) {
         
        values[slotId][courtId] = value; 

        // We add only if coords aren't already in the array
        boolean adding = (createdReservations.isEmpty()); // If it's empty, we had
        
        // Else, we loop through the array
        for(Integer[] reservationInformation : createdReservations) {
            if(reservationInformation[0] != slotId && reservationInformation[1] != courtId){
                adding = true;
            }
        }
        
        if(adding){
            
            Integer[] coord = {slotId, courtId};
            createdReservations.add(coord);
            
        }
        
        fireTableCellUpdated(slotId, courtId); 

    }
     
    public void setNoEditable(int rowIndex, int columnIndex) {
        
        this.isEditable[rowIndex][columnIndex] = false;
        
    }
     
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return isEditable[rowIndex][columnIndex];
    }
    
    public void setReservationCollection(ReservationCollection reservationCollection){
        this.reservationCollection = reservationCollection;
    }
    
    public ReservationCollection getReservationColleciton(){
        return reservationCollection;
    }
    
}
