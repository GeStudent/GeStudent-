/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.entities;

import java.util.Date;

/**
 *
 * @author user
 */
public class Evenement {
    private int id_event;
    private String nom;
    private String description;
    private String date;
    private String place;
    private int id_club;
    private int nb_place;

    public Evenement() {
    }
    

    public Evenement(int id_event, String nom, String description, String date, String place, int id_club,int nb_place) {
        this.id_event = id_event;
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.place = place;
        this.id_club = id_club;
        this.nb_place=nb_place;
    }    
    public Evenement(String nom, String description, String date, String place, int id_club,int nb_place) {
       
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.place = place;
        this.id_club = id_club;
        this.nb_place=nb_place;
    }

    public int getId_evenement() {
        return id_event;
    }
  public int getNb_place() {
        return nb_place;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

    public int getId_club() {
        return id_club;
    }

    public void setId_evenement(int id_event) {
        this.id_event = id_event;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setId_club(int id_club) {
        this.id_club = id_club;
    }
public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }
    @Override
    public String toString() {
        return "Evenement{" + "id_event=" + id_event + ", nom=" + nom + ", description=" + description + ", date=" + date + ", place=" + place + ", id_club=" + id_club + ",nb_place=" + nb_place +'}';
    }

   
}
