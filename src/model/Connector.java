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
import java.util.Calendar;
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
        
        if (params.size() != count) {
            
            System.err.println("Le nombre d'argument n'est pas correct");
            
        }
        
       
        
        try {

            PreparedStatement stmt = dataBase.prepareStatement(statement);
            for(Object param: params) {


                if(param instanceof java.util.Date) {

                    java.util.Date paramAsDate = (java.util.Date)param;
                    
                    Calendar c = Calendar.getInstance();
                    
                    c.setTime(paramAsDate);
                    c.add(Calendar.YEAR, -1900);
                    c.add(Calendar.MONTH, -1);
                    
                    paramAsDate = c.getTime();
                    
                    
                    stmt.setDate(cpt, new java.sql.Date(paramAsDate.getTime()));

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
          
          
            if(statement.toLowerCase().startsWith("select")) {
                
               

                ResultSetMetaData queryResponseMetaData = queryResponse.getMetaData();

                ArrayList<HashMap<String,Object>> result;
                HashMap<String, Object> resultLine;
                result = new ArrayList<>();

                while (queryResponse.next()) {


                    resultLine = new HashMap<String, Object>();
                    for(int i = 1; i<= queryResponseMetaData.getColumnCount(); i++) {
                        
                        queryResponse.getObject(i);
                        
                        
                        if(queryResponse.wasNull()) {
                            Object typeObject = queryResponse.getObject(i);
                            if(typeObject instanceof Date) {

                            resultLine.put(queryResponseMetaData.getColumnName(i).toUpperCase(), new java.util.Date(0,0,0));

                        } else if(typeObject instanceof Integer) {

                            resultLine.put(queryResponseMetaData.getColumnName(i).toUpperCase(), 0);

                        } else if(typeObject instanceof Float) {

                            resultLine.put(queryResponseMetaData.getColumnName(i).toUpperCase(), 0f);

                        } else if(typeObject instanceof Boolean) {

                            resultLine.put(queryResponseMetaData.getColumnName(i).toUpperCase(), false);

                        } else { // String ou autre

                            resultLine.put(queryResponseMetaData.getColumnName(i).toUpperCase(), "");

                        }
                            
                            
                        } else {
                                       resultLine.put(queryResponseMetaData.getColumnName(i).toUpperCase(), queryResponse.getObject(i));
                        }
                    }                     

                    result.add(resultLine);

                }

                queryResponse.close();

                stmt.close();


                return result;
        
                
                
            }
          
            return new ArrayList<HashMap<String, Object>>();
        
        } catch (Exception e){
            
            e.printStackTrace();
            
        }
        
        return null;
    }
    
}
