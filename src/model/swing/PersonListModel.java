/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.swing;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import model.Person;

/**
 *
 * @author laurent
 */
public class PersonListModel extends AbstractListModel implements ComboBoxModel{
    
    ArrayList<Person> persons;
    
    public PersonListModel(){
        persons = new ArrayList<>();
    }
    
    public void setPersons(ArrayList<Person> players){
        this.persons = players;
    } 
    
    public ArrayList<Person> getPersons(){
        return persons;
    }

    @Override
    public int getSize() {
        return persons.size();
    }

    @Override
    public Object getElementAt(int index) {
        return persons.get(index).getForename() + " " + persons.get(index).getLastname();
    }

    @Override
    public void setSelectedItem(Object anItem) {
        currentSelection = anItem;
    }

    @Override
    public Object getSelectedItem() {
        return currentSelection;
    }
    
    private Object currentSelection;
    
}
