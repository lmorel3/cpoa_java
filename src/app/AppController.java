/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Manager;
import model.Reservation;
import model.ReservationCollection;
import view.main.Connection;
import view.planning.Court;
import view.planning.CourtsContainer;
import view.planning.DayPane;
import view.planning.Planning;

/**
 *
 * @author laurent
 */
public class AppController {
    
    private static Manager manager;

    /**
     * Try to connect the manager (via login and password)
     * If it works, the main view is shown
     */
    public static void connectManager() {
        
        String login = Connection.getLogin();
        String password = Connection.getPassword();
       
        manager = Manager.getAccountOf(login, password);
        
        if(manager instanceof Manager){
            
            System.out.println("Utilisateur " + login + " connecté !");
            System.out.println(manager.getLogin() + " <<");
            
            Connection.close();
            Planning.display();
            AppController.refreshPlanning();
            
        }else{
            JOptionPane.showMessageDialog(Connection.getFrame(),
                "Les informations de connexion entrées sont incorrectes",
                "Connexion impossible",
                JOptionPane.ERROR_MESSAGE);
        }
        

    }

    public static void makeReservation() {

        System.out.println("Reservation :D");
        
    }
    
    public static void refreshPlanning(){
        
        ArrayList<DayPane> dayPanes = Planning.getDayPanes();
                
        DayPane currentDay;
        CourtsContainer currentSlot;
        Court currentCourt;
        
        ArrayList<Reservation> reservations = ReservationCollection.readAll();
        
        int numberOfDay;
        
        for(Reservation reservation : reservations) {
            
            numberOfDay = Settings.getDayNumber(reservation.getStartDate());
            currentDay = dayPanes.get(numberOfDay);
            currentSlot = currentDay.getCourtsContainer(reservation.getSlotId());
            currentCourt = currentSlot.getCourt(reservation.getCourtId());
            
            currentCourt.setInformations(reservation.getReservationName());
            currentCourt.setPhase("Réservation");
            currentCourt.setStatus(Settings.COURT_STATUS_UNAVAILABLE);
            
        }
        
        
        
        
    }
        
    
}
