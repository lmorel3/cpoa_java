/*
 * The DayPane contains different CourtsContainer (one for each hours).
 * It corresponds to a specific day.
 */
package view.planning;

import java.util.ArrayList;
import javax.swing.JTabbedPane;

/**
 *
 * @author laurent
 */
public class DayPane extends JTabbedPane {
    
    public DayPane(int dayNumber){
        
        this.dayNumber = dayNumber;

        initComponents();
                
    }
    
    private void initComponents(){
       
        courtsContainer = new ArrayList();  

        /* Test with random values */
        for (int i = 0; i < 5; i++) {
            courtsContainer.add(new CourtsContainer(dayNumber, i)); // i = slotId
        }
        
        ///////
        
        setTabPlacement(javax.swing.JTabbedPane.LEFT);
        
        addTab("8h", (CourtsContainer) courtsContainer.get(0));
        addTab("11h", (CourtsContainer) courtsContainer.get(1));
        addTab("14h", (CourtsContainer) courtsContainer.get(2));
        addTab("18h", (CourtsContainer) courtsContainer.get(3));
        addTab("21h", (CourtsContainer) courtsContainer.get(4));
    }
    
    /**
     * Get the CourtsContainer for a specific hour
     * @param hourNumber 0 is "8h"
     * @return the CourtsContainer
     */
    public CourtsContainer getCourtsContainer(int hourNumber){
        return (CourtsContainer) courtsContainer.get(hourNumber);
    }
    
    public ArrayList<CourtsContainer> getCourtsContainer(){
        return courtsContainer;
    }
    
    private ArrayList<CourtsContainer> courtsContainer;
    
    private int dayNumber;
    
}
