package design_test;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import model.Connector;
import model.Umpire;
import model.UmpireCollection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author Rouquette Loïc
 */
public class Test {
    
    public static void main(String args[]) {
        
        ArrayList<Object> params;
        params = new ArrayList<>();
        
        params.add(4);
        Connector co = Connector.getConnection();
        
        ArrayList<HashMap<String,Object>> result = Connector.getConnection().query("Select * From person where person_id = ?", params);
        
        Umpire carver = new Umpire(result.get(0));
       
        System.out.println(carver.getForename());
        
        System.out.println(UmpireCollection.read(4).get(0).getCountry());
        
    }
    
}
