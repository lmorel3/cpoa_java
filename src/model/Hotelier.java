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
    
    public Hotelier(int personId, String forename, String lastname, String country, String login, String password) {
        
        super(personId, forename, lastname, country);
        this.login = login;
        this.password = password;
        
    }

}
