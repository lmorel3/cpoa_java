/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

/**
 *
 * @author laurent
 */
public class MenuActionListener implements ActionListener{  
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("ACTION");
        System.out.println(e);
    }
    
}
