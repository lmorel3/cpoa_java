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
 * @author laurent
 */
public class ManagerCollection {
    
    public static Manager readOne(int index) {
        
        ArrayList<Object> params = new ArrayList<>();
        params.add(index);
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * From person where person_id = ? and person_type = 'Manager'", params);
        
        Manager result = new Manager();
        
        for(HashMap<String, Object> row : cursor) {
        
            result.hydrate(row);
        
        }
        
        return result;
    }
    
    public static Manager hasOne(String login, String password) {
        
        ArrayList<Object> params = new ArrayList<>();
        
        params.add(login);
        params.add(password);
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * From person where person_type='"+Person.TYPE_MANAGER+"' and login = ? and person_password = ?", params);
        
        for(HashMap<String, Object> row : cursor) {
            
            return new Manager(row);
            
        }
        
        return null;
        
    }
    
    
}
