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
    
    public static ArrayList<Hotel> read(int index) {
        
        ArrayList<Object> params = new ArrayList<>();
        
        ArrayList<Hotel> result = new ArrayList<>();
        
        ArrayList<HashMap<String, Object>> cursor = Connector.getConnection().query("Select * From hotel where hotel_id = ?", params);
        
        
        
        
        return result;
        
        
    }
    
    

}
