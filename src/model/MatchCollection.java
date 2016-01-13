/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import app.Settings;
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
    
   
    public static void create(Match match) {
        
        /*
        
        **private int matchId;*
        **private Date date;
        **private int kind;
        **private int phase;
        **private Court court;
        **private int slot;
        **private ArrayList<Player> players;
        **private ArrayList<Match> previousMatchs;
        **private ArrayList<Umpire> umpires;
        **private ArrayList<BallBoy> ballboys;
        
        
        */
        
        ArrayList<Object> params = new ArrayList<>();
        
        params.add(Match.getLastId());
        params.add(match.getCourt().getCourtId());;
        params.add(match.getSlot());
        params.add(match.getDate());
        params.add(match.getKind());
        params.add(match.getPhase());
        
        if(match.getPreviousMatchs().size() != 2) {
            
            params.add(null);
            params.add(null);
            
        } else {
        
            params.add(match.getPreviousMatchs().get(0).getMatchId());
            params.add(match.getPreviousMatchs().get(1).getMatchId());
        
        }
        
        match.setMatchId(Match.getLastId());
        
        Connector.getConnection().query("Insert into match values (?, ?, ?, ?, NULL, ?, ?, NULL, ?, ?)", params);
        
        for(Player p : match.getPlayers()) {

            params.clear();
            params.add(match.getMatchId());
            params.add(p.getPersonId());
            Connector.getConnection().query("Insert into player_match values (?,?)", params);
            
        }
        
        for(Umpire u : match.getUmpires()) {
            
            params.clear();
            params.add(match.getMatchId());
            params.add(u.getPersonId());
            Connector.getConnection().query("Insert into umpire_match values (?,?)", params);
            
        }
        
        for(BallBoy b : match.getBallboys()) {
            
            params.clear();
            params.add(match.getMatchId());
            params.add(b.getPersonId());
            Connector.getConnection().query("Insert into ballboy_match values (?,?)", params);
            
        }
        
    }
    
    public static void finalise(Match match) {
        
        ArrayList<Object> params = new ArrayList<>();
        
        
        params.add(match.getWinner().getPersonId());
        params.add(match.getResult());
        
        Connector.getConnection().query("Update match set winner = ?, results = ?", params);
        
        
        
    }
    
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
        
       
    /**
     * Verify if the players don't already play at the same time
     * @param match 
     * @return true : the player are not taken
     * false : the players are taken
     */
    public static boolean checkPlayersDisponibility(Match match) {
        
        ArrayList<Player> players = match.getPlayers();
        
        int day;
        int year;
        int month;
        
        Date correctedDate;
        
        for(Player p : players) {
            
            
            day = match.getDate().getDate();
            year = match.getDate().getYear()+1900;
            month = match.getDate().getMonth()+1;
            
            correctedDate = new java.util.Date(year, month, day);
            
            if (p.playAt(correctedDate, match.getSlot())) {
                
                return false;
                
            }
            
            
        }
        
        return true;
        
    }
    
    /**
     * Verify if the chair Umpire has not the same nationality as the players
     * @param match
     * @return true : Umpire and all the players have different nationality
     * false : one of the players have the same nationality as the Chair umpire.
     */
    public static boolean checkPlayersAndUmpireNationality(Match match) {
        
        ArrayList<Player> players = PlayerCollection.readByMatch(match.getMatchId());
        
        Umpire chairUmpire = UmpireCollection.readByMatch(match.getMatchId()).get(0);
        
        for(Player p : players) {
            
            if(p.getCountry().equals(chairUmpire.getCountry())) {
                
                return false;
                
            }
            
        }

        return true;
        
    }
    
    /**
     * Verify that the match respects the phases order
     * @param match
     * @return true if the phase respects the pahses order, else false
     */
    public static boolean checkPhasesOrder(Match match) {
        
        ArrayList<Object> params = new ArrayList<>();
        
        params.add(match.getMatchId());
        params.add(match.getMatchId());
        
        int day = match.getDate().getDate();
        int year = match.getDate().getYear()+1900;
        int month = match.getDate().getMonth()+1;
        
        Date correctedDate = new Date(year, month, day);
        
        params.add(correctedDate);
        params.add(correctedDate);
        
        params.add(match.getSlot());
        
        return Connector.getConnection().query(""
                +"Select * "
                +"From match "
                +"Where (PREVIOUS_MATCH1 = ? OR PREVIOUS_MATCH2 = ?) "
                +"AND (DATE_MATCH < ? OR (DATE_MATCH = ? AND SLOT_ID <= ?))"
                , params).isEmpty();
        
        
    }
    
    /**
     * Verify is a match can be Updated or deleted.
     * @param match
     * @return true if the match is locked else false
     */
    public static boolean isLocked(Match match) {
        
        if(match.getWinner().getPersonId() == 0) {
            
            return false;
            
        }

        return true;
        
    }
    
    /**
     * Verify if the umpire has not too many matchs
     * @param match
     * @return true : if the umpire is available else false
     */
    public static boolean checkCountOfMatchOfUmpire(Match match) {
        
        if(match.getUmpires().isEmpty()) {
            
            return false;
            
        }
        
        
        // match.getUmpires().get(0) return the ChairUmpire
        if (UmpireCollection.getCountOfMatch(match.getUmpires().get(0)) <= 4) {
            
            return false;
            
        }
        
        return true;
        
    }
    
    /**
     * Verify if the slot and the court are taken by a RERSERVATION
     * @param match
     * @return 
     */
    public static boolean courtAndSlotAreReserved(Match match) {
        
        return ReservationCollection.exist(match.getDate(), match.getCourt().getCourtId(), match.getSlot());
        
    }
    
    /**
     * Verify if the slot and the court are taken by a MATCH
     * @param match
     * @return 
     */
    public static boolean courtAndSlotAreTaken(Match match) {
        
        return MatchCollection.exist(match.getDate(), match.getCourt().getCourtId(), match.getSlot());
        
    }
     
    /**
     * Return if the slot contains a match
     * @param date
     * @param courtId
     * @param slotId
     * @return 
     */
     public static boolean exist(Date date, int courtId, int slotId) {
        
        ArrayList<Object> params = new ArrayList<>();
        
        params.add(date);
        params.add(courtId);
        params.add(slotId);
        
        int size = Connector.getConnection().query("Select * From match where date_match = ? and court_id = ? and slot_id = ?", params).size();
        
        if(size == 0) {
            
            return false;
            
        }
        
        return true;
        
    }
    
}
