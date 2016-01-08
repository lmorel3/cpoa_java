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
public class Option {
    private int optionId;
    private String label;
    
    public Option() {
        
    }
    
    public Option(int optionId, String label) {
        
        this.optionId = optionId;
        this.label = label;        
        
    }
    
    public Option(HashMap<String, Object> datas) {
        
        this.hydrate(datas);
        
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
     public void hydrate(HashMap<String, Object> datas) {        
        
        try {
            
            int optionId = (int)((BigDecimal)datas.get("OPTION_ID")).intValue();
            String label = (String)datas.get("LABEL");
            
            this.optionId = optionId;
            this.label = label;
            
            
          
            
            
        }
        catch (Exception e) {
            
            System.err.println("Erreur d'hydratation option " + e.getMessage());
            
        }
        
    }
    
    
}
