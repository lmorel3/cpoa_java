/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Calendar.getInstance;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import model.Reservation;
import model.ReservationCollection;
import model.swing.ReservationTableModel;
import view.reservation.DailyReservation;
import view.reservation.ManageReservation;

/**
 *
 * @author laurent
 */
public class ReservationController {
    
    public static void makeReservation(ArrayList<DailyReservation> dailyReservations) {
       
        int col;
        int row;
        int nbOfDay = 0;
        Calendar c = getInstance();
        Date date;
        
        Reservation reservation;
        
        int court, slot;
        String name;
        
        for(DailyReservation dailyReservation : dailyReservations) {
            
            ReservationTableModel tModel = dailyReservation.getReservationTableModel();

            ArrayList<Integer[]> createdReservation = tModel.getCreatedReservations();

            for(Integer[] reservationInformation : createdReservation) {

                slot = reservationInformation[0];
                court = reservationInformation[1];
                name = (String)tModel.getValueAt(slot, court);
                date = Settings.generateDate(nbOfDay);

                reservation = new Reservation(0, court, slot, name, date, date);
                ReservationCollection.create(reservation);

                tModel.setNoEditable(slot, court);

                JOptionPane.showMessageDialog(dailyReservation, "Les réservations ont bien été effecutées. Elles ne sont désormais plus modifiables.");

            }
            
            nbOfDay += 1;
            
        }
        
    }
    
    public static void deleteReservation(Reservation reservation){
        
         ReservationCollection.delete(reservation.getReservationId());
        
    }
    
}
