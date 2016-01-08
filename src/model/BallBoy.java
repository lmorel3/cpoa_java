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
 * @author laurent
 */
public class BallBoy extends Person {
    
    public BallBoy() {
        
    }
    
    public BallBoy(HashMap<String, Object> datas) {
        
        this.hydrate(datas);
        
    }

    public BallBoy(int personId, String forename, String lastname, String country) {
        super(personId, forename, lastname, country);
    }
    
     public void hydrate(HashMap<String, Object> datas) {        
        
        try {
            
            int umpireId = (int)((BigDecimal)datas.get("PERSON_ID")).intValue();
            String foreName = (String)datas.get("FORENAME");
            String lastName = (String)datas.get("LASTNAME");
            String country = (String)datas.get("NATIONALITY");
            
            this.setPersonId(umpireId);
            this.setForename(foreName);
            this.setLastname(lastName);
            this.setCountry(country);
            
        }
        catch (Exception e) {
            
            System.err.println(e.getMessage());
            
        }
        
    }
}
