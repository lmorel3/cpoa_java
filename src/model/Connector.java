/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
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
        
        if (params.size() != count) {
            
            System.err.println("Le nombre d'argument n'est pas correct");
            
        }
        
       
        
        try {
            int j, remplacementIndex;
            int treatedDate = 0;
            for(Object param: params) {
                
                if(param instanceof java.util.Date) {

                    j = 0;
                    remplacementIndex = -1;
                    while(j < cpt - treatedDate) {
                        remplacementIndex = statement.indexOf("?", remplacementIndex+1);
                        j += 1;
                    }       
                    
                    String firstPart = statement.substring(0, remplacementIndex);
                    String secondPart = statement.substring(remplacementIndex+1, statement.length());
                         
                    Date date = (Date)param;
                    
                    String hours;
                    String minutes;
                    
                    if(date.getHours() < 10) {
                        
                        hours = 0+String.valueOf(date.getHours());
                        
                    } else {
                        
                        hours = String.valueOf(date.getHours());
                        
                    }
                    
                    if(date.getMinutes()< 10) {
                        
                        minutes = 0+String.valueOf(date.getMinutes());
                        
                    } else {
                        
                        minutes = String.valueOf(date.getMinutes());
                        
                    }
                    
                    statement = firstPart
                            + "to_date('"
                            +hours+":"
                            +minutes+" "
                            +date.getDate()+"-"
                            +date.getMonth()+"-"
                            +date.getYear()+"',"
                            + "'HH24:MI DD-MM-YYYY')"
                            + secondPart;
                    
                   // System.out.println(statement);
                    treatedDate += 1;
                
                }
                cpt += 1;
            }

            cpt = 1;
            // System.out.println(statement);
            PreparedStatement stmt = dataBase.prepareStatement(statement);
            
            for(Object param: params) {

                if(param instanceof Date) {
                    cpt -= 1;
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
                            //System.out.println(queryResponseMetaData.getColumnClassName(i));
                            if(queryResponseMetaData.getColumnClassName(i).equals("java.sql.Date")) {

                                resultLine.put(queryResponseMetaData.getColumnName(i).toUpperCase(), new Date(0,0,0));

                            } else if(queryResponseMetaData.getColumnClassName(i).equals("java.math.BigDecimal")) {
                                
                                resultLine.put(queryResponseMetaData.getColumnName(i).toUpperCase(), new BigDecimal(0));

                            } else if(queryResponseMetaData.getColumnClassName(i).equals("java.lang.Boolean")) {

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
