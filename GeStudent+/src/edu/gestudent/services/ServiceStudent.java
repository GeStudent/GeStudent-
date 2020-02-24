/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.services;

import edu.gestudent.entities.Student;
import edu.gestudent.entities.user;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ayadi
 */
public class ServiceStudent extends ServicesUsers {

    public ServiceStudent() {
        super();
    }

    public void ajouterStudent(Student s) {

        PreparedStatement pre;
        try {
            super.ajouter(s);
            pre = con.prepareStatement("update user set roles='student' where idcode= ?");
            pre.setString(1, super.getQRcode(s.getEmail()));
            pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Student> readAllStudentRegistrated() {

        List<Student> lu = new ArrayList<>();
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select id,firstname,lastname,email,birthDay,phone,pays,adress,gender from user where roles='student' and enabled=1 ");
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String birthDay = rs.getString("birthDay");
                int phone = rs.getInt("phone");
                String pays = rs.getString("pays");
                String adress = rs.getString("adress");
                String gender = rs.getString("gender");
                Student u = new Student(id, lastname, firstname, email, birthDay, phone, pays, adress, gender);
                lu.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lu;
    }

    public List<Student> readAllStudentNotRegistrated() {

        List<Student> lu = new ArrayList<>();
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select id,firstname,lastname,email,birthDay,phone,pays,adress,gender from user where roles='student' and enabled=0 ");
            while (rs.next()) {
                int id = rs.getInt("id");

                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String birthDay = rs.getString("birthDay");
                int phone = rs.getInt("phone");
                String pays = rs.getString("pays");
                String adress = rs.getString("adress");
                String gender = rs.getString("gender");
                Student u = new Student(id, lastname, firstname, email, birthDay, phone, pays, adress, gender);
                lu.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lu;
    }
}
