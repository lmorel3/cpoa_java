/*
 * A CourtsContainer contains many Court.
 * It corresponds to a hour of a day.
 */
package view.planning;

import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author laurent
 */
public class CourtsContainer extends JPanel {
    
    public CourtsContainer(){
                
        courts = new ArrayList();
        initComponents();
                
    }
    
    private void initComponents(){
                
        setLayout(new java.awt.GridLayout(2, 3));

        for (int i = 0; i < 6; i++) {
            courts.add(new Court());
            add(courts.get(i));
        }
        
        
    }
    
    public ArrayList<Court> getCourts(){
        return courts;
    }
    
    /**
     * Get a specific Court of this CourtsContainer
     * @param courtNumber
     * @return the Court
     */
    public Court getCourt(int courtNumber){
        return (Court) courts.get(courtNumber);
    }
    
    private final ArrayList<Court> courts;
    
}
