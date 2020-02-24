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
public class tsa {
    int idbeh;
    int idstu;
    int idtea;

    public tsa() {
    }

    public tsa(int idbeh, int idstu, int idtea) {
        this.idbeh = idbeh;
        this.idstu = idstu;
        this.idtea = idtea;
    }

    public int getIdbeh() {
        return idbeh;
    }

    public void setIdbeh(int idbeh) {
        this.idbeh = idbeh;
    }

    public int getIdstu() {
        return idstu;
    }

    public void setIdstu(int idstu) {
        this.idstu = idstu;
    }

    public int getIdtea() {
        return idtea;
    }

    public void setIdtea(int idtea) {
        this.idtea = idtea;
    }
    
    
    
}
