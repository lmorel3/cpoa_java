/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import app.AppController;
import app.MenuActionListener;
import app.Settings;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author laurent
 */
public final class PlanningManager extends JFrame {
    
    private PlanningManager(){
        
        initComponents();
        
        generateTopTabs();
        
        System.out.println("ContentPane : overPanel");
        
    }
    
    public static void display(){
        
        frame = getFrame();
        
        frame.setVisible(true);
        
    }
    
    public static void close(){
        
        frame = getFrame();
        
        frame.setVisible(false);
        frame.dispose(); // Destroy the frame
        
    }
        
    private static PlanningManager getFrame(){
        
        if(PlanningManager.frame == null){
            frame = new PlanningManager();
        }
        
        return frame;
        
    }    
    
    private void initComponents(){
        
        setSize(797, 496);
        setTitle("Gestion des plannings");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);   
        
        menuBar = new javax.swing.JMenuBar();
        menuGenerate = new javax.swing.JMenu();
        menuDisconnect = new javax.swing.JMenuItem();

        menuGenerate.setText("Générer les matchs");
        menuBar.add(menuGenerate);
        
        menuDisconnect.setText("Se déconnecter");
        menuBar.add(menuDisconnect);
        
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
        
        menuDisconnect.addActionListener((java.awt.event.ActionEvent evt) -> {
            
            validDisconnect();
            
        });
        
        menuGenerate.add(menuQualif);
        menuGenerate.add(menuHuitieme);
        menuGenerate.add(menuQuart);
        menuGenerate.add(menuDemi);
        menuGenerate.add(menuFinal);
        
        setJMenuBar(menuBar);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
           @Override
           public void windowClosing(java.awt.event.WindowEvent windowEvent) {
               validDisconnect();
           }
       });
        
    }
    
    private void validDisconnect(){
        if (JOptionPane.showConfirmDialog(frame, 
                "Etes-vous sûr de vouloir vous déconnecter ? Toutes modification non sauvegardée sera perdue.", "Quitter l'application", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
        {
            PlanningManager.close();
            ChooseModeDialog.display();
        }
    }
    
    private void generateTopTabs(){
        
        daysTab = new ArrayList();
        
        tabbedPaneTop = new javax.swing.JTabbedPane();
        setContentPane(tabbedPaneTop);
        
        for (int i = 1; i <= Settings.NB_DAYS; i++) {
            daysTab.add(new DayPane());
            tabbedPaneTop.add("Jour " + i, (DayPane) daysTab.get(i-1));
        }

    }
    
    
    
    private javax.swing.JTabbedPane tabbedPaneTop;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuGenerate;
    private javax.swing.JMenuItem menuDisconnect;
    
    private JMenuItem menuQualif;
    private JMenuItem menuHuitieme;
    private JMenuItem menuQuart;
    private JMenuItem menuDemi;
    private JMenuItem menuFinal;

    private ArrayList daysTab;
    private static PlanningManager frame;
    
}
