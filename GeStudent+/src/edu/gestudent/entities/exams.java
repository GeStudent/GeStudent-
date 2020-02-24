/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.entities;


/**
 *
 * @author Asus
 */
public class exams {
    int idexa;
    String nomex;
    String dateex;
    String duree;

    public exams() {
    }

    public exams(int idexa, String nomex, String dateex, String duree) {
        this.idexa = idexa;
        this.nomex = nomex;
        this.dateex = dateex;
        this.duree = duree;
    }

    public exams(String nomex, String dateex, String duree) {
        this.nomex = nomex;
        this.dateex = dateex;
        this.duree = duree;
    }


  

    public int getIdexa() {
        return idexa;
    }

    public void setIdexa(int idexa) {
        this.idexa = idexa;
    }

    public String getNomex() {
        return nomex;
    }

    public void setNomex(String nomex) {
        this.nomex = nomex;
    }

    public String getDateex() {
        return dateex;
    }

    public void setDateex(String dateex) {
        this.dateex = dateex;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }


//    public int getDuree() {
//        return duree;
//    }
//
//    public void setDuree(int duree) {
//        this.duree = duree;
//    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.idexa;
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
        final exams other = (exams) obj;
        if (this.idexa != other.idexa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "exams{" + "idexa=" + idexa + ", nomex=" + nomex + ", dateex=" + dateex + ", duree=" + duree + '}';
    }
    


    
    
    
}
