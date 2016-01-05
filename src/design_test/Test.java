package design_test;

import java.util.ArrayList;
import java.util.HashMap;
import model.Umpire;
import model.UmpireCollection;
import model.Connector;
import model.Person;
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
        
        Umpire maurice = UmpireCollection.read(4).get(0);
        
        maurice.setCountry("France");
        
        UmpireCollection.update(maurice);
        
    }
    
}
