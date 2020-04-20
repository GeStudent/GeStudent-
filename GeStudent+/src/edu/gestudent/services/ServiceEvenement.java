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
import java.sql.Date;
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
            String requeteInsert = "INSERT INTO Evenement (nom,photo,description,nombreplaces,datedebut,etat,decision) VALUES (?,?,?,?,?,?;?)";

            PreparedStatement pst = con.prepareStatement(requeteInsert);
            pst.setString(1, e.getNom());
            pst.setString(1, e.getPhoto());

            pst.setString(2, e.getDescription());
            pst.setInt(2, e.getNombreplaces());
            pst.setDate(3, (Date) e.getDatedebut());
            pst.setInt(4, e.getEtat());
            pst.setString(5, e.getDecision());

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
                int id = rs.getInt("id");
                int etat = rs.getInt("etat");

                String nom = rs.getString("nom");

                String description = rs.getString("description");

                Date datedebut = rs.getDate("datedebut");

                int nombreplaces = rs.getInt("nombreplaces");
                String photo = rs.getString("photo");
                String decision = rs.getString("decision");

                Evenement e = new Evenement(id, nom, photo, description, nombreplaces, datedebut, etat, decision);
                arr.add(e);
//ahouka yaffchi
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arr;
    }

    public boolean Update(String nom, String datedebut) {

        String requete = "UPDATE Evenement SET  datedebut= ? where nom = ? ;";
        try {
            PreparedStatement pst = con.prepareStatement(requete);
//            pst.setInt(1, m.getId());

            pst.setString(1, nom);
            pst.setString(2, datedebut);
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

        String requete4 = "select nombreplaces from evenement where id=?;";
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
