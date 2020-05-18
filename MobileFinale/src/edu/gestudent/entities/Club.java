/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.entities;


/**
 *
 * @author Ayadi
 */
public class Club {
     private  int id;
    private String nom;
    private String date;
    private String email;
    private String image;
    private int tel;
    private String description;
    private int etat;
    private int id_president;
    private int nombreplace;

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }




    public int getNombreplace() {
        return nombreplace;
    }

    public void setNombreplace(int nombreplace) {
        this.nombreplace = nombreplace;
    }
    

    public Club() {
    }

    public Club(int id, String nom, String date, String email, int tel, String description, int etat, int id_president) {
        this.id =id ;
        this.nom = nom;
        this.date = date;
        this.email = email;
        this.tel = tel;
        this.description = description;
        this.etat = etat;
        this.id_president = id_president;
    }

    public Club(String nom, String date, String email, int tel, String description, int etat, int id_president) {
        this.nom = nom;
        this.date = date;
        this.email = email;
        this.tel = tel;
        this.description = description;
        this.etat = etat;
        this.id_president = id_president;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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


    public String getDescription() {
        return description;
    }

    public int getEtat() {
        return etat;
    }

    public int getId_president() {
        return id_president;
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
        return "Club{" + "id=" + id + ", nom=" + nom + ", date=" + date + ", email=" + email + ", tel=" + tel + ", description=" + description + ", etat=" + etat + ", id_president=" + id_president + ", nombreplace=" + nombreplace + '}';
    }

 
}
