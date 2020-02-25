/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.services;

import edu.gestudent.entities.Emprunt;
import edu.gestudent.entities.Livre;
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
 * @author ASUS
 */
public class EmpruntCrud {

    Connection cn2;
    Statement st;

    public EmpruntCrud() {
        cn2 = DataBase.getInstance().getConnection();
    }

    public void ajouterEmprunt(Emprunt e) {
        String requete2 = "INSERT INTO emprunt (date_emprunt,date_retour,id,id_livre) VALUES (now(),?,?,?)";
        try {
            PreparedStatement pst = cn2.prepareStatement(requete2);

            pst.setString(1, e.getDate_retour());
            pst.setInt(2, e.getId());
            pst.setInt(3, e.getId_livre());
            pst.executeUpdate();
            decrementqte(e.getId_livre());
            System.out.println("emprunt affecté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public boolean supprimeremprunt(Emprunt e, int id_emprunt) {

        String reqeute = "delete from emprunt  where (id_emprunt = ?);";
        try {
            PreparedStatement pst = cn2.prepareStatement(reqeute);
            System.out.println("id : " + e.getId_livre());
            pst.setInt(1, id_emprunt);
            if (pst.executeUpdate() != 0) {
                System.out.println("id : " + e.getId_livre());
                incrementqte(e.getId_livre());
                //System.out.println("the book is back");
                return true;

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return false;
    }

    public boolean UpdateE(int id_emprunt, String date_emprunt) throws SQLException {

        String reqeute = "UPDATE emprunt SET  date_emprunt= ?  where id_emprunt = ? ;";
        try {
            PreparedStatement pst = cn2.prepareStatement(reqeute);

            pst.setString(1, date_emprunt);
            if (pst.executeUpdate() != 0) {
                System.out.println("duration extended");
            } else {
                System.out.println("non");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return false;
    }

    public List<Emprunt> afficherEmprunt() {
        ArrayList<Emprunt> emp = new ArrayList<>();
        try {
            String requete3 = "SELECT *FROM emprunt";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                Emprunt e = new Emprunt();
                e.setId_emprunt(rs.getInt("id_emprunt"));
                System.out.println("idemprunt: " + e.getId_emprunt());
                e.setDate_emprunt(rs.getString("date_emprunt"));
                e.setDate_retour(rs.getString("datee_retour"));
                e.setId(rs.getInt("id"));
                e.setId_livre(rs.getInt("id_livre"));
                emp.add(e);
            }
        } catch (SQLException ex) {
        }
        return emp;
    }

    public int getquantite(int id_liv) {
        int q = 0;

        String requete4 = "select quantite from livres where id_livre=?;";
        PreparedStatement pst;
        try {
            pst = cn2.prepareStatement(requete4);
            pst.setInt(1, id_liv);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                q = rs.getInt(1);
            }
        } catch (SQLException ex) {
        }
        return q;
    }

    public void decrementqte(int id_l) {
        int q = getquantite(id_l);
        q--;
        String requete4 = "update livres SET quantite=? where id_livre=?;";
        PreparedStatement pst;
        try {
            pst = cn2.prepareStatement(requete4);
            pst.setInt(1, q);

            pst.setInt(2, id_l);
            pst.executeUpdate();

        } catch (SQLException ex) {
        }
    }

    public void incrementqte(int id_l) {
        int q = getquantite(id_l);
        System.out.println("quantite : " + q);
        q++;
        String requete4 = "update livres SET quantite=? where id_livre=?;";
        PreparedStatement pst;
        try {
            pst = cn2.prepareStatement(requete4);
            pst.setInt(1, q);

            pst.setInt(2, id_l);
            pst.executeUpdate();

        } catch (SQLException ex) {
        }
    }

    public List<Emprunt> afficherlivreemprunte(int id) {
        ArrayList<Emprunt> livresemprunt = new ArrayList<>();
        String nom = "", dateret = "";

        String requete4 = "select l.name,e.date_retour,e.id_emprunt from livres l join emprunt e on l.id_livre=e.id_livre where id=?;";
        PreparedStatement pst;
        try {
            pst = cn2.prepareStatement(requete4);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                nom = rs.getString(1);
                dateret = rs.getString(2);

                Emprunt emp = new Emprunt(dateret, nom);
                emp.setId_emprunt(rs.getInt(3));
                System.out.println(rs.getInt(3));
                livresemprunt.add(emp);

            }
        } catch (SQLException ex) {
        }
        //livresemprunt.clear();
        return livresemprunt;

    }

    public int getidemp(int id, int idl) {
        int q = 0;

        String requete4 = "select id_emprunt from emprunt where id=? and id_livre=? ;";
        PreparedStatement pst;
        try {
            pst = cn2.prepareStatement(requete4);
            pst.setInt(1, id);
            pst.setInt(2, idl);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                q = rs.getInt(1);
            }
        } catch (SQLException ex) {
        }
        return q;
    }

    public List<Emprunt> afficherempruntadmin() {
        ArrayList<Emprunt> livresemprunt = new ArrayList<>();

        String requete4 = "SELECT u.id,u.firstname,u.lastname,l.id_livre,l.name,e.id_emprunt,e.date_retour from user u, livres l , emprunt e where u.id=e.id and l.id_livre=e.id_livre;";
        PreparedStatement pst;
        try {
            pst = cn2.prepareStatement(requete4);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String firstname = rs.getString(2);
                String lastname = rs.getString(3);
                int id_livre = rs.getInt(4);
                String name = rs.getString(5);
                int id_emprunt = rs.getInt(6);
                String date_retour = rs.getString(7);

                Emprunt emp = new Emprunt(firstname,lastname,name,date_retour,id,id_livre,id_emprunt);

                livresemprunt.add(emp);

            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        //livresemprunt.clear();
        return livresemprunt;

    }
}
