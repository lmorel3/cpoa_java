package design_test;

import java.sql.Connection;
import model.Connector;

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
       Connection co = Connector.getConnection(); 
    }
    
}
