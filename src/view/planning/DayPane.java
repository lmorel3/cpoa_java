/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.planning;

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
            slotsContainer.add(new CourtsContainer());
        }
        
        ///////
        
        setTabPlacement(javax.swing.JTabbedPane.LEFT);
        
        addTab("8h", (CourtsContainer) slotsContainer.get(0));
        addTab("11h", (CourtsContainer) slotsContainer.get(1));
        addTab("14h", (CourtsContainer) slotsContainer.get(2));
        addTab("18h", (CourtsContainer) slotsContainer.get(3));
        addTab("21h", (CourtsContainer) slotsContainer.get(4));
    }
    
    private final ArrayList slotsContainer;
    
}
