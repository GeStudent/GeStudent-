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
public class inscriRest {
    private int id_inscri;
    private int id_user;
    private String duration;
    private double amount;

    
    public inscriRest() {
    }
    
    public inscriRest(int id_inscri, int id_user, String duration, double amount) {
        this.id_inscri = id_inscri;
        this.id_user = id_user;
        this.duration = duration;
        this.amount = amount;
    }

    public inscriRest(String duration, double amount) {
        this.duration = duration;
        this.amount = amount;
    }
    
     public inscriRest(int id_user, String duration, double amount) {
        this.id_user = id_user;
        this.duration = duration;
        this.amount = amount;
    }


    public int getId_inscri() {
        return id_inscri;
    }

    public int getId_user() {
        return id_user;
    }

    public String getDuration() {
        return duration;
    }

    public double getAmount() {
        return amount;
    }

    public void setId_inscri(int id_inscri) {
        this.id_inscri = id_inscri;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    

    @Override
    public int hashCode() {
        int hash = 5;
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
        final inscriRest other = (inscriRest) obj;
        if (this.id_inscri != other.id_inscri) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (Double.doubleToLongBits(this.amount) != Double.doubleToLongBits(other.amount)) {
            return false;
        }
        if (!Objects.equals(this.duration, other.duration)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "inscriRest{" + "id_inscri=" + id_inscri + ", id_user=" + id_user + ", duration=" + duration + ", amount=" + amount + '}';
    }

   
    
    
    
}
