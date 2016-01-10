/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.ArrayList;
import model.Match;
import model.Reservation;
import model.ReservationCollection;
import view.planning.AddMatch;
import view.planning.Court;
import view.planning.CourtsContainer;
import view.planning.DayPane;
import view.planning.EditMatch;
import view.planning.Planning;

/**
 *
 * @author laurent
 */
public class PlanningController {
   
    public static void refreshPlanning(){
        
        ArrayList<DayPane> dayPanes = Planning.getDayPanes();
                
        DayPane currentDay;
        CourtsContainer currentSlot;
        Court currentCourt;
        
        ArrayList<Reservation> reservationsOfTheDay;
        
        int dayNumber;
       
        for(dayNumber = 0; dayNumber < Settings.NB_DAYS; dayNumber++) {
            
            reservationsOfTheDay = ReservationCollection.read(Settings.generateDate(dayNumber), Settings.generateDate(dayNumber+1));
            
            for(Reservation currentReservation : reservationsOfTheDay) {
                
                currentDay = dayPanes.get(dayNumber);
                currentSlot = currentDay.getCourtsContainer(currentReservation.getSlotId());
                currentCourt = currentSlot.getCourt(currentReservation.getCourtId());
                
                currentCourt.setInformations(currentReservation.getReservationName());
                currentCourt.setPhase("Réservation");
                currentCourt.setStatus(Settings.COURT_STATUS_UNAVAILABLE);
                
            }
            
            
        }
        
    }
    
    public static void initMatchEdition(Match match){
        
        EditMatch.display();
        EditMatch.setMatch(match);
        
    }
    
    public static void initMatchCreation(int matchType){
        
        AddMatch.display(matchType);
           
    }

    public static void initMatchEdition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
         
}
