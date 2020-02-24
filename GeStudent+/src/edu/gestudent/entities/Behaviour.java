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
public class Behaviour {

    int idbeh;
    int attendance;
    int award;
    String nombeh;
    
    public Behaviour() {
    }

    public Behaviour(int award, String nombeh) {
        this.award = award;
        this.nombeh = nombeh;
    }

    public Behaviour(int idbeh, int award, String nombeh) {
        this.idbeh = idbeh;
        this.award = award;
        this.nombeh = nombeh;
    }
    
    public Behaviour(int idbeh, int attendance, int award) {
        this.idbeh = idbeh;
        this.attendance = attendance;
        this.award = award;
    }

    public Behaviour(int attendance, int award) {
        this.attendance = attendance;
        this.award = award;
    }

    public String getNombeh() {
        return nombeh;
    }

    public void setNombeh(String nombeh) {
        this.nombeh = nombeh;
    }

    public int getIdbeh() {
        return idbeh;
    }

    public void setIdbeh(int idbeh) {
        this.idbeh = idbeh;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public int getAward() {
        return award;
    }

    public void setAward(int award) {
        this.award = award;
    }

    @Override
    public String toString() {
        return "Behaviour{" + "idbeh=" + idbeh + ", attendance=" + attendance + ", award=" + award + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.idbeh;
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
        final Behaviour other = (Behaviour) obj;
        if (this.idbeh != other.idbeh) {
            return false;
        }
        return true;
    }

 


}
