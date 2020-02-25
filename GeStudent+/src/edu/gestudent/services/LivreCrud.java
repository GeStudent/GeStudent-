/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.services;

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
public class LivreCrud {

    Connection cn2;
    Statement st;
    String tri = " ORDER BY name " ;
    String ordre = " DESC" ;
    public void ordrecroiss () {
        ordre = " ASC" ; 
    }
     public void ordredecroiss () {
        ordre = " DESC" ; 
    }
    
    public void triByName() { 
        tri = " ORDER BY author ";
        }
    public void triByAuthor() { 
        tri = " ORDER BY author ";
        }

    
    public LivreCrud() {
        cn2 = DataBase.getInstance().getConnection();
    }



    public void ajouterLivre(Livre l) {
        String requete2 = "INSERT INTO livres (name,image,author,url,categorie,quantite) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn2.prepareStatement(requete2);

            pst.setString(1, l.getName());
            pst.setString(2, l.getImage());
            pst.setString(3, l.getAuthor());
            pst.setString(4, l.getUrl());
            pst.setString(5, l.getCategorie());
            pst.setInt(6, l.getQuantite());
            pst.executeUpdate();
            System.out.println("livre ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Livre> afficherlivre() {
        ArrayList<Livre> liv = new ArrayList<>();
        try {
            String requete3 = ("SELECT * FROM livres"+ tri + ordre) ;
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();
            while(rs.next()){
                Livre l = new Livre();
                l.setId_livre(rs.getInt("id_livre"));
                l.setName(rs.getString("name"));
                l.setImage(rs.getString("image"));
                l.setAuthor(rs.getString("author"));
                l.setUrl(rs.getString("url"));
                l.setCategorie(rs.getString("categorie"));
                l.setQuantite(rs.getInt("quantite"));
                liv.add(l);
            }
        } catch (SQLException ex) {
        }
        return liv;
    }
    public boolean supprimerlivre(Livre l )  {

        String reqeute = "delete from livres  where (name = ?) ;";
        try {
            PreparedStatement pst = cn2.prepareStatement(reqeute);
            pst.setString(1, l.getName());
            if (pst.executeUpdate() != 0) {
                System.out.println("livre deleted");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;
//To change body of generated methods, choose Tools | Templates.
    }

    public boolean Update(String name,String image,String url,String categorie,int quantite)   {

        String reqeute = "UPDATE livres SET  image= ? , url= ? , categorie= ? , quantite=?  where name = ? ;";
        try {
            PreparedStatement pst = cn2.prepareStatement(reqeute);

            pst.setString(1, image);
            pst.setString(2, url);
            pst.setString(3, categorie);
            pst.setInt(4, quantite);
            pst.setString(5, name);
            if (pst.executeUpdate() != 0) {
                System.out.println("livre Updated");
            } else {
                System.out.println("non");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return false;
    }
    public List<Livre> rechrecherlivre(String Name)
    {
        try {
            List<Livre> liv=new ArrayList<>();
            PreparedStatement pre=cn2.prepareStatement("Select * from livres  WHERE name=? " + tri + ordre);
            pre.setString(1, Name );
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String name=rs.getString("name");
                String image=rs.getString("image");
                String author=rs.getString("author");
                String url=rs.getString("url");
                String categorie=rs.getString("categorie");
                int quantite=rs.getInt("quantite");
                Livre l=new Livre (name,image,author,url,categorie,quantite);
                liv.add(l);
            }
            return liv;
        } catch (SQLException ex) {
            Logger.getLogger(LivreCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public int getidliv(String name) {
        int q = 0;

        String requete4 = "select id_livre from livres where name=?;";
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
    
    
    public String getImageLivre(int idlivre) {
        String q = "";

        String requete4 = "select image from livres where id_livre=?;";
        PreparedStatement pst;
        try {
            pst = cn2.prepareStatement(requete4);
            pst.setInt(1, idlivre);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                q = rs.getString(1);
            }
        } catch (SQLException ex) {
        }
        return q;
    }
}
