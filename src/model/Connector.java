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
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

/**
 * 
 * @author Rouquette Loïc
 */
public class Connector {
    
    public static Connector instance;
    private Connection dataBase;
   
    private Connector() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("properties.properties"));

            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");
            String driver = properties.getProperty("driver");
            
            Class.forName(driver);

            this.dataBase = DriverManager.getConnection(url, user, password);      


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
    
    public ArrayList<HashMap<String, Object>> query(String statement, ArrayList<Object> params) {
        int cpt = 1;
        
        int count = statement.length() - statement.replace("?", "").length();
        
         System.err.println(count+" "+params.size());
        
        if (params.size() != count) {
            
            System.err.println("Le nombre d'argument n'est pas correct");
            
        }
        
       
        
        try {

        PreparedStatement stmt = dataBase.prepareStatement(statement);
          for(Object param: params) {


            if(param instanceof Date) {
                
                stmt.setDate(cpt, (Date)param);
                
            } else if(param instanceof Integer) {
                
                stmt.setInt(cpt, (Integer)param);
                
            } else if(param instanceof Float) {
                
                stmt.setFloat(cpt, (Float)param);
                
            } else if(param instanceof Boolean) {
                
                stmt.setBoolean(cpt, (Boolean)param);
                
            } else { // String ou autre
                
                stmt.setString(cpt, (String)param);
                
            }
            
            cpt += 1;
            
        }
        
        
        
        ResultSet queryResponse = stmt.executeQuery();
        
        ResultSetMetaData queryResponseMetaData = queryResponse.getMetaData();
        
        ArrayList<HashMap<String,Object>> result;
        HashMap<String, Object> resultLine;
        result = new ArrayList<>();
                
        while (queryResponse.next()) {
            
            
            resultLine = new HashMap<String, Object>();
            for(int i = 1; i<= queryResponseMetaData.getColumnCount(); i++) {
                resultLine.put(queryResponseMetaData.getColumnName(i).toUpperCase(), queryResponse.getObject(i));
            }
            
            result.add(resultLine);
            
        }
        
        queryResponse.close();
        
        stmt.close();
        
        
        return result;
        
        
        } catch (Exception e){
            
            e.printStackTrace();
            
        }
        
        return null;
    }
    
}
