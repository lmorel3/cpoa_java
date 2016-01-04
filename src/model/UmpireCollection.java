/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Rouquette Loïc
 */
public class UmpireCollection{
    
    /**
     * 
     * @param umpire
     * @return 
     */
    public static Boolean create(Umpire umpire) {
        
        ArrayList<Object> params;
        params = new ArrayList<>();
        
        params.add((String)umpire.getForename());
        params.add((String)umpire.getLastname());
        params.add((String)umpire.getCountry());
        params.add("Umpire");
        params.add((String)umpire.getLevel());
        
        
        Connector.getConnection().query("Insert into person (forename, lastname, nationality, person_type, person_level) values (?, ?, ?, ?, ?)", params);
        
        
      return true;
    }
    
    /**
     * 
     * @param umpireId
     * @return 
     */
    public static ArrayList<Umpire> read(int umpireId) {
        ArrayList<Umpire> result = new ArrayList<>();
        
        Umpire current;
        
        ArrayList<Object> params = new ArrayList<>();
        params.add(umpireId);
        
        ArrayList<HashMap<String, Object>> queryResponses = Connector.getConnection().query("Select * From person where person_type = 'Umpire' and person_id = ?", params);
        for(HashMap<String, Object> queryResponse : queryResponses) {
            
            current = new Umpire(queryResponse);
            
            result.add(current);
            
        }
        
        return result;
    }
    
    /**
     * 
     * @param umpireId
     * @return 
     */
    public static Boolean delete(int umpireId) {
        
        ArrayList<Object> params = new ArrayList<>();
        params.add(umpireId);
        
        Connector.getConnection().query("Delete from table where person_id = ? and person_type = 'Umpire'", params);
        
        return true;
    }
    
    /**
     * 
     * @param umpireId
     * @param newUmpire
     * @return 
     */
    public static Boolean update(int umpireId, Umpire newUmpire) {
        
        return true;
    }

}
