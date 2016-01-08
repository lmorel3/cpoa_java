/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Rouquette Loïc
 */
public class OptionCollection {

    public static Option readOne(int index) {
        
        Option result = new Option();
        ArrayList<Object> params = new ArrayList<>();
        
        params.add(index);
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * From option_type where option_id = ?", params);
        
        for(HashMap<String, Object> row : cursor) {
            
            result.hydrate(row);
            
        }
        return result;
        
        
    }
    
    public static ArrayList<Option> read(ArrayList<HashMap<String, Object>> optionIdList) {
        
        try {
        
        int optionId;
        
        ArrayList<Option> result = new ArrayList<>();
        Option current;
        
        for(HashMap<String, Object> row : optionIdList) {
            
            optionId = (int)((BigDecimal)row.get("OPTION_ID")).intValue();
            current = OptionCollection.readOne(optionId);
            
            result.add(current);
            
            
        }
        
        return result;
        
        }
        
        catch (Exception e) {
            
            System.err.println("Erreur read ArrayList<HashMap<String, Object>> dans la classe OptionCollection " + e.getMessage());
            
        }
        
        return null;
        
    }
    
}
