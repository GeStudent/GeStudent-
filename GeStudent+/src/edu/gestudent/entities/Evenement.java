/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.entities;

import java.util.Date;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author user
 */
public class Evenement {

    private int id;
    private String nom;
    private String description;
    private String photo;

    private Date datedebut;
    private int nombreplaces;
    private int etat;
    private String decision;
    

    public Evenement() {
    }

    public Evenement(int id, String nom, String photo, String description, int nombreplaces, Date datedebut, int etat, String decision) {
        this.id = id;
        this.nom = nom;
        this.photo = photo;
        this.description = description;

        this.nombreplaces = nombreplaces;
        this.datedebut = datedebut;

        this.etat = etat;
        this.decision = decision;
    }

    public Evenement(String nom, String photo, String description, int nombreplaces, Date datedebut, int etat, String decision) {

        this.nom = nom;
        this.photo = photo;
        this.description = description;
        this.nombreplaces = nombreplaces;
        this.datedebut = datedebut;
        this.etat = etat;
        this.decision = decision;

    }

    public int getId() {
        return id;
    }

    public int getNombreplaces() {
        return nombreplaces;
    }

   
     public String getPhoto() {
        return photo;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public int getEtat() {
        return etat;
    }

    public String getDecision() {
        return decision;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public void setNombreplaces(int nombreplaces) {
        this.nombreplaces = nombreplaces;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    @Override
    public String toString() {

    
   return "Evenement{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", nombreplaces=" + nombreplaces + ", datedebut=" + datedebut + ", etat=" + etat + ",decision=" + decision + '}';
    }

}




