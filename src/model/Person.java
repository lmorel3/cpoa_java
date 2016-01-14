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
public abstract class Person {
    
    private int personId;
    private String lastname;
    private String forename;
    private String country;
    
    public static String TYPE_BALLBOY = "BallBoy";
    public static String TYPE_UMPIRE = "Umpire";
    public static String TYPE_PLAYER = "Player";
    public static String TYPE_MANAGER = "MANAGER";
    
    public Person() {}

    public Person(int personId, String lastname, String forename, String country) {
        this.personId = personId;
        this.lastname = lastname;
        this.forename = forename;
        this.country = country;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public static int getLastId() {
        
        ArrayList<Object> params = new ArrayList<>();
        
        ArrayList<HashMap<String, Object>> result = Connector.getConnection().query("Select person_id from person where rownum = 1 order by person_id desc", params);
        
        return 1+(int)((BigDecimal)result.get(0).get("PERSON_ID")).intValue();
        
    }
    
    @Override
    public String toString(){
        return forename + " " + lastname;
    }

    @Override
    public boolean equals(Object o) {
        
        if (!(o instanceof Person)) {
            
            return false;
            
        }
        
        Person p = (Person)o;
        
        return p.getPersonId() == this.getPersonId();
    }
   
    public boolean hasSameNationality(Object o) {
        
        if(!(o instanceof Person)) {
            
            return false;
            
        }
        
        Person p = (Person)o;
        
        return p.getCountry().toLowerCase().equals(this.getCountry().toLowerCase());
        
    }
    
}
