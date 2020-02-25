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
public class ServiceEvenement {

    private Connection con;
    private Statement ste;

    public ServiceEvenement() {
        con = DataBase.getInstance().getConnection();

    }

    public void ajouter(Evenement e) {
        try {

            ste = con.createStatement();
            String requeteInsert = "INSERT INTO Evenement (nom,description,date,place,id_club,nb_place) VALUES (?,?,?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(requeteInsert);
            pst.setString(1, e.getNom());
            pst.setString(2, e.getDescription());
            pst.setString(3, e.getDate());
            pst.setString(4, e.getPlace());
            pst.setInt(5, e.getId_club());
            pst.setInt(6, e.getNb_place());

//  String requeteInsert = "INSERT INTO evenement (`id_event`, `nom`, `description`, `date`,`place`,`id_club`,`nb_place`) VALUES (NULL, '" + e.getNom() + "', '" + e.getDescription() + "', '" + e.getDate() + "','" + e.getPlace() + "','" + e.getId_club() + "','" + e.getNb_place() + "'  ;";
            //            String requeteInsert = "INSERT INTO `evenement` (`id_event`, `nom`, `description`, `date`,`place`,`id_club`/*,`nb_place`*/) VALUES (NULL, '" + e.getNom() + "', '" + e.getDescription() + "', '" + e.getDate() + "','" + e.getPlace() + "','" + e.getId_club() + "' ;";
//String requete2 = "INSERT INTO MemberClub (id_club,id) VALUES (?,?)";
            /*  if (ste.executeUpdate(requeteInsert) != 0) {
                System.out.println("salut nader nooob");
            }

             */ if (pst.executeUpdate() != 0) {
                System.out.println("Evenement deleted");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean update(Evenement e) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean supprimer(Evenement e) throws SQLException {

        String reqeute = "delete from Evenement  where (nom = ?) ;";
        try {
            PreparedStatement pst = con.prepareStatement(reqeute);
            pst.setString(1, e.getNom());
            // pst.setString(2, m.setDescription(reqeute));
            if (pst.executeUpdate() != 0) {
                System.out.println("Evenement deleted");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;
//To change body of generated methods, choose Tools | Templates.
    }

    public List<Evenement> readAll() {

        List<Evenement> arr = new ArrayList<>();
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select * from Evenement");
            while (rs.next()) {
                //int id=rs.getInt(1);
                int id_event = rs.getInt("id_event");

                String nom = rs.getString("nom");

                String description = rs.getString("description");

                String date = rs.getString("date");

                String place = rs.getString("place");
                int id_club = rs.getInt("id_club");
                int nb_place = rs.getInt("nb_place");

                Evenement e = new Evenement(id_event, nom, description, date, place, id_club, nb_place);
                arr.add(e);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public boolean Update(String nom, String date) {

        String requete = "UPDATE Evenement SET  date= ? where nom = ? ;";
        try {
            PreparedStatement pst = con.prepareStatement(requete);
//            pst.setInt(1, m.getId());

            pst.setString(1, nom);
            pst.setString(2, date);
            if (pst.executeUpdate() != 0) {
                System.out.println("Evenement Updated");
            } else {
                System.out.println("non");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return false;
    }

   
       public int getnb_place(int name) {
        int nb = 0;

        String requete4 = "select nb_place from evenement where id_evenement=?;";
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(requete4);
            pst.setInt(1, name);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                nb = rs.getInt(1);
            }
        } catch (SQLException ex) {
        }
        return nb;
    }

 /* public void decrementqte(int id_event) {
        int nb = getnb_place(id_event);
        nb--;
        String requete4 = "update evenement SET nb_place=? where id_event=?;";
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(requete4);
            pst.setInt(1, nb);

            pst.setInt(2, id_event);
            pst.executeUpdate();

        } catch (SQLException ex) {
        }
    }
    */
}
