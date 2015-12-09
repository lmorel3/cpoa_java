/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import app.MenuActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
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
        
        menuBar = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        
        menu.setText("Générer les matchs");
        menuBar.add(menu);
        
        menuQualif = new JMenuItem("Qualifications");
        menuHuitieme = new JMenuItem("Huitièmes de finale");
        menuQuart = new JMenuItem("Quarts de finale");
        menuDemi = new JMenuItem("Demies-finales");
        menuFinal = new JMenuItem("Finale");
        
        menuQualif.addActionListener(new MenuActionListener());
        menuHuitieme.addActionListener(new MenuActionListener());
        menuQuart.addActionListener(new MenuActionListener());
        menuDemi.addActionListener(new MenuActionListener());
        menuFinal.addActionListener(new MenuActionListener());
        
        menu.add(menuQualif);
        menu.add(menuHuitieme);
        menu.add(menuQuart);
        menu.add(menuDemi);
        menu.add(menuFinal);
        
        
        setJMenuBar(menuBar);
        
    }
    
    private void generateTopTabs(){
        
        for (int i = 1; i <= NB_DAYS; i++) {
            daysTab.add(new DayPane());
            tabbedPaneTop.add("Jour " + i, (DayPane) daysTab.get(i-1));
        }

    }
    
    private final javax.swing.JTabbedPane tabbedPaneTop;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menu;
    
    private JMenuItem menuQualif;
    private JMenuItem menuHuitieme;
    private JMenuItem menuQuart;
    private JMenuItem menuDemi;
    private JMenuItem menuFinal;

    
    private final ArrayList daysTab;
    private final int NB_DAYS = 9;
    
}
