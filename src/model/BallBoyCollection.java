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
public class BallBoyCollection {
    
    public static ArrayList<BallBoy> read(int index) {
        
        ArrayList<Object> params = new ArrayList<>();
        ArrayList<BallBoy> result = new ArrayList<>();
        
        params.add(index);
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * from person where person_type = 'BallBoy' and person_id = ?", params);
        
        BallBoy current;
        
        for(HashMap<String, Object> row : cursor) {
            
            
            current = new BallBoy(row);
            result.add(current);
            
        }
        
        return result;
        
    }
    

}
