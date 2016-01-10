package app;

import java.util.Date;
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author laurent
 */
public class Settings {
    
    public static final int MODE_PLAYER = 1;
    public static final int MODE_MANAGER = 0;
    public static final int NB_DAYS = 9;
    
    public static final Date DATE_BEGINNING = new Date(2015, 8, 1);
    public static final Date DATE_END = new Date(2015, 8, 9);
    
    public static final String EMPTY_COURT_DESC = "Aucun match<br>ou réservation<br>sur ce court</body>";
    public static final int COURT_STATUS_FREE = 0;
    public static final int COURT_STATUS_UNAVAILABLE = 1;
    public static final int COURT_STATUS_CLOSED = 2;
    
    public static final int MATCH_TYPE_SIMPLE = 0;
    public static final int MATCH_TYPE_DOUBLE = 1;
    
    /**
     * Get the day number compared to the DATE_BEGINNING
     * @param date
     * @return the day number
     */    
    public static int getDayNumber(Date date){
        
        int dayNumber = 0;
        
        Calendar beginningDate = Calendar.getInstance();
        Calendar currDate = Calendar.getInstance();
        
        beginningDate.setTime(convertedDate(date));
        currDate.setTime(date);
        
        while(!beginningDate.after(currDate)) {
            beginningDate.add(Calendar.DATE, 1);
            dayNumber += 1;
        }
         
        return dayNumber;
        
    }
    
    public static Date generateDate(int dayNumber) {
        
        Date date;
        
        int day = DATE_BEGINNING.getDate();
        int month = DATE_BEGINNING.getMonth();
        int year = DATE_BEGINNING.getYear();
        
        date = new Date(year, month, day + dayNumber);
        
        return date;
        
    }
    
    public static java.util.Date convertedDate(Object date) {
        
        Date result;
        
        int year;
        int month;
        int day;
        
        if(date instanceof java.sql.Date) {
        
            year = ((java.sql.Date)date).getYear();
            month = ((java.sql.Date)date).getMonth();
            day = ((java.sql.Date)date).getDate();
                    
        } else if (date instanceof java.util.Date) {
            
            year = ((java.util.Date)date).getYear()-1900;
            month = ((java.util.Date)date).getMonth();
            day = ((java.util.Date)date).getDate();
            
        } else {
            
            year = month = day = 0;
            
        }
        
        result = new Date(year, month, day);
        
        return result;
        
    }

    
}
