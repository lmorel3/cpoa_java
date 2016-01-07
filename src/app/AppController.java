/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import javax.swing.JOptionPane;
import model.Manager;
import view.main.ChooseModeDialog;
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
       
        manager = Manager.getAccountOf(login, password);
        
        if(manager instanceof Manager){
            
            System.out.println("Utilisateur " + login + " connecté !");
            System.out.println(manager.getLogin() + " <<");
            
            Connection.close();
            
            Planning.display();
            
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
        
    
}
