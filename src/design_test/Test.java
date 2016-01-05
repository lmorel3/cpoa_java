package design_test;

import java.util.ArrayList;
import java.util.HashMap;
import model.Umpire;
import model.UmpireCollection;
import model.Connector;
<<<<<<< Updated upstream
import model.Person;
=======
import model.Manager;
>>>>>>> Stashed changes
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
        
<<<<<<< Updated upstream
        Umpire maurice = UmpireCollection.read(4).get(0);
=======
        /*ArrayList<Object> params;
        params = new ArrayList<>();
        
        params.add(4);
        Connector co = Connector.getConnection();
>>>>>>> Stashed changes
        
        maurice.setCountry("France");
        
<<<<<<< Updated upstream
        UmpireCollection.update(maurice);
=======
        Umpire carver = new Umpire();
        System.out.println(carver.getForename());
        
        System.out.println(UmpireCollection.read(4).get(0).getCountry());*/
        
        System.out.println(Manager.connectManager("toto", "tata"));

>>>>>>> Stashed changes
        
    }
    
}
