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
public class Umpire extends Person {
    
    String level;
    
    public Umpire() {}
    
    public Umpire(HashMap<String, Object> datas) {
        
        this.hydrate(datas);
        
    }
    
    public Umpire(int personId, String forename, String lastname, String country, String level) {
        super(personId, forename, lastname, country);
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    
    public void hydrate(HashMap<String, Object> datas) {        
        
        try {
            
            int umpireId = (int)((BigDecimal)datas.get("PERSON_ID")).intValue();
            String foreName = (String)datas.get("FORENAME");
            String lastName = (String)datas.get("LASTNAME");
            String country = (String)datas.get("NATIONALITY");
            this.level = (String)datas.get("PERSON_LEVEL");
            
            this.setPersonId(umpireId);
            this.setForename(foreName);
            this.setLastname(lastName);
            this.setCountry(country);
            this.level = level;
            
        }
        catch (Exception e) {
            
            System.err.println(e.getMessage());
            
        }
        
    }
}
