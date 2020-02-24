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
public class Student extends user {

    public Student(String lastname, String firstname, String email, String birthDay, int phone, String pays, String adress, String gender) {
        super(lastname, firstname, email, birthDay, phone, pays, adress, gender);

    }

    public Student(int id, String lastname, String firstname, String email, String birthDay, int phone, String pays, String adress, String gender) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.birthDay = birthDay;
        this.phone = phone;
        this.adress = adress;
        this.gender = gender;
        this.pays=pays;

    }

    public String getUsername() {
        return username;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getImage() {
        return image;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public int getPhone() {
        return phone;
    }

    public String getPays() {
        return pays;
    }

    public String getAdress() {
        return adress;
    }

    public String getGender() {
        return gender;
    }



    @Override
    public String toString() {
        return super.toString();
    }

}
