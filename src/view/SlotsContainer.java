/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author laurent
 */
public class SlotsContainer extends JPanel {
    
    public SlotsContainer(){
                
        slots = new ArrayList();
        initComponents();
                
    }
    
    private void initComponents(){
                
        setLayout(new java.awt.GridLayout(2, 3));

        for (int i = 0; i < 6; i++) {
            slots.add(new Slot());
            add(slots.get(i));
        }
        
        
    }
    
    private final ArrayList<Slot> slots;
    
}
