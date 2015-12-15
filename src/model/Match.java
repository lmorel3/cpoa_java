/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Date;

/**
 * 
 * @author Rouquette Loïc
 */
public class Match {

    private int matchId;
    private int[] result;
    private Date date;
    private String kind;
    private String phase;
    private Court court;
    private Player[] players;
    private Match previousMatchs;
    private Umpire[] umpires;
    private BallBoy[] ballboys;
    private Player winner;

   


    public Match(int matchId, Date date, String kind, String phase, Court court, Match previousMatchs, Player[] players, Umpire[] umpires, BallBoy[] ballboys) {
        this.matchId = matchId;
        this.date = date;
        this.kind = kind;
        this.phase = phase;
        this.court = court;
        this.players = players;
        this.previousMatchs = previousMatchs;
    }
    
    public void concludeMatch(int[] result, Player winner) {
        
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
    

    /**
     * Get the value of ballboys
     *
     * @return the value of ballboys
     */
    public BallBoy[] getBallboys() {
        return ballboys;
    }

    /**
     * Set the value of ballboys
     *
     * @param ballboys new value of ballboys
     */
    public void setBallboys(BallBoy[] ballboys) {
        this.ballboys = ballboys;
    }


    /**
     * Get the value of umpires
     *
     * @return the value of umpires
     */
    public Umpire[] getUmpires() {
        return umpires;
    }

    /**
     * Set the value of umpires
     *
     * @param umpires new value of umpires
     */
    public void setUmpires(Umpire[] umpires) {
        this.umpires = umpires;
    }


    /**
     * Get the value of previousMatchs
     *
     * @return the value of previousMatchs
     */
    public Match getPreviousMatchs() {
        return previousMatchs;
    }

    /**
     * Set the value of previousMatchs
     *
     * @param previousMatchs new value of previousMatchs
     */
    public void setPreviousMatchs(Match previousMatchs) {
        this.previousMatchs = previousMatchs;
    }


    /**
     * Get the value of players
     *
     * @return the value of players
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Set the value of players
     *
     * @param players new value of players
     */
    public void setPlayers(Player[] players) {
        this.players = players;
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
    public String getPhase() {
        return phase;
    }

    /**
     * Set the value of phase
     *
     * @param phase new value of phase
     */
    public void setPhase(String phase) {
        this.phase = phase;
    }


    /**
     * Get the value of kind
     *
     * @return the value of kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * Set the value of kind
     *
     * @param kind new value of kind
     */
    public void setKind(String kind) {
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
    public int[] getResult() {
        return result;
    }

    /**
     * Set the value of result
     *
     * @param result new value of result
     */
    public void setResult(int[] result) {
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


    
    
}
