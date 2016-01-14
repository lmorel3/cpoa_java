/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.swing;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import model.Person;
import model.Player;

/**
 *
 * @author laurent
 */
public class DoublePersonListRenderer extends JLabel implements ListCellRenderer<Person> {
    
    public DoublePersonListRenderer(){
        
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Person> list, Person value, int index, boolean isSelected, boolean cellHasFocus) {

        //String code = country.getCode();
        //ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/" + code + ".png"));
        //setIcon(imageIcon);
        
        if(value instanceof Person){
            setText(value.getLastname() + '-' + ((Player)value).getPartner().getLastname());
        }else{
            setText("");
        }
        
        return this;
        
    }
    
}