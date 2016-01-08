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
public class PlayerCollection {
    
    
    public static Player readOne(int index) {
        
        Player result = new Player();
        
        ArrayList<Object> params = new ArrayList<>();
        
        params.add(index);
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * From person where person_type = 'Player' and person_id = ?", params);
        for(HashMap<String, Object> row : cursor) {
            
            result.hydrate(row);
            
        }

        return result;
        
    }    
   
}
