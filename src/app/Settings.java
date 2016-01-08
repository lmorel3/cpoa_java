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
        
        long diff = date.getTime() - DATE_BEGINNING.getTime();
        
        return (int) (diff/(1000*60*60*24)) + 1;
        
    }

    
}
