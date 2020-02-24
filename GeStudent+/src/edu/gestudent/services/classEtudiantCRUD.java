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

    public void ajouter(classEtudiant ce) throws SQLException {
        PreparedStatement pre = con.prepareStatement(" INSERT INTO `classEtudiant` ( `idclass`, `idetudiant`) VALUES ( ?, ?); ");
        pre.setInt(1, ce.getidclass());
        pre.setInt(2, ce.getidetudiant());
        pre.executeUpdate();
    }

    public List<classEtudiant> rechercheclassetudiant(int idclass) throws SQLException {
        {
            List<classEtudiant> arr = new ArrayList<>();
            PreparedStatement pre = con.prepareStatement("SELECT  Cl.nameC, u.firstname  FROM  class cl ,user u, classEtudiant ce WHERE ce.idclass = cl.idclass AND ce.idEtudiant = u.id and ce.idclass=? ");
            pre.setInt(1, idclass);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                String namecl = rs.getString("nameC");
                String firstname = rs.getString("firstname");
                classEtudiant ce = new classEtudiant(namecl, firstname);
                arr.add(ce);
            }

            return arr;
        }
    }

    public List<classEtudiant> rechercheetudiant(int idetudiant) throws SQLException {
        {
            List<classEtudiant> arr = new ArrayList<>();
            PreparedStatement pre = con.prepareStatement("SELECT  Cl.nameC ,u.firstname FROM  class cl ,user u, classEtudiant ce WHERE ce.idclass = cl.idclass AND ce.idEtudiant = u.id and ce.idEtudiant=? ");
            pre.setInt(1, idetudiant);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                String namecl = rs.getString("nameC");
                String firstname = rs.getString("firstname");
                classEtudiant ce = new classEtudiant(namecl, firstname);
                arr.add(ce);
            }

            return arr;
        }
        
        

    }

}
