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
        
        umpire.setPersonId(Person.getLastId());
        
        params.add(umpire.getPersonId());
        params.add(umpire.getForename());
        params.add(umpire.getLastname());
        params.add(umpire.getCountry());
        params.add("Umpire");
        params.add(umpire.getLevel());
        
        
        Connector.getConnection().query("Insert into person (person_id, forename, lastname, nationality, person_type, person_level) values (?, ?, ?, ?, ?, ?)", params);
        
        
      return true;
    }
    
    /**
     * 
     * @param umpireId
     * @return 
     */
    public static Umpire readOne(int umpireId) {
        
        Umpire current = new Umpire();
        
        ArrayList<Object> params = new ArrayList<>();
        params.add(umpireId);
        
        ArrayList<HashMap<String, Object>> queryResponses = Connector.getConnection().query("Select * From person where person_type = 'Umpire' and person_id = ?", params);
        for(HashMap<String, Object> queryResponse : queryResponses) {
            
            current.hydrate(queryResponse);
            
            
            
        }
        
        return current;
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
     * @param newUmpire
     * @return 
     */
    public static Boolean update(Umpire newUmpire) {
        
        ArrayList<Object> params = new ArrayList<>();
        
        params.add(newUmpire.getForename());
        params.add(newUmpire.getLastname());
        params.add(newUmpire.getCountry());
        params.add(newUmpire.getLevel());
        params.add(newUmpire.getPersonId());
        
        Connector.getConnection().query("Update person set forename = ?, lastname = ?, nationality = ?, person_level = ? where person_id = ?", params);
        
        return true;
    }

}
