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
    
    public static ArrayList<Manager> read(int index) {
        
        ArrayList<Object> params = new ArrayList<>();
        params.add(index);
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * From person where person_id = ? and person_type = 'Manager'", params);
        
        Manager current;
        ArrayList<Manager> result = new ArrayList<>();
        
        for(HashMap<String, Object> row : cursor) {
        
            current = new Manager(row);
            result.add(current);
        
        
        }
        
        
        
        return result;
    }
    
    
}
