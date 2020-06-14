/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.entities;

import java.util.Objects;

/**
 *
 * @author user
 */
public class menu {
    private int id_menu;
    private String name;
    private String description;

    public menu() {
    }

    public menu(int id_menu, String name, String description) {
        this.id_menu = id_menu;
        this.name = name;
        this.description = description;
    }

    public menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId_menu() {
        return id_menu;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
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
        hash = 97 * hash + this.id_menu;
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
        final menu other = (menu) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "menu{" + "id_menu=" + id_menu + ", name=" + name + ", description=" + description + '}';
    }
}
