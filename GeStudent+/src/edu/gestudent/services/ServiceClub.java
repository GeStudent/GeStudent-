/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.services;

import edu.gestudent.entities.Club;
import edu.gestudent.entities.Evenement;
import edu.gestudent.utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ServiceClub {

    private Connection con;
    private Statement ste;

    public ServiceClub() {
        con = DataBase.getInstance().getConnection();

    }

    public void ajouterClub(Club c) {

        try {

            PreparedStatement pre = con.prepareStatement("INSERT INTO `club` (nom,date,email,numero,description,etat,id_president) values(?,?,?,?,?,?,?) ");
            pre.setString(1, c.getNom());
            pre.setString(2, c.getDate());
            pre.setString(3, c.getEmail());
            pre.setInt(4, c.getNumero());
            pre.setString(5, c.getDescription());
            pre.setInt(6, c.getEtat());
            pre.setInt(7, c.getId_president());
            pre.executeUpdate();
            System.out.println("Club ajoute");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    public boolean supprimer(Club c)  {


        String reqeute = "delete  from Club  where (nom = ?) ";
        try {
            PreparedStatement pst = con.prepareStatement(reqeute);
            pst.setString(1, c.getNom());
            // pst.setString(2, m.setDescription(reqeute));
            if (pst.executeUpdate() != 0) {
                System.out.println("Club deleted");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;
//To change body of generated methods, choose Tools | Templates.
    }

    public boolean Update(int etat, String nom) {

        String requete = "UPDATE Club SET  etat= ? where nom = ? ;";
        try {
            PreparedStatement pst = con.prepareStatement(requete);
//            pst.setInt(1, m.getId());

            pst.setInt(1, etat);
            pst.setString(2, nom);
            if (pst.executeUpdate() != 0) {
                System.out.println("Club Updated");
            } else {
                System.out.println("non");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return false;
    }

    /**
     *
     * @return @throws SQLException
     */

    
    public List<Club> readAll()  {

        List<Club> arr = new ArrayList<>();
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select * from Club");
            while (rs.next()) {

                //int id=rs.getInt(1);
                int id_club = rs.getInt("id");
                String nom = rs.getString("nom");

                String date = rs.getString("date");

                String email = rs.getString("email");

                int numero = rs.getInt("tel");

                String description = rs.getString("description");

                int etat = rs.getInt("etat");

                int id_president = rs.getInt("id_president");

                Club c = new Club(id_club, nom, date, email, numero, description, etat, id_president);
                arr.add(c);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arr;
    }

    public List<Club> rechercher(String nom1) throws SQLException {
        List<Club> arr = new ArrayList<>();
        // ste=con.createStatement();
        // ResultSet rs=ste.executeQuery("select * from evenement");
        PreparedStatement pre = con.prepareStatement("Select * from Club  WHERE nom=? ");
        pre.setString(1, nom1);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {

            //int id=rs.getInt(1);
            int id_club = rs.getInt("id_club");
            String nom = rs.getString("nom");
            String date = rs.getString("date");
            String email = rs.getString("email");
            int numero = rs.getInt("numero");
            String description = rs.getString("description");
            int etat = rs.getInt("etat");
            int id_president = rs.getInt("id_president");

            Club c = new Club(id_club, nom, date, email, numero, description, etat, id_president);
            arr.add(c);
        }
        return arr;
    }
     public String getImageClub(int id) {
        String q = "";

        String requete4 = "select image from club where id=?;";
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(requete4);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                q = rs.getString(1);
            }
        } catch (SQLException ex) {
        }
        return q;
    }

    /*String tri = " ORDER BY nbreplace " ;
    String ordre = " ASC" ;
    public void ordreASC () {
        ordre = " ASC" ; 
    }
     public void ordreDESC () {
        ordre = " DESC" ; 
    }
    
    public void triByPrix() { 
        tri = " ORDER BY prix ";
        }
    public void triByNbPlace() { 
        tri = " ORDER BY nbreplace ";
        }
    public void triByDateDebut() { 
        tri = " ORDER BY dateD ";
        }*/
    public void triByDate() {
        String tri = " ORDER BY date ";
    }

}
