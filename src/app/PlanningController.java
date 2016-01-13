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
        int coordonate;
        
        for(dayNumber = 0; dayNumber < Settings.NB_DAYS; dayNumber++) {
            
            reservationsOfTheDay = ReservationCollection.read(Settings.generateDate(dayNumber), Settings.generateDate(dayNumber+1));
            
            for(Reservation currentReservation : reservationsOfTheDay) {
                
                currentDay = dayPanes.get(dayNumber);
                currentSlot = currentDay.getCourtsContainer(currentReservation.getSlotId());
                
                // 0 - 1 - 2   <->  1 - 3 - 5
                // 3 - 4 - 5        2 - 4 - 6
                
                switch(currentReservation.getCourtId()) {
                    
                    case 1:
                        coordonate = 0;
                        break;
                    case 2:
                        coordonate = 3;
                        break;
                    case 3:
                        coordonate = 1;
                        break;
                    case 4:
                        coordonate = 4;
                        break;
                    case 5:
                        coordonate = 2;
                        break;
                    case 6:
                        coordonate = 5;
                        break;
                    default:
                        coordonate = -1;
                    
                }
                
                currentCourt = currentSlot.getCourt(coordonate);
                currentCourt.setMatch(currentReservation);
                
            }
            
            
        }
        
    }
    
    public static void initMatchEdition(Match match){
        
        EditMatch.display();
        EditMatch.setMatch(match);
        
    }
    
    public static void initMatchCreation(int matchType, int dayNumber, int slotId, int courtId){
        
        AddMatch.display(matchType, dayNumber, slotId, courtId);
           
    }
    
    public static void createMatch(){
        
        String kind = (String) AddMatch.modelType.getSelectedItem();
        String kindLowerCase = kind.toLowerCase();
        
        int phase = Match.PHASE_QUALIFICAITON;
        if(kindLowerCase.startsWith("huitième"))
            phase = Match.PHASE_8EME;
        else if(kindLowerCase.startsWith("quart"))
            phase = Match.PHASE_QUART;
        else if(kindLowerCase.startsWith("demie"))
            phase = Match.PHASE_SEMIFINAL;
        else if(kindLowerCase.startsWith("finale"))
            phase = Match.PHASE_FINAL;
        
        //int matchId, Date date, int kind, int phase, Court court, int slot, ArrayList<Match> previousMatchs, ArrayList<Player> players, ArrayList<Umpire> umpires, ArrayList<BallBoy> ballboys) {

        
    }

         
}
