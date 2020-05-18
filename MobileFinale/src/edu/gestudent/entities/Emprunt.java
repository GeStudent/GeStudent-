/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.entities;

/**
 *
 * @author ASUS
 */
public class Emprunt {

    private int id_emprunt;
    private String d_emprunt;
    private String d_retour;
    private int id_user;
    private int id_livre;
    private String bookName;
    private String firstname;
    private String lastname;
    private int phone;

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

   
    private String image;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    private String name;
    private int nombre;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public Emprunt() {
    }

    public int getId_emprunt() {
        return id_emprunt;
    }

    public void setId_emprunt(int id_emprunt) {
        this.id_emprunt = id_emprunt;
    }

    public String getD_emprunt() {
        return d_emprunt;
    }

    public void setD_emprunt(String d_emprunt) {
        this.d_emprunt = d_emprunt;
    }

    public String getD_retour() {
        return d_retour;
    }

    public void setD_retour(String d_retour) {
        this.d_retour = d_retour;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_livre() {
        return id_livre;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "Emprunt{" + "id_emprunt=" + id_emprunt + ", d_emprunt=" + d_emprunt + ", d_retour=" + d_retour + ", id_user=" + id_user + ", id_livre=" + id_livre + '}';
    }

}
