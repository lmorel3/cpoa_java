/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * 
 * @author Rouquette Loïc
 */
public class ReservationCollection {

    
    public static Reservation readOne(int index) {
        
        ArrayList<Object> params = new ArrayList<>();
        
        params.add(index);
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * From reservation where reservation_id = ?", params);
        
        Reservation result = new Reservation();
        
        for(HashMap<String, Object> row : cursor) {
        
            result.hydrate(row);
        
        }
        
        
        return result;
        
    }
    
    public static ArrayList<Reservation> read(Date startDate, Date endDate) {
        
        ArrayList<Reservation> result = new ArrayList<>();
        ArrayList<Object> params = new ArrayList<>();
        
        params.add(startDate);
        params.add(endDate);

        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * From reservation where start_date >= ? and end_date <= ?", params);
        
        Reservation current;
        
        for(HashMap<String, Object> row : cursor) {
            
            current = new Reservation(row);
            result.add(current);
            
        }
        
        return result;
        
    }
    
    public static ArrayList<Reservation> read(Date startDate, Date endDate, int courtId) {
        
        ArrayList<Reservation> result = new ArrayList<>();
        ArrayList<Object> params = new ArrayList<>();
        
        params.add(startDate);
        params.add(endDate);
        params.add(courtId);
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * From reservation where start_date >= ? and end_date <= ? and court_id = ?", params);
        
        Reservation current;
        
        for(HashMap<String, Object> row : cursor) {
            
            current = new Reservation(row);
            result.add(current);
            
        }
        
        return result;
        
    }
    
    public static ArrayList<Reservation> readAll() {
        
        ArrayList<Reservation> result = new ArrayList<>();
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * From reservation", new ArrayList<>());
        
        for(HashMap<String, Object> row : cursor) {
            
            result.add(new Reservation(row));
            
        }
        
        return result;
        
    }
    
    public static void create(Reservation reservation) {
        
        ArrayList<Object> params = new ArrayList<>();
        
        reservation.setReservationId(Reservation.getLastId());
        
        params.add(reservation.getReservationId());
        params.add(reservation.getCourtId());
        params.add(reservation.getStartDate());
        params.add(reservation.getEndDate());
        params.add(reservation.getReservationName());
        
        Connector.getConnection().query("Insert into reservation values (?, ?, ?, ?, ?)", params);
        
        
        
        
    }
    
    public static void update(Reservation reservation) {
        
        ArrayList<Object> params = new ArrayList<>();
        
        params.add(reservation.getCourtId());
        params.add(reservation.getStartDate());
        params.add(reservation.getEndDate());
        params.add(reservation.getReservationName());
        params.add(reservation.getReservationId());
        
        Connector.getConnection().query("Update reservation set COURT_ID = ?, START_DATE = ?, END_DATE = ?, RESERVATION_NAME = ? where RESERVATION_ID = ?", params);
        
        
    }
    
    public static void delete(int reservationId) {
        
        ArrayList<Object> params = new ArrayList<>();
        params.add(reservationId);
        
        Connector.getConnection().query("Delete from reservation where reservation_id = ?", params);
        
    }

}
