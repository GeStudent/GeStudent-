/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.entities;

/**
 *
 * @author user
 */
public class MenuMeal {

    private String namemenu;
    private String namemeal;
    private String description;
    private String typemenumeal;
    private int id_menu;
    private int id_meal;

    public MenuMeal(int id_meal, int id_menu, String namemeal, String namemenu, String description, String type) {
        this.id_meal = id_meal;
        this.id_menu = id_menu;
        this.namemeal = namemeal;
        this.namemenu = namemenu;
        this.description = description;
        this.typemenumeal = typemenumeal;

    }

    public MenuMeal(String namemenu, String description, String type, int id_menu, int id_meal) {
        this.namemenu = namemenu;
        this.description = description;
        this.typemenumeal = typemenumeal;
        this.id_menu = id_menu;
        this.id_meal = id_meal;
    }

    public MenuMeal() {
    }

    public MenuMeal(String namemenu, String menumeal, String description, String type) {
        this.namemenu = namemenu;

        this.description = description;
        this.typemenumeal = typemenumeal;
    }

    public MenuMeal(String namemenu, String menumeal, String description, String type, int id_menu, int id_meal) {
        this.namemenu = namemenu;

        this.description = description;
        this.typemenumeal = typemenumeal;
        this.id_menu = id_menu;
        this.id_meal = id_meal;
    }

    public MenuMeal(int id_menu, int id_meal) {
        this.id_menu = id_menu;
        this.id_meal = id_meal;
    }

    public String getNamemenu() {
        return namemenu;
    }

    public void setNamemenu(String namemenu) {
        this.namemenu = namemenu;
    }

    public String getNamemeal() {
        return namemeal;
    }

    public void setNamemeal(String namemeal) {
        this.namemeal = namemeal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    

    public int getId_menu() {
        return id_menu;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
    }

    public int getId_meal() {
        return id_meal;
    }

    public void setId_meal(int id_meal) {
        this.id_meal = id_meal;
    }

    public String getTypemenumeal() {
        return typemenumeal;
    }

    public void setTypemenumeal(String typemenumeal) {
        this.typemenumeal = typemenumeal;
    }
    
    

    @Override
    public String toString() {
        return "MenuMeal{" + "namemenu=" + namemenu + ", namemeal=" + namemeal + ", description=" + description + ", type=" + typemenumeal + ", id_menu=" + id_menu + ", id_meal=" + id_meal + '}';
    }

}
