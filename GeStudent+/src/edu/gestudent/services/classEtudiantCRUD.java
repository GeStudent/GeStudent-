/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.services;

import edu.gestudent.entities.classEtudiant;
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
public class classEtudiantCRUD {

    private Connection con;
    private Statement ste;

    public classEtudiantCRUD() {
        con = DataBase.getInstance().getConnection();

    }

    public void ajouter(classEtudiant ce) {
        try {
            PreparedStatement pre = con.prepareStatement(" INSERT INTO `classEtudiant` ( `idclass`, `idetudiant`) VALUES ( ?, ?); ");
            pre.setInt(1, ce.getidclass());
            pre.setInt(2, ce.getidetudiant());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(classEtudiantCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<classEtudiant> rechercheclassetudiant(int idclass) {
        {
            List<classEtudiant> arr = new ArrayList<>();

            try {
                PreparedStatement pre = con.prepareStatement("SELECT  u.id,Cl.idclass,Cl.nameC, u.firstname  FROM  class cl ,user u, classEtudiant ce WHERE ce.idclass = cl.idclass AND ce.idEtudiant = u.id and ce.idclass=? ");
                pre.setInt(1, idclass);
                ResultSet rs = pre.executeQuery();

                while (rs.next()) {

                    String namecl = rs.getString("nameC");
                    String firstname = rs.getString("firstname");
                    int idstudent = rs.getInt(1);
                    int idcla=rs.getInt("idclass");
                    
                    classEtudiant ce = new classEtudiant(idstudent, namecl, firstname);
                    ce.setIdclass(idcla);
                    arr.add(ce);
                }
            } catch (SQLException ex) {
                Logger.getLogger(classEtudiantCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }

            return arr;
        }
    }

    public List<classEtudiant> rechercheetudiant(int idetudiant) throws SQLException {
        {
            List<classEtudiant> arr = new ArrayList<>();
            PreparedStatement pre = con.prepareStatement("SELECT  cl.idclass,u.id,Cl.nameC ,u.firstname FROM  class cl ,user u, classEtudiant ce WHERE ce.idclass = cl.idclass AND ce.idEtudiant = u.id and ce.idEtudiant=? ");
            pre.setInt(1, idetudiant);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                String namecl = rs.getString("nameC");
                String firstname = rs.getString("firstname");
                classEtudiant ce = new classEtudiant(namecl, firstname);
                ce.setIdclass(rs.getInt("idclass"));
                ce.setIdetudiant(rs.getInt("id"));
                arr.add(ce);
            }

            return arr;
        }

    }

    public boolean supprimerclassEtudiant(int idEtudiant, int idclass) throws SQLException {

        PreparedStatement pre = con.prepareStatement("Delete from classetudiant where idclass=? and  idEtudiant=? ");
        try {

            pre.setInt(1, idclass);
            pre.setInt(2, idEtudiant);
            if (pre.executeUpdate() != 0) {
                System.out.println("Deleted");

                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     //   System.out.println("id not found!!!");
        return false;

    }

}
