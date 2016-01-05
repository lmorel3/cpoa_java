/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author laurent
 */
public class Player extends Person{
 
    private Player partner;
    
    public Player(int personId, String lastname, String forename, String country, Player partner) {
        super(personId, lastname, forename, country);
        this.partner = partner;
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
    
    
    
    public void hydrate(HashMap<String, Object> datas) {
        
        ArrayList<HashMap<String, Object>> possiblePartner = Connector.getConnection().query("Select person.* \n" +
            "from person p, partner pr \n" +
            "where (p.person_id = pr.playera_id and pr.playerb_id = XX )\n" +
            "OR ( p.person_id = pr.PLAYERB_ID and pr.playera_id = XX)", new ArrayList<Object>());
        
              
        
        try {
            
            if(!possiblePartner.isEmpty()) {
            
            System.out.println("Le joueur"+datas.get("FORENAME")+"n'a pas de partnenaire");
            
            }  
            
           
            int umpireId = (int)((BigDecimal)datas.get("PERSON_ID")).intValue();
            String foreName = (String)datas.get("FORENAME");
            String lastName = (String)datas.get("LASTNAME");
            String country = (String)datas.get("NATIONALITY");
            String level = (String)datas.get("PERSON_LEVEL");
            Player partner = (Player)datas.get("PARTNER_ID");
            
            this.setPersonId(umpireId);
            this.setForename(foreName);
            this.setLastname(lastName);
            this.setCountry(country);
            this.setPartner(partner);
            
        }
        catch (Exception e) {
            
            System.err.println(e.getMessage());
            
        }
        
    }
    
    
}
