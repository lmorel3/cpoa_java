/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 
 * @author Rouquette Loïc
 */
public class Hotelier extends Person {
    
    private String login;
    private String password;
    private Hotel hotel;
    
    public Hotelier() {}
    
    public Hotelier(HashMap<String, Object> datas) {
        
        this.hydrate(datas);
        
    }
    
    public Hotelier(int personId, String forename, String lastname, String country, String login, String password) {
        
        super(personId, forename, lastname, country);
        this.login = login;
        this.password = password;
        
    }
    /**
     * Get the value of hotel
     *
     * @return the value of hotel
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * Set the value of hotel
     *
     * @param hotel new value of hotel
     */
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void hydrate(HashMap<String, Object> datas) {        
        
        try {
            
            int umpireId = (int)((BigDecimal)datas.get("PERSON_ID")).intValue();
            String foreName = (String)datas.get("FORENAME");
            String lastName = (String)datas.get("LASTNAME");
            String country = (String)datas.get("NATIONALITY");
            this.login = (String)datas.get("LOGIN");
            this.password = (String)datas.get("PERSON_PASSWORD");
            int hotelId = (int)((BigDecimal)datas.get("HOTEL_ID")).intValue();
            
            this.hotel = HotelCollection.readOne(hotelId);
            
            this.setPersonId(umpireId);
            this.setForename(foreName);
            this.setLastname(lastName);
            this.setCountry(country);
            
        }
        catch (Exception e) {
            
            System.err.println(e.getMessage());
            
        }
        
    }  

}
