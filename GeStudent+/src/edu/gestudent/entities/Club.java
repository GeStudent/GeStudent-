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
public class Club {
    private  int id_club;
    private String nom;
    private String date;
    private String email;
    private int numero;
    private String description;
    private int etat;
    private int id_president;

    public Club() {
    }

    public Club(int id_club, String nom, String date, String email, int numero, String description, int etat, int id_president) {
        this.id_club = id_club;
        this.nom = nom;
        this.date = date;
        this.email = email;
        this.numero = numero;
        this.description = description;
        this.etat = etat;
        this.id_president = id_president;
    }

    public Club(String nom, String date, String email, int numero, String description, int etat, int id_president) {
        this.nom = nom;
        this.date = date;
        this.email = email;
        this.numero = numero;
        this.description = description;
        this.etat = etat;
        this.id_president = id_president;
    }

  
  

    public int getId_club() {
        return id_club;
    }

    public String getNom() {
        return nom;
    }

    public String getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public int getNumero() {
        return numero;
    }

    public String getDescription() {
        return description;
    }

    public int getEtat() {
        return etat;
    }

    public int getId_president() {
        return id_president;
    }

    public void setId_club(int id_club) {
        this.id_club = id_club;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setId_president(int id_president) {
        this.id_president = id_president;
    }

    @Override
    public String toString() {
        return "Club{" + "id_club=" + id_club + ", nom=" + nom + ", date=" + date + ", email=" + email + ", numero=" + numero + ", description=" + description + ", etat=" + etat + ", id_president=" + id_president + '}';
    }
    
}
