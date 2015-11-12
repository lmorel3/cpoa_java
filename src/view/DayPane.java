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
        
        contentView = new ArrayList();
        
        /* Test with random values */
        for (int i = 0; i < 5; i++) {
            contentView.add(new ContentView());
        }
        
        ///////
        
        setTabPlacement(javax.swing.JTabbedPane.LEFT);
        
        addTab("8h", (ContentView) contentView.get(0));
        addTab("11h", (ContentView) contentView.get(1));
        addTab("14h", (ContentView) contentView.get(2));
        addTab("18h", (ContentView) contentView.get(3));
        addTab("21h", (ContentView) contentView.get(4));
        
        ContentView test = (ContentView) contentView.get(1);
        test.setState(ContentView.NUM_TRAIN1, 1);
        
    }
    
    private ArrayList contentView;
    
}
