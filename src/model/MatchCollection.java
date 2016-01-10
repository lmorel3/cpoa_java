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
public class MatchCollection {

    public static Match readOne(int index) {
        
        Match result = new Match();
        ArrayList<Object> params = new ArrayList<>();
        params.add(index);
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * From match where match_id = ?", params);
        
        
        for(HashMap<String, Object> row : cursor ) {
            
            result.hydrate(row);
            
        }
                
        return result;

    }
    
    public static ArrayList<Match> readAll() {
        
        ArrayList<Match> result= new ArrayList<>();
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * From match", new ArrayList<>());
        
        Match current;
        
        for(HashMap<String, Object> row : cursor ) {
            
            current = new Match(row);
            result.add(current);
            
        }
        
        return result;
        
    }
    
}
