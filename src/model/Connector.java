/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * @author Rouquette Loïc
 */
public class Connector {
    
    public static Connector instance;
    private Connection database;
   
    private Connector() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("properties.properties"));

            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");
            String driver = properties.getProperty("driver");
            
            Class.forName(driver);

            database = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion effective !");         

       } catch (Exception e) {
            System.err.println(e.getMessage());
       }      
    }
    
    public static Connector getConnection() {
        
        if(instance == null) {
            
            instance = new Connector();
            
        }
        
        return instance;
        
    }
    
    public ResultSet query(String statement, Object[] params) {
        int cpt = 1;
        try {
        PreparedStatement stmt = database.prepareStatement(statement);
          for(Object param: params) {
            
            if(param instanceof Date) {
                
            } else if(param instanceof Integer) {
                stmt.setInt(cpt, (Integer)param);
            } else if(param instanceof Float) {
                
            } else if(param instanceof Boolean) {
                
            } else { // String ou autre
                stmt.setString(cpt, (String)param);
            }
            cpt += 1;
        }
        ResultSet result = stmt.executeQuery();
        return result;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
}
