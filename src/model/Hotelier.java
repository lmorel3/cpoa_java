/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 * 
 * @author Rouquette Loïc
 */
public class Hotelier extends Person {
    
    private String login;
    private String password;
    private Hotel hotel;
    
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

}
