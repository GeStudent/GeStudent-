/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.services;

import edu.gestudent.entities.Behaviour;
import edu.gestudent.entities.tsa;
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
 * @author Asus
 */
public class tsaCRUD {
        private Connection con;
    private Statement ste;

    public tsaCRUD() {
        con = DataBase.getInstance().getConnection();

    }

    public void ajouter(tsa t)  {
            try {
                //zaama naamel jointure houni ? wala bch yajouti kol chay iwali ?
                PreparedStatement pre = con.prepareStatement(" INSERT INTO `tsa` ( `idstu`, `idtea`,`idbeh`) VALUES ( ?, ?, ?); ");
                pre.setInt(1, t.getIdstu());
                pre.setInt(2, t.getIdtea());
                pre.setInt(3, t.getIdbeh());
                
                pre.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(tsaCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
        public void remove(int idstu,int idtea,int idbeh)  {
            try {
                //zaama naamel jointure houni ? wala bch yajouti kol chay iwali ?
                PreparedStatement pre = con.prepareStatement(" Delete FROM  tsa where idstu=? and idtea=? and idbeh=? ");
                pre.setInt(1, idstu);
                pre.setInt(2, idtea);
                pre.setInt(3,idbeh);
                System.out.println("dssssssssssssssssssssssss"
                        + "d");
                
                
                pre.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }
    

    public List<Behaviour> afficherBehaviourstu(int idtea,int idstu) {
        ArrayList<Behaviour> per = new ArrayList();

        try {
            String requete3 = "SELECT b.idbeh,b.nombeh,b.award FROM behaviour b,user u ,tsa t WHERE b.idbeh =t.idbeh AND t.idstu =u.id AND t.idtea = ? AND t.idstu=? ;";
            PreparedStatement pst2 = con.prepareStatement(requete3);
            pst2.setInt(1, idtea);
             pst2.setInt(2, idstu);

            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                Behaviour b = new Behaviour();
                b.setIdbeh(rs.getInt(1));
                b.setNombeh(rs.getString(2));
                b.setAward(rs.getInt(3));

                per.add(b);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return per;
    }
    
    
    
    public List<Behaviour> afficherBehaviourstu(int idstu) {
        ArrayList<Behaviour> per = new ArrayList();

        try {
            String requete3 = "SELECT DISTINCT b.idbeh,b.nombeh,b.award , t.idstu ,t.idtea FROM behaviour b,user u ,tsa t WHERE   t.idstu=u.id and u.id=?";
            PreparedStatement pst2 = con.prepareStatement(requete3);
            pst2.setInt(1, idstu);

            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                Behaviour b = new Behaviour();
                b.setIdbeh(rs.getInt(1));
                b.setNombeh(rs.getString(2));
                b.setAward(rs.getInt(3));

                per.add(b);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return per;
    }
}