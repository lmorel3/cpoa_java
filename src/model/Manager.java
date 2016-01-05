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
public class Manager extends Person {
   
    private String login, password;

    public Manager(int personId, String forename, String lastname, String country, String login, String password) {
        super(personId, forename, lastname, country);
        this.login = login;
        this.password = password;
    }
    
    public Manager(HashMap<String, Object> datas){
        this.hydrate(datas);
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
    
    public static Manager connectManager(String login, String password){
        
        ArrayList<Object> params = new ArrayList<>();
        params.add(login);
        params.add(password);
        
        ArrayList<HashMap<String, Object>> result = Connector.getConnection().query("SELECT * FROM person WHERE login = ? AND person_password = ?", params);
        
        System.out.println(result);
        
        if(!result.isEmpty()){
            
            Manager manager = new Manager(result.get(0));
            return manager;
            
        }
        
        return null;
        
    } 
    
    private void hydrate(HashMap<String, Object> datas) {        
        
        try {
            
            int umpireId = (int)((BigDecimal)datas.get("PERSON_ID")).intValue();
            String foreName = (String)datas.get("FORENAME");
            String lastName = (String)datas.get("LASTNAME");
            String country = (String)datas.get("NATIONALITY");
            this.login = (String)datas.get("LOGIN");
            this.password = (String)datas.get("PERSON_PASSWORD");
            
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
