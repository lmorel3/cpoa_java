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
    
    public static ArrayList<Player> readByMatch(int matchIndex) {
        
        ArrayList<Object> params = new ArrayList<>();
        ArrayList<Player> result = new ArrayList<>();
        
        params.add(matchIndex);
        Player current;        
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * From person Where person_id IN (Select person_id From player_match where match_id = ?) and person_type = '"+Person.TYPE_PLAYER+"'", params);
        
        for(HashMap<String, Object> row : cursor) {
            
            current = new Player(row);
            result.add(current);
            
        }
        
        return result;        
        
    }
    
    public static ArrayList<Player> readAll() {
        
        ArrayList<Player> result = new ArrayList<>();
        Player current;
        
        ArrayList<Object> params = new ArrayList<>();
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * From person where person_type = 'Player'", params);
        for(HashMap<String, Object> row : cursor) {
            
            current = new Player(row);
            result.add(current);
            
        }

        return result;
        
    }    
   
}
