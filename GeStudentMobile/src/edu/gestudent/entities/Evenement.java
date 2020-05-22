/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.entities;

import com.codename1.ui.Form;



/**
 *
 * @author user
 */
public class Evenement {

    private int id;
    private String nom;
    private String description;
    private String photo;
        private Form f;
            private String qrCodeEvenement;


    private String datedebut;
    private int nombreplaces;
    private int etat;
    private String decision;
    private int idmanager;
    

    public Evenement() {
    }

   

    public Evenement(int id,String nom, String photo, String description, int nombreplaces, String datedebut, int etat, String decision,int idmanager,String qrCodeEvenement) {
        this.id=id;
        this.nom = nom;
        this.photo = photo;
        this.description = description;
        this.nombreplaces = nombreplaces;
        this.datedebut = datedebut;
        this.etat = etat;
        this.decision = decision;
        this.idmanager=idmanager;
       this.qrCodeEvenement=qrCodeEvenement;



    }

   
 public Evenement(String nom, String photo, String description, int nombreplaces, String datedebut, int etat, String decision,int idmanager,String qrCodeEvenement) {

        this.nom = nom;
        this.photo = photo;
        this.description = description;
        this.nombreplaces = nombreplaces;
        this.datedebut = datedebut;
        this.etat = etat;
        this.decision = decision;
        this.idmanager=idmanager;
        this.qrCodeEvenement = qrCodeEvenement;


    }
    public int getId() {
        return id;
    }
     public int getIdmanager() {
        return idmanager;
    }

    public String getQrCodeEvenement() {
        return qrCodeEvenement;
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

    public String getDatedebut() {
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
     public void setIdmanager(int idmanager) {
        this.idmanager = idmanager;
    }

    public void setQrCodeEvenement(String qrCodeEvenement) {
        this.qrCodeEvenement = qrCodeEvenement;
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

    public void setDatedebut(String datedebut) {
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
  public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    @Override
    public String toString() {

                                                                                                                                                                                                                                        
   return "Evenement{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", nombreplaces=" + nombreplaces + ", datedebut=" + datedebut + ", etat=" + etat + ",decision=" + decision +",idmanager="+ idmanager +",qrCodeEvenement="+ qrCodeEvenement + '}';
    }

}
