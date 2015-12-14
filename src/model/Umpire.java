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
public class Umpire extends Person {
    
    int level;
    
    public Umpire(int personId, String forename, String lastname, String country, int level) {
        super(personId, forename, lastname, country);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    
 
}
