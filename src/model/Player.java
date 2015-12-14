/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author laurent
 */
public class Player extends Person{
 
    private Player partner;
    
    public Player(int personId, String lastname, String forename, String country) {
        super(personId, lastname, forename, country);
    }
    
    /**
     * Get the value of partner
     *
     * @return the value of partner
     */
    public Player getPartner() {
        return partner;
    }

    /**
     * Set the value of partner
     *
     * @param partner new value of partner
     */
    public void setPartner(Player partner) {
        this.partner = partner;
    }
    
    
}
