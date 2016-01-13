/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
    
    /**
     * 
     * @param match
     * @return 
     */
    public static List<Integer> isValid(Match match) {
        
        List<Integer> errors = new ArrayList<>();
        
     /*   private int matchId;
        private String result;
        private Date date;
        private int kind;
        private int phase;
        private Court court;
        private int slot;
        private ArrayList<Player> players;
        private ArrayList<Match> previousMatchs;
        private ArrayList<Umpire> umpires;
        private ArrayList<BallBoy> ballboys;
        private Player winner; */
        
        //Disponibilité des joueurs
        
        errors.add(Match.ERR_PLAYERS);
        
        //Nationalité arbitre
        
        errors.add(Match.ERR_NATIONALITY_CONFLICT);
        
        //Ordre chronologique des phases
        
        
        
        // Match over ne peuvent plus être déplacé
        
        // Arbitre 2 matchs / tournois
        
        // Check reservations
        
        // Match antérieurs ne peut pas être déplacé après les match des phases suivantes.
        
        return errors;
        
    }
    
    public static void create(Match match) {
        
        ArrayList<Object> params = new ArrayList<>();
        
        params.add(Match.getLastId());
        params.add(match.getCourt());
        params.add(match.getSlot());
        params.add(match.getDate());
        params.add(match.getKind());
        params.add(match.getPhase());
        
        if(match.getPreviousMatchs().isEmpty()) {
            
            params.add(null);
            params.add(null);
            
        } else {
        
            params.add(match.getPreviousMatchs().get(0).getMatchId());
            params.add(match.getPreviousMatchs().get(1).getMatchId());
        
        }
        
        Connector.getConnection().query("Insert into match values (?, ?, ?, ?, NULL, ?, ?, NULL, ?, ?", params);
        
    }
    
    public static void finalise(Match match) {
        
        ArrayList<Object> params = new ArrayList<>();
        
        params.add(match.getWinner().getPersonId());
        params.add(match.getResult());
        
        
        
    }
    
}
