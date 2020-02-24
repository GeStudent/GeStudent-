/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author user
 */
public class Menu {

    private int id;
    private String name;
    private String description;
    
    
    
    //public Set<menu> menu;
public Menu() {
        
    }
    
    public Menu(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Menu other = (Menu) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Menu{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }

    public String setName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
//    public boolean recherchermenu(menu m)
//    {
//        return menu.stream().anyMatch(x->x.equals(m));
//    }
    
    
    


}
