/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.Date;
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
    
    public static ArrayList<Match> readByDate(Date date) {
        
        ArrayList<Match> result = new ArrayList<>();
        ArrayList<Object> params = new ArrayList<>();
        
        params.add(date);
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * From match where date_match = ?", params);
        
        Match current;
        
        for(HashMap<String, Object> row : cursor) {
        
            current = new Match(row);
            result.add(current);
        
        }
        
        return result;
        
    }
    
    public static int countMatchOfPhase(int phase) {
        
        int result = 0;
        ArrayList<Object> params = new ArrayList<>();
        
        params.add(phase);
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select match_id from match where phase = ?", params);
        
        for(HashMap<String, Object> row : cursor) {
            
            result += 1;
            
        }
        
        return result;
        
    }
    
    public static void generatePhase(int phase) {
        
        int previousPhase;
        int numberOfPreviousPhase = 0;
        
        switch(phase) {
            case 0:
                previousPhase = 0;
                numberOfPreviousPhase = 0;
        }
        
        if (MatchCollection.countMatchOfPhase(previousPhase) != numberOfPreviousPhase) {
            
            
        }
        
        
        
    }
    
}
