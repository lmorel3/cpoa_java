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
    
    public static BallBoy readOne(int index) {
        
        ArrayList<Object> params = new ArrayList<>();
        BallBoy result = new BallBoy();
        
        params.add(index);
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * from person where person_type = 'BallBoy' and person_id = ?", params);
        
        
        for(HashMap<String, Object> row : cursor) {
            
            result.hydrate(row);
            
        }
        
        return result;
        
    }
    
    public static ArrayList<BallBoy> readByMatch(int matchIndex) {
        
        ArrayList<Object> params = new ArrayList<>();
        ArrayList<BallBoy> result = new ArrayList<>();
        
        params.add(matchIndex);
        BallBoy current;        
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * From person Where person_id IN (Select person_id From ballboy_match where match_id = ?) and person_type = '"+Person.TYPE_BALLBOY+"'", params);
        
        for(HashMap<String, Object> row : cursor) {
            
            current = new BallBoy(row);
            result.add(current);
            
        }
        
        return result;        
        
    }
        
    public static ArrayList<BallBoy> readAll() {
        
        ArrayList<Object> params = new ArrayList<>();
        ArrayList<BallBoy> result = new ArrayList<>();
                
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * from person where person_type = '"+Person.TYPE_BALLBOY+"'", params);
        
        BallBoy current;
        
        for(HashMap<String, Object> row : cursor) {
            
            
            current = new BallBoy(row);
            result.add(current);
            
        }
        
        return result;
        
    }
    

}
