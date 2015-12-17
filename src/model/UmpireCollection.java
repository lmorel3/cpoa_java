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
