/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import javax.swing.JOptionPane;
import model.Manager;
import model.Match;
import model.MatchCollection;
import view.main.Connection;
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
        
        System.out.println("Login : " + login + " " + password);
       
        if(login.length() > 0 && password.length() > 0){
            manager = Manager.getAccountOf(login, password);
        }
        
        if(manager instanceof Manager){
            
            System.out.println("Utilisateur " + login + " connecté !");
            System.out.println(manager.getLogin() + " <<");
            
            Connection.close();
            Planning.display();
            PlanningController.initPlanning();
            PlanningController.refreshPlanning();
            
        }else{
            JOptionPane.showMessageDialog(Connection.getFrame(),
                "Les informations de connexion entrées sont incorrectes",
                "Connexion impossible",
                JOptionPane.ERROR_MESSAGE);
        }
        

    }
    
    /**
     * Generate automaticaly the match of the phase "phase"
     * @param phase the number of the phase. Use Match.PHASE_
     */
    public static void generatePhase(int phase) {
        
        int previousPhase = 2*phase;
        
        System.out.println(previousPhase);
        System.out.println(MatchCollection.countMatchOfPhase(previousPhase));
        
        if(phase != Match.PHASE_QUALIFICAITON) {
            if (MatchCollection.countMatchOfPhase(previousPhase) != previousPhase) {
            
                System.err.println("La fonction n'est pas possible. La phase précédente n'est pas terminée.");
            
            }
        }
        
    }
    
    
}
