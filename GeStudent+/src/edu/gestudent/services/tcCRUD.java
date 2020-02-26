/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.services;

import edu.gestudent.entities.tc;
import edu.gestudent.utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class tcCRUD {

    private Connection con;
    private Statement ste;

    public tcCRUD() {
        con = DataBase.getInstance().getConnection();

    }

    public void ajouter(tc t) {
        try {
            PreparedStatement pre = con.prepareStatement(" INSERT INTO `tc` (`idtea`,`idclas`,`idexa`) VALUES ( ?, ?, ?); ");
            pre.setInt(1, t.getIdtea());
            pre.setInt(2, t.getIdclas());
            pre.setInt(3, t.getIdexa());

            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(tsaCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
