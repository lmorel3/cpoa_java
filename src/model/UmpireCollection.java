/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.Date;
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
        
        ArrayList<HashMap<String, Object>> queryResponses = Connector.getConnection().query("Select * From person where person_type = '"+Person.TYPE_UMPIRE+"' and person_id = ?", params);
        for(HashMap<String, Object> queryResponse : queryResponses) {
            
            current.hydrate(queryResponse);
            
            
            
        }
        
        return current;
    }
    
    /**
     * Return the list of the umpires of a match. The first umpire is the
     * Chair umpire.
     * @param matchIndex The id of the mathc
     * @return the list of the umpires of a match
     */
    public static ArrayList<Umpire> readByMatch(int matchIndex) {
        
        ArrayList<Object> params = new ArrayList<>();
        ArrayList<Umpire> result = new ArrayList<>();
        
        params.add(matchIndex);
        Umpire current;        
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * From person Where person_id IN (Select person_id From umpire_match where match_id = ?) and person_type = '"+Person.TYPE_UMPIRE+"' and person_level = '"+Umpire.CHAIR_LEVEL+"'", params);
        
        for(HashMap<String, Object> row : cursor) {
            
            current = new Umpire(row);
            result.add(current);
            
        }
        
        if(result.isEmpty()) {
            
            
            result.add(new Umpire());
            
        }
        
        cursor = Connector.getConnection().query("Select * From person Where person_id IN (Select person_id From umpire_match where match_id = ?) and person_type = '"+Person.TYPE_UMPIRE+"' and person_level = '"+Umpire.NET_LEVEL+"'", params);
        
        for(HashMap<String, Object> row : cursor) {
            
            current = new Umpire(row);
            result.add(current);
            
        }
               
        return result;        
        
    }
    
    public static ArrayList<Umpire> readAll() {
        ArrayList<Umpire> result = new ArrayList<>();
        
        Umpire current;
        
        ArrayList<Object> params = new ArrayList<>();
        
        ArrayList<HashMap<String, Object>> queryResponses = Connector.getConnection().query("Select * From person where person_type = '"+Person.TYPE_UMPIRE+"'", params);
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

    /**
     * Return the number of matchs where the Umpire participates
     * @param umpire
     * @return the number of matchs where the Umpire participates
     */
    public static int getCountOfMatch(Umpire umpire, int kindOfMatch) {
        
        ArrayList<Object> params = new ArrayList<>();
        
        params.add(umpire.getPersonId());
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * from umpire_match where person_id = ?", params);
        
        return cursor.size();
        
        
    }
    
    public static ArrayList<Umpire> getFreeAt(Date date, int slot) {
        
        ArrayList<Object> params = new ArrayList<>();
        
        params.add(date);
        params.add(slot);
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query(""
                
                +"Select * "
                +"From person "
                +"Where person_type = '"+ Person.TYPE_UMPIRE +"' "
                +"and person_id not in "
                +"(Select person_id "
                +"From umpire_match bm, match m "
                +"Where bm.MATCH_ID = m.MATCH_ID and m.DATE_MATCH = ? and slot_id = ?)"
                
                , params);
        
        ArrayList<Umpire> result = new ArrayList<>();
        
        
        for(HashMap<String, Object> row : cursor) {
            
            result.add(new Umpire(row));
            
        }
        
        return result;
        
    }
    
}
