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
public class PlayerCollection {
    
    /**
     * Return the player where player_id = index
     * @param index the id of the player
     * @return player
     */
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
    
    /**
     * Return the list of the players who participate at a match
     * @param matchIndex id of the match
     * @return the list of the players of a match
     */
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
    
    /**
     * Return the list of the player who won the previous Phase and don't
     * participate to the current phase.
     * @param phase the number of the pahse. Use Match.PHASE_
     * @return the list of the player who won the previous Phase and don't
     * participate to the current phase.
     */
    public static ArrayList<Player> readFreePlayerByPhase(int phase) {
        
        ArrayList<Object> params = new ArrayList<>();
        ArrayList<Player> result = new ArrayList<>();
        Player current;
        
        ArrayList<HashMap<String, Object>> cursor;
        
        if(phase != Match.PHASE_QUALIFICAITON) {
        
        params.add(phase);
        params.add(phase*2);
        
        cursor = Connector.getConnection().query(""
                +"Select * "
                +"From person "
                +"Where person_type = 'Player' and person_id not in ("
                        +"Select p.person_id "
                        +"From match m, person p, player_match pm "
                        +"Where m.MATCH_ID = pm.MATCH_ID "
                            +"and pm.PERSON_ID = p.person_id "
                            +"and person_type = 'Player' "
                            +"and m.phase = ?) and person_id in ( "
                                    +"Select winner "
                                        +" From match "
                                        +" Where match.phase = ?)"      
                , params);
        } else {
         params.add(phase);
        
        cursor = Connector.getConnection().query(""
                +"Select * "
                +"From person "
                +"Where person_type = 'Player' and person_id not in ("
                        +"Select p.person_id "
                        +"From match m, person p, player_match pm "
                        +"Where m.MATCH_ID = pm.MATCH_ID "
                            +"and pm.PERSON_ID = p.person_id "
                            +"and person_type = 'Player' "
                            +"and m.phase = ?)"     
                , params);
  
        }
        
        
        
        for(HashMap<String, Object> row : cursor) {
            
            current = new Player(row);
            result.add(current);
            
        }
        
        return result;
        
    }
    
    /**
     * Return the list of all the players
     * @return the list of all the players
     */
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
    
    public static ArrayList<Player> getFreeAt(Date date, int slot) {
        
        ArrayList<Object> params = new ArrayList<>();
        
        params.add(date);
        params.add(slot);
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query(""
                
                +"Select * " +
                "From person " +
                "Where person_type = '"+Person.TYPE_PLAYER+"' " +
                "and person_id not in " +
                "(Select person_id " +
                "From player_match bm, match m " +
                "Where bm.MATCH_ID = m.MATCH_ID and m.DATE_MATCH = ? and m.SLOT_ID = ?)"
                
                , params);
        
        ArrayList<Player> result = new ArrayList<>();
        
        
        for(HashMap<String, Object> row : cursor) {
            
            result.add(new Player(row));
            
        }
        
        return result;
        
    }
    
   
}
