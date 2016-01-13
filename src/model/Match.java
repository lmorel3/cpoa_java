/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Rouquette Loïc
 */
public class Match {

    private int matchId;
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
    private Player winner;

    public final static int KIND_SIMPLE = 1;
    public final static int KIND_DOUBLE = 2;
    
    public final static int PHASE_QUALIFICAITON = 16;
    public final static int PHASE_8EME = 8;
    public final static int PHASE_QUART = 4;
    public final static int PHASE_SEMIFINAL = 2;
    public final static int PHASE_FINAL = 1;
        
    public Match() {
        
        this.previousMatchs = new ArrayList<>(); // Facultative
   
    }
    
    public Match(HashMap<String, Object> datas) {
        
        this.hydrate(datas);
        
    }

    public Match(int matchId, Date date, int kind, int phase, Court court, int slot, ArrayList<Match> previousMatchs, ArrayList<Player> players, ArrayList<Umpire> umpires, ArrayList<BallBoy> ballboys) {
        this.matchId = matchId;
        this.date = date;
        this.kind = kind;
        this.slot = slot;
        this.phase = phase;
        this.court = court;
        this.players = players;
        this.previousMatchs = previousMatchs;
    }
    
    public void concludeMatch(String result, Player winner) {
        
        this.result = result;
        this.winner = winner;
        
    }
    
     /**
     * Get the value of winner
     *
     * @return the value of winner
     */
    public Player getWinner() {
        return winner;
    }

    /**
     * Set the value of winner
     *
     * @param winner new value of winner
     */
    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Match> getPreviousMatchs() {
        return previousMatchs;
    }

    public void setPreviousMatchs(ArrayList<Match> previousMatchs) {
        this.previousMatchs = previousMatchs;
    }

    public ArrayList<Umpire> getUmpires() {
        return umpires;
    }

    public void setUmpires(ArrayList<Umpire> umpires) {
        this.umpires = umpires;
    }

    public ArrayList<BallBoy> getBallboys() {
        return ballboys;
    }

    public void setBallboys(ArrayList<BallBoy> ballboys) {
        this.ballboys = ballboys;
    }
    

   

    /**
     * Get the value of court
     *
     * @return the value of court
     */
    public Court getCourt() {
        return court;
    }

    /**
     * Set the value of court
     *
     * @param court new value of court
     */
    public void setCourt(Court court) {
        this.court = court;
    }


    /**
     * Get the value of phase
     *
     * @return the value of phase
     */
    public int getPhase() {
        return phase;
    }

    /**
     * Set the value of phase
     *
     * @param phase new value of phase
     */
    public void setPhase(int phase) {
        this.phase = phase;
    }


    /**
     * Get the value of kind
     *
     * @return the value of kind
     */
    public int getKind() {
        return kind;
    }

    /**
     * Set the value of kind
     *
     * @param kind new value of kind
     */
    public void setKind(int kind) {
        this.kind = kind;
    }


    /**
     * Get the value of date
     *
     * @return the value of date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the value of date
     *
     * @param date new value of date
     */
    public void setDate(Date date) {
        this.date = date;
    }


    /**
     * Get the value of result
     *
     * @return the value of result
     */
    public String getResult() {
        return result;
    }

    /**
     * Set the value of result
     *
     * @param result new value of result
     */
    public void setResult(String result) {
        this.result = result;
    }


    /**
     * Get the value of matchId
     *
     * @return the value of matchId
     */
    public int getMatchId() {
        return matchId;
    }

    /**
     * Set the value of matchId
     *
     * @param matchId new value of matchId
     */
    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
    
    public void hydrate(HashMap<String, Object> datas) {        
        
        try {
            
            int matchId = (int)((BigDecimal)datas.get("MATCH_ID")).intValue();
            int courtId = (int)((BigDecimal)datas.get("COURT_ID")).intValue();
            int winnerId = (int)((BigDecimal)datas.get("WINNER")).intValue();
            int slotId = (int)((BigDecimal)datas.get("SLOT_ID")).intValue();               
            java.util.Date date = new java.util.Date(((java.util.Date)datas.get("DATE_MATCH")).getTime());
            String result = (String)datas.get("RESULTS");
            int kind = (int)((BigDecimal)datas.get("KIND")).intValue();
            int phase = (int)((BigDecimal)datas.get("PHASE")).intValue();
            int previousMatch1Id = (int)((BigDecimal)datas.get("PREVIOUS_MATCH1")).intValue();
            int previousMatch2Id = (int)((BigDecimal)datas.get("PREVIOUS_MATCH2")).intValue();
            
            ArrayList<Umpire> umpires = UmpireCollection.readByMatch(matchId);
            ArrayList<BallBoy> ballBoys = BallBoyCollection.readByMatch(matchId);
            ArrayList<Player> players = PlayerCollection.readByMatch(matchId);
            
            Match previousM1 = MatchCollection.readOne(previousMatch1Id);
            Match previousM2 = MatchCollection.readOne(previousMatch2Id);
            
            ArrayList<Match> previousMatchs = new ArrayList<>();
            previousMatchs.add(previousM1);
            previousMatchs.add(previousM2);
            
            this.matchId = matchId;
            this.court = CourtCollection.readOne(courtId);
            this.slot = slotId;
            this.winner = PlayerCollection.readOne(winnerId);
            this.kind = kind;
            this.phase = phase;
            this.umpires = umpires;
            this.ballboys = ballBoys;
            this.players = players;
            this.date = date;
            this.previousMatchs = previousMatchs;
            this.result = result;
                 
            
        }
        catch (Exception e) {
            
            System.err.println("Erreur d'hydratation Match " + e.getMessage());
            
        }
        
    }
    
     public static int getLastId() {
        
        ArrayList<Object> params = new ArrayList<>();
        
        ArrayList<HashMap<String, Object>> result = Connector.getConnection().query("Select match_id from match where rownum = 1 order by match_id desc", params);
        
        return 1+(int)((BigDecimal)result.get(0).get("MATCH_ID")).intValue();
        
    }


    
    
}
