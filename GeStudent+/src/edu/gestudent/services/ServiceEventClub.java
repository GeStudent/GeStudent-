/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.services;

import edu.gestudent.entities.Club;
import edu.gestudent.entities.Emprunt;
import edu.gestudent.entities.EventClient;
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
public class ServiceEventClub {

    Connection cn2;
    Statement st;

    public ServiceEventClub() {
        cn2 = DataBase.getInstance().getConnection();
    }

    public void ajouterEventClub(EventClient ec) {
        String requete2 = "INSERT INTO EventClient (id_event_client,id_event,date_reservation) VALUES (?,?,now())";
        try {
            PreparedStatement pst = cn2.prepareStatement(requete2);

            pst.setInt(1, ec.getId_event_client());
            pst.setInt(2, ec.getId_event());
            pst.executeUpdate();
            decrementNbrplace(ec.getId_event());
            System.out.println("un evenement est reserve");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public boolean supprimerEventClub(EventClient ec) {

        String reqeute = "delete from EventClient  where  id_event_client= ?  and  id_event=?;";
        try {
            PreparedStatement pst = cn2.prepareStatement(reqeute);
            pst.setInt(1, ec.getId_event_client());
            pst.setInt(2, ec.getId_event());
            incrementNbrplace(ec.getId_event());
            if (pst.executeUpdate() != 0) {
                System.out.println("the event is back");
                //incrementqte(ec.getId_event());
                return true;

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return false;
    }

    public boolean UpdateE(int id, String date_reservation) throws SQLException {

        String reqeute = "UPDATE EventClub SET  date_reservation= ?  where id_event_client = ? ;";
        try {
            PreparedStatement pst = cn2.prepareStatement(reqeute);

            pst.setString(1, date_reservation);
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

    public List<EventClient> afficherEventClient() {
        ArrayList<EventClient> emp = new ArrayList<>();
        try {
            String requete3 = "SELECT *FROM EventClient";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                EventClient ec = new EventClient();
                ec.setId_event_client(rs.getInt("id_event_client"));
                ec.setDate_reservation(rs.getString("date_reservation"));
                ec.setId_event(rs.getInt("id_event"));
                emp.add(ec);
            }
        } catch (SQLException ex) {
        }
        return emp;
    }

    public int getnbrplace(int idevent) {
        int nb = 0;

        String requete4 = "select nb_place from evenement where id_event=?;";
        PreparedStatement pst;
        try {
            pst = cn2.prepareStatement(requete4);
            pst.setInt(1, idevent);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                nb = rs.getInt(1);
            }
        } catch (SQLException ex) {
        }
        return nb;
    }

    public int getidEvent(String name) {
        int q = 0;

        String requete4 = "select id_event from evenement where nom=?;";
        PreparedStatement pst;
        try {
            pst = cn2.prepareStatement(requete4);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                q = rs.getInt(1);
            }
        } catch (SQLException ex) {
        }
        return q;
    }

    public void decrementNbrplace(int idevent) {
        int q = getnbrplace(idevent);
        q--;
        String requete4 = "update evenement SET nb_place=? where id_event=?;";
        PreparedStatement pst;
        try {
            pst = cn2.prepareStatement(requete4);
            pst.setInt(1, q);

            pst.setInt(2, idevent);
            pst.executeUpdate();

        } catch (SQLException ex) {
        }
    }

    public void incrementNbrplace(int id_event) {
        int q = getnbrplace(id_event);
        q++;
        String requete4 = "update evenement SET nb_place=? where id_event=?;";
        PreparedStatement pst;
        try {
            pst = cn2.prepareStatement(requete4);
            pst.setInt(1, q);
            pst.setInt(2, id_event);
            pst.executeUpdate();

        } catch (SQLException ex) {
        }
    }

    public ArrayList<EventClient> afficherEvenementReserve(int id) {
        ArrayList<EventClient> evenementreserve = new ArrayList<>();
        String nom = "ha", dateret = "";

        String requete4 = "select e.nom,ec.date_reservation,ec.id_event from evenement e , EventClient ec , user u where u.id=ec.id_event_client and ec.id_event=e.id_event and u.id=?;";
        PreparedStatement pst;
        try {
            pst = cn2.prepareStatement(requete4);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                nom = rs.getString(1);
                dateret = rs.getString(2);
                int idevent = rs.getInt("id_event");
                EventClient emp = new EventClient(idevent, id, nom, dateret);
                evenementreserve.add(emp);

            }
        } catch (SQLException ex) {
        }
        //livresemprunt.clear();
        return evenementreserve;

    }

}
