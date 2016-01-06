/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;

/**
 * 
 * @author Rouquette Loïc
 */
public class Reservation {
    
    private int reservationId;
    private int courtId;
    private String reservationName;
    private Date startDate;
    private Date endDate;
    
    public Reservation(int reservationId, int courtId, String reservationName, Date startDate, Date endDate) {
        
        this.reservationId = reservationId;
        this.courtId = courtId;
        this.reservationName = reservationName;
        this.startDate = startDate;
        this.endDate = endDate;
        
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getCourtId() {
        return courtId;
    }

    public void setCourtId(int courtId) {
        this.courtId = courtId;
    }

    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
           
    public void hydrate(HashMap<String, Object> datas) {        
        
        try {
            
            int reservationId = (int)((BigDecimal)datas.get("RESERVATION_ID")).intValue();
            int courtId = (int)((BigDecimal)datas.get("COURT_ID")).intValue();
            Date startDate = (Date)datas.get("START_DATE");
            Date endDate = (Date)datas.get("END_DATE");
            String reservationName = (String)datas.get("RESERVATION_NAME");
            
            this.reservationId = reservationId;
            this.courtId =courtId;
            this.startDate = startDate;
            this.endDate = endDate;
            this.reservationName = reservationName;
            
            
            
        }
        catch (Exception e) {
            
            System.err.println("Erreur d'hydratation Reservation " + e.getMessage());
            
        }
        
    }
    
    

}
