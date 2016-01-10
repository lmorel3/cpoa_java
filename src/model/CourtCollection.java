/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Rouquette Loïc
 */
public class CourtCollection {

    public static Court readOne(int index) {
        
        ArrayList<Object> params = new ArrayList<>();
        params.add(index);
        
        Court result = new Court();
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * From court where court_id = ?", params);
        
        for(HashMap<String, Object> row : cursor) {
            
            result.hydrate(row);
            
        }
        
        return result;
        
    }
    
}
