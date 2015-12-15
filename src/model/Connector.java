/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * 
 * @author Rouquette Loïc
 */
public class Connector {
    
    public static Connection instance;
   
    private Connector() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("properties.properties"));

            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");
            String driver = properties.getProperty("driver");
            
            Class.forName(driver);

            instance = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion effective !");         

       } catch (Exception e) {
            e.printStackTrace();
       }      
    }
    
    public static Connection getConnection() {
        
        if(instance == null) {
            
            new Connector();
            
        }
        
        return instance;
        
    }
    
}
