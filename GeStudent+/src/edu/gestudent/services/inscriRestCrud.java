/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.services;

import edu.gestudent.entities.Menu;
import edu.gestudent.entities.inscriRest;
import edu.gestudent.utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class inscriRestCrud {

    Connection con;
    Statement st;

    public inscriRestCrud() {
        con = DataBase.getInstance().getConnection();
    }

    public int InscriInterface(int iduser) {
        int count = 0;
        try {
            String requete = "SELECT * FROM restaurant_subscription WHERE id_user=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, iduser);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("id_user");
            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
        return count;
    }

    public void ajouterinscri(inscriRest m) {
        String reqeute = "insert into restaurant_subscription (	id_user,duration,amount) values(?,?,?);";
        try {
            PreparedStatement pst = con.prepareStatement(reqeute);
            pst.setInt(1, m.getId_user());
            pst.setString(2, m.getDuration());
            pst.setDouble(3, m.getAmount());
            pst.executeUpdate();

            System.out.println("inscri added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public ObservableList<inscriRest> afficherinscri() {
        ObservableList<inscriRest> lsp = FXCollections.observableArrayList();
        String req = "Select * from restaurant_subscription";
        try {
            PreparedStatement st2 = con.prepareStatement(req);
            ResultSet rs = st2.executeQuery();
            while (rs.next()) {
                inscriRest m = new inscriRest();
                m.setId_user(rs.getInt("id_user"));
                m.setDuration(rs.getString("duration"));
                m.setAmount(rs.getDouble("amount"));
                lsp.add(m);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lsp;
    }

}
