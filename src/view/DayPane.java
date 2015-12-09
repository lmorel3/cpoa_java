/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javax.swing.JTabbedPane;

/**
 *
 * @author laurent
 */
public class DayPane extends JTabbedPane {
    
    public DayPane(){
        
        slotsContainer = new ArrayList();  
        
        initComponents();
        
    }
    
    private void initComponents(){
       
        /* Test with random values */
        for (int i = 0; i < 5; i++) {
            slotsContainer.add(new SlotsContainer());
        }
        
        ///////
        
        setTabPlacement(javax.swing.JTabbedPane.LEFT);
        
        addTab("8h", (SlotsContainer) slotsContainer.get(0));
        addTab("11h", (SlotsContainer) slotsContainer.get(1));
        addTab("14h", (SlotsContainer) slotsContainer.get(2));
        addTab("18h", (SlotsContainer) slotsContainer.get(3));
        addTab("21h", (SlotsContainer) slotsContainer.get(4));
    }
    
    private final ArrayList slotsContainer;
    
}
