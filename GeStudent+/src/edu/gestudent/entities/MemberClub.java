/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.entities;

import java.util.logging.Logger;

/**
 *
 * @author user ;           
 */
public class MemberClub {//nom teba3 lclub

    private int id_club;
    private int id;

    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

   
    
    

    public MemberClub(int id, int id_club) {
        this.id_club = id_club;
        this.id = id;
    }
        public MemberClub(int id, int id_club,String nom) {
        this.id_club = id_club;
        this.id = id;
        this.nom=nom;
    }

    public MemberClub() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public MemberClub(String nom) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_club() {
        return id_club;
    }

    public int getId() {
        return id;
    }





    public void setId_club(int id_club) {
        this.id_club = id_club;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.id_club;
        hash = 71 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        return "MemberClub{" + "id_club=" + id_club + ", id=" + id + '}';
    }

}
