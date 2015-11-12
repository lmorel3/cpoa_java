/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author laurent
 */
public final class MainView extends JFrame {
    
    public MainView(){
        
        initComponents();
        
        daysTab = new ArrayList();
        
        tabbedPaneTop = new javax.swing.JTabbedPane();
        
        generateTopTabs();
        
        setContentPane(tabbedPaneTop);
        
        System.out.println("ContentPane : overPanel");
        
    }
    
    private void initComponents(){
        
        setSize(797, 496);
        setTitle("Gestion des plannings");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        
    }
    
    private void generateTopTabs(){
        
        for (int i = 1; i <= NB_DAYS; i++) {
            daysTab.add(new DayPane());
            tabbedPaneTop.add("Jour " + i, (DayPane) daysTab.get(i-1));
        }

    }
    
    private javax.swing.JTabbedPane tabbedPaneTop;
    private ArrayList daysTab;
    private final int NB_DAYS = 9;
    
}
