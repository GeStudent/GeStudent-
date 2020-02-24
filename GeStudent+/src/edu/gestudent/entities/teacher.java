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
public class teacher extends user {



    public teacher(String lastname, String firstname, String email, String roles, String birthDay, int phone, String pays, String adress, String gender) {
        super(lastname, firstname, email, roles, birthDay, phone, pays, adress, gender);
    }

    public teacher(String lastname, String firstname, String email, String birthDay, int phone, String pays, String adress, String gender ) {
        super(lastname, firstname, email, birthDay, phone, pays, adress, gender);
    }

    public teacher(int id, String lastname, String firstname, String email, String birthDay, int phone, String pays, String adress, String gender ) {
        super(lastname, firstname, email, birthDay, phone, pays, adress, gender);
        this.id = id;

    }

   

    @Override
    public String toString() {
        return super.toString() ;
    }

}
