/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.services;

import edu.gestudent.entities.Club;
import edu.gestudent.entities.Emprunt;
import edu.gestudent.entities.Livre;
import edu.gestudent.entities.MemberClub;
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
public class ServiceMemberClub {

    Connection cn2;
    Statement st;

    public ServiceMemberClub() {
        cn2 = DataBase.getInstance().getConnection();
    }

    public void ajouterMemberClub(MemberClub mc) {
        String requete2 = "INSERT INTO MemberClub (id_club,id) VALUES (?,?)";
        try {
            PreparedStatement pst = cn2.prepareStatement(requete2);

            pst.setInt(1, mc.getId_club());
            pst.setInt(2, mc.getId());
            pst.executeUpdate();
            System.out.println("un membre est affecté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /*public List<Emprunt> afficherlivreemprunte(int id) {
        ArrayList<Emprunt> livresemprunt = new ArrayList<>();
        String nom = "j", dateret = "";

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
                livresemprunt.add(emp);

            }
        } catch (SQLException ex) {
        }
        //livresemprunt.clear();
        return livresemprunt;

    }

    
    public boolean supprimeremprunt(Emprunt e, int id_emprunt) {

        String reqeute = "delete from emprunt  where (id_emprunt = ?) ;";
        try {
            PreparedStatement pst = cn2.prepareStatement(reqeute);
            pst.setInt(1, id_emprunt);
            if (pst.executeUpdate() != 0) {
                System.out.println("the book is back");
                incrementqte(e.getId_livre());
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

    public int getquantite(int name) {
        int q = 0;

        String requete4 = "select quantite from livres where id_livre=?;";
        PreparedStatement pst;
        try {
            pst = cn2.prepareStatement(requete4);
            pst.setInt(1, name);
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
     */
    public List<MemberClub> afficherClub(int id) {
        ArrayList<MemberClub> memberclub = new ArrayList<>();
        String nom = ""; int id_club=0;

        String requete4 = "select c.nom from club c, memberclub m,fos_user u where c.id_club=m.id_club and u.id=m.id and u.id=?;";
        PreparedStatement pst;
        try {
            pst = cn2.prepareStatement(requete4);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                nom = rs.getString(1);

                MemberClub emp = new MemberClub(id,id_club,nom);
                //emp.setNom(nom);
                //emp.setId_club(rs.getInt(2));
                memberclub.add(emp);

            }
        } catch (SQLException ex) {
        }
        //livresemprunt.clear();
        return memberclub;

    }

}
