/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.services;

import edu.gestudent.entities.classe;
import edu.gestudent.entities.cours;
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
public class classCRUD{

    private Connection con;
    private Statement ste;

    public classCRUD() {
        con =  DataBase.getInstance().getConnection();

    }

    public void ajouter(classe cl) throws SQLException {
 PreparedStatement pre=con.prepareStatement("INSERT INTO `class` ( `nameC`) VALUES ( ? );");      
     pre.setString(1, cl.getNameC());
   
    
     pre.executeUpdate();
    }

    public boolean supprimerclass(int idclass) throws SQLException {

        PreparedStatement pre = con.prepareStatement("Delete from class where idclass=? ;");
        try {
            pre.setInt(1, idclass);
            if (pre.executeUpdate() != 0) {
                System.out.println("idclass Deleted");
                /*          
   String requete = "SELECT idcours FROM tcc WHERE idcours NOT IN(SELECT idcour FROM cours) ";
            PreparedStatement p = con.prepareStatement(requete);  
            ResultSet rs = p.executeQuery();
    int idcours=rs.getInt("idcours") ;
    
 
          System.out.println(idcours);
                 */

                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("id class not found!!!");
        return false;

    }

    public boolean modifierclass(String nameC, int idclass) throws SQLException {
        try {
            PreparedStatement pre = con.prepareStatement("update class set nameC =? where idclass=? ;");

            pre.setString(1, nameC);
            pre.setInt(2, idclass);

            if (pre.executeUpdate() != 0) {
                System.out.println(" updated");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("id  not found!!!");
        return false;

    }

    public boolean delete(classe cl) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean update(classe cl) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<classe> readAll() throws SQLException {
        List<classe> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from class");
        while (rs.next()) {
            int idclass = rs.getInt(1);
            String nameC = rs.getString("nameC");
              

           classe cl = new classe(nameC, idclass);
            arr.add(cl);
        }
        return arr;
    }

}
