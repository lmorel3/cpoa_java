/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import model.Manager;
import view.Connection;

/**
 *
 * @author laurent
 */
public class AppController {
    
    private static Manager manager;

    public static void connectManager(Connection connection) {
        
        String login = connection.getLogin();
        String password = connection.getPassword();
       
        manager = Manager.connectManager(login, password);
        
        if(manager instanceof Manager){
            
            System.out.println("Utilisateur " + login + " connecté !");
            
        }
        

    }
    
    
    
}
