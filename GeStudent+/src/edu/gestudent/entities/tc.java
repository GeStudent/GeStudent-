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
public class tc {
        int idtea;
        int idclas;
        int idexa;

    public tc() {
    }

    public tc(int idtea, int idclas,int idexa) {
        this.idtea = idtea;
        this.idclas = idclas;
        this.idexa=idexa;
    }

    public int getIdtea() {
        return idtea;
    }

    public void setIdtea(int idtea) {
        this.idtea = idtea;
    }

    public int getIdclas() {
        return idclas;
    }

    public void setIdclas(int idclas) {
        this.idclas = idclas;
    }

 

    public int getIdexa() {
        return idexa;
    }

    public void setIdexa(int idexa) {
        this.idexa = idexa;
    }
        
}
