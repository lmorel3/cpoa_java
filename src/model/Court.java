/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 
 * @author Rouquette Loïc
 */
class Court {
    private int courtId;
    private String courtName;
    
    public Court() {}
    
    public Court(int courtId, String courtName) {
        
        this.courtId = courtId;
        this.courtName = courtName;
        
    }

    public int getCourtId() {
        return courtId;
    }

    public void setCourtId(int courtId) {
        this.courtId = courtId;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }
    
    public void hydrate(HashMap<String, Object> datas) {        
        
        try {
            
            int courtId = (int)((BigDecimal)datas.get("COURT_ID")).intValue();
            String courtName = (String)datas.get("COURT_NAME");
            
            this.courtId = courtId;
            this.courtName = courtName;
                 
        }
        catch (Exception e) {
            
            System.err.println("Erreur d'hydratation Court " + e.getMessage());
            
        }
    }
    
}
