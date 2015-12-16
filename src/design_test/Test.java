package design_test;

import java.util.ArrayList;

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
        
        
        params.add((int)10);
        params.add((Integer)10);
        
        for(Object param : params) {
            
            System.out.println(param.getClass().toString());
            if(param instanceof Integer) {
                System.out.println("Is Integer");
            }
            
        }
         
        
    }
    
}
