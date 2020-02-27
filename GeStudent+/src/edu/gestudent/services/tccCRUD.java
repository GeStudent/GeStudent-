/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.services;

import edu.gestudent.entities.classe;
import edu.gestudent.entities.cours;
import edu.gestudent.entities.tcc;
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
 * @author CHIKHAOUI NOUHA
 */
public class tccCRUD {

    private Connection con;
    private Statement ste;

    public tccCRUD() {
        con = DataBase.getInstance().getConnection();

    }

    public void ajouter(tcc t) throws SQLException {
        //zaama naamel jointure houni ? wala bch yajouti kol chay iwali ?
        PreparedStatement pre = con.prepareStatement(" INSERT INTO `tcc` ( `idclass`, `idteacher`,`idcours`) VALUES ( ?, ?, ?); ");
        pre.setInt(1, t.getIdclass());
        pre.setInt(2, t.getidteacher());
        pre.setInt(3, t.getIdcours());

        pre.executeUpdate();
    }

    /**
     *
     * @return @throws SQLException
     */
    public List<tcc> rechercheclass(int idclass) throws SQLException {
        {
            List<tcc> arr = new ArrayList<>();
            PreparedStatement pre = con.prepareStatement("SELECT C.name,u.id ,c.duration, Cl.nameC, u.firstname FROM cours C, class cl, user u, tcc t WHERE t.idcours = c.idcour AND t.idteacher = u.id and cl.idclass=t.idclass and t.idclass=? ");
            pre.setInt(1, idclass);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int idteacher=rs.getInt("id");
                String name = rs.getString("name");
                String nameC = rs.getString("nameC");

                int duration = rs.getInt(2);
                String firstname = rs.getString("firstname");

                tcc t = new tcc(name, nameC, duration, firstname);
                t.setIdteacher(idteacher);
                arr.add(t);
            }
            return arr;
        }
    }

    public List<tcc> recherchecour(int idcours) throws SQLException {
        {
            List<tcc> arr = new ArrayList<>();

            PreparedStatement pre = con.prepareStatement("SELECT C.name,c.duration, Cl.nameC, u.firstname FROM cours C, class cl, user u, tcc t WHERE t.idcours = c.idcour AND t.idteacher = u.id and cl.idclass=t.idclass and t.idcours= ? ");

            pre.setInt(1, idcours);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {

                String name = rs.getString("name");

                String nameC = rs.getString("nameC");

                int duration = rs.getInt(2);
                String firstname = rs.getString("firstname");

                tcc r = new tcc(name, nameC, duration, firstname);
                arr.add(r);
            }
            return arr;
        }

    }

    public List<tcc> rechercheprof(int idteacher) {

        List<tcc> arr = new ArrayList<>();

        try {

            PreparedStatement pre = con.prepareStatement("SELECT  C.idcour,u.id,cl.idclass, C.name , C.duration, Cl.nameC, u.firstname FROM cours C, class cl, user u, tcc t WHERE t.idcours = c.idcour AND t.idteacher = u.id and cl.idclass=t.idclass and u.id= ? ");

            pre.setInt(1, idteacher);

            ResultSet rs = pre.executeQuery();

            while (rs.next()) {

                String name = rs.getString("name");
                String nameC = rs.getString("nameC");

                int duration = rs.getInt(2);
                String firstname = rs.getString("firstname");

                tcc r = new tcc(name, nameC, duration, firstname);
                r.setIdclass(rs.getInt("idclass"));
                r.setIdteacher(rs.getInt("id"));
                r.setIdcours(rs.getInt("idcour"));
                
                arr.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(tccCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arr;

    }

    public List<classe> Listclassteacher(int idteacher) {

        List<classe> arr = new ArrayList<>();

        try {

            PreparedStatement pre = con.prepareStatement("SELECT C.name , C.duration, Cl.nameC, cl.idclass , u.firstname FROM cours C, class cl, user u, tcc t WHERE t.idcours = c.idcour AND t.idteacher = u.id and cl.idclass=t.idclass and u.id= ? ");

            pre.setInt(1, idteacher);

            ResultSet rs = pre.executeQuery();

            while (rs.next()) {

                String nameC = rs.getString("nameC");
                int idclass = rs.getInt("idclass");

                classe c = new classe(nameC, idclass);
                arr.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(tccCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arr;

    }

    public boolean supprimertcc(int idcours, int idclass, int idteacher) throws SQLException {

        PreparedStatement pre = con.prepareStatement("Delete from tcc where idcours=? and  idclass=? and idteacher=? ");
        try {
            pre.setInt(1, idcours);
            pre.setInt(2, idclass);
            pre.setInt(3, idteacher);
            if (pre.executeUpdate() != 0) {
                System.out.println("Deleted");

                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("id not found!!!");
        return false;

    }

}
