/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Rouquette Loïc
 */
public class HotelCollection {
    
    public static Hotel readOne(int index) {
        
        ArrayList<Object> params = new ArrayList<>();
        
        Hotel result = new Hotel();
        
        params.add(index);
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * From hotel where hotel_id = ?", params);
        
        
        for(HashMap<String, Object> row : cursor) {
            
            result.hydrate(row);
            
            
        }
        
        return result;        
        
    }
    
    public static Hotelier hasOne(String login, String password) {
        
        ArrayList<Object> params = new ArrayList<>();
        
        params.add(login);
        params.add(password);
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * From person where person_type='"+Person.TYPE_MANAGER+"' and login = ? and person_password = ?", params);
        
        for(HashMap<String, Object> row : cursor) {
            
            return new Hotelier(row);
            
        }
        
        return null;
        
    }
    

}
