/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * 
 * @author Rouquette Loïc
 */
public class Reservation {
    
    private int reservationId;
    private int courtId;
    private int slotId;
    private String reservationName;
    private Date startDate;
    private Date endDate;
    
    public Reservation(int reservationId, int courtId, int slotId, String reservationName, Date startDate, Date endDate) {
        
        this.reservationId = reservationId;
        this.courtId = courtId;
        this.slotId = slotId;
        this.reservationName = reservationName;
        this.startDate = startDate;
        this.endDate = endDate;
        
    }
    
    public Reservation() {
        
    }

    public Reservation(HashMap<String, Object> datas) {
        this.hydrate(datas);
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

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }
    
    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }

    public java.util.Date getStartDate() {
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
            int slotId = (int)((BigDecimal)datas.get("SLOT_ID")).intValue();               
            java.util.Date startDate = new java.util.Date(((java.util.Date)datas.get("START_DATE")).getTime());
            java.util.Date endDate = new java.util.Date(((java.util.Date)datas.get("END_DATE")).getTime());
            String reservationName = (String)datas.get("RESERVATION_NAME");
            
            this.reservationId = reservationId;
            this.courtId =courtId;
            this.slotId = slotId;
            this.startDate = (java.util.Date)startDate;
            this.endDate = (java.util.Date)endDate;
            this.reservationName = reservationName;
            
            
            
        }
        catch (Exception e) {
            
            System.err.println("Erreur d'hydratation Reservation " + e.getMessage());
            
        }
        
    }
    
     public static int getLastId() {
        
        ArrayList<Object> params = new ArrayList<>();
        
        ArrayList<HashMap<String, Object>> result = Connector.getConnection().query("Select reservation_id from reservation where rownum = 1 order by reservation_id desc", params);
        
        return 1+(int)((BigDecimal)result.get(0).get("RESERVATION_ID")).intValue();
        
    }
    

}
