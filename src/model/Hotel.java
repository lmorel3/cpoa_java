/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 
 * @author Rouquette Loïc
 */
public class Hotel {
    private int hotelId;
    private String name;
    private double location[];
    private int capacity;
    private String type;
    private int stars;
    private Option options[];

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getLocation() {
        return location;
    }

    public void setLocation(double latitude, double longitude) {
        this.location[0] = latitude;
        this.location[1] = longitude;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Option[] getOptions() {
        return options;
    }

    public void setOptions(Option[] options) {
        this.options = options;
    }
    
    
    
     public void hydrate(HashMap<String, Object> datas) {        
        
        try {
            
            int hotelId = (int)((BigDecimal)datas.get("HOTEL_ID")).intValue();
            String name = (String)datas.get("NAME");
            double latitude = (Float)((BigDecimal)datas.get("LATITUDE")).floatValue();
            double longitude = (Float)((BigDecimal)datas.get("LONGITUTE")).floatValue();
            int capacity = (int)((BigDecimal)datas.get("CAPACITY")).intValue();
            String type = (String)datas.get("TYPE");
            int stars = (int)((BigDecimal)datas.get("STARS")).intValue();
            
            this.hotelId = hotelId;
            this.name = name;
            this.setLocation(latitude, longitude);
            this.capacity = capacity;
            this.type = type;
            this.stars = stars;
            
            
        }
        catch (Exception e) {
            
            System.err.println(e.getMessage());
            
        }
        
    }
    
    
    
}
